package com.skinmarket.project.service;

import com.skinmarket.project.dto.ListingDTO;
import com.skinmarket.project.exception.SellErrorException;
import com.skinmarket.project.mapper.DtoMapper;
import com.skinmarket.project.model.entity.*;
import com.skinmarket.project.model.entity.enums.BuyOrderStatus;
import com.skinmarket.project.model.entity.enums.InstanceStatus;
import com.skinmarket.project.model.entity.enums.TransactionStatus;
import com.skinmarket.project.model.entity.enums.TransactionType;
import com.skinmarket.project.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ListingService {
    private final ListingRepository listingRepository;
    private final DtoMapper<ListingDTO, Listing> listingMapper;
    private final ItemInstanceRepository itemInstanceRepository;
    private final UserRepository userRepository;
    private final BuyOrderRepository buyOrderRepository;
    private final TransactionRepository transactionRepository;

    public ListingService(ListingRepository listingRepository, DtoMapper<ListingDTO, Listing> listingMapper, ItemInstanceRepository itemInstanceRepository, UserRepository userRepository, BuyOrderRepository buyOrderRepository, TransactionService transactionService, TransactionRepository transactionRepository) {
        this.listingRepository = listingRepository;
        this.listingMapper = listingMapper;
        this.itemInstanceRepository = itemInstanceRepository;
        this.userRepository = userRepository;
        this.buyOrderRepository = buyOrderRepository;
        this.transactionRepository = transactionRepository;
    }

    @Transactional
    public Listing save(Listing l) {
        return listingRepository.save(l);
    }


    @Transactional(readOnly = true)
    public List<Listing> getAll() {
        return listingRepository.findAll();
    }


    @Transactional(readOnly = true)
    public List<ListingDTO> getListings() {
        return listingMapper.toDtoList(listingRepository.findAllWithItems());
    }


    @Transactional
    public void listOnMarket(Long instanceId, Long sellerId, BigDecimal price) {
        ItemInstance item = itemInstanceRepository.findById(instanceId).orElseThrow();
        User seller = userRepository.findById(sellerId).orElseThrow();

        if (price == null || price == BigDecimal.ZERO) {
            throw new IllegalArgumentException("Set the price!!!");
        }
        Listing newListing = Listing.builder().itemInstance(item).seller(seller).price(price).build();

        item.setStatus(InstanceStatus.ONSALE);
        item.setActiveListing(newListing);

        listingRepository.save(newListing);
        itemInstanceRepository.save(item);
    }


    @Transactional
    public void instantSell(Long instanceId, Long sellerId) {
        ItemInstance item = itemInstanceRepository.findById(instanceId).orElseThrow(() -> new SellErrorException("Item not found"));
        User seller = userRepository.findById(sellerId).orElseThrow();

        Long itemDefId = item.getItemDefinition().getItemDefinitionId();
        BuyOrder buyOrder = buyOrderRepository.findHighestActiveBuyOrder(itemDefId)
                .orElseThrow(() -> new SellErrorException("There are no active buy orders for that item"));

        User buyer = buyOrder.getBuyer();
        BigDecimal orderPrice = buyOrder.getTargetPrice();

        if (buyer.getBalance().compareTo(orderPrice) < 0) {
            throw new IllegalStateException("Buyer no longer has enough balance");
        }

        BigDecimal sellerPayout = orderPrice.multiply(BuyOrder.amountWithFee);
        seller.setBalance(seller.getBalance().add(sellerPayout));
        buyer.setBalance(buyer.getBalance().subtract(orderPrice));

        buyOrder.setBuyOrderStatus(BuyOrderStatus.CLOSED);
        buyOrder.setClosedAt(LocalDateTime.now());

        seller.getInventory().getItemInstanceList().remove(item);
        item.setInventory(buyer.getInventory());
        buyer.getInventory().getItemInstanceList().add(item);
        item.setStatus(InstanceStatus.ININVENTORY);

        Transaction transaction = Transaction.builder()
                .transactionType(TransactionType.BUYORDERMATCH)
                .transactionStatus(TransactionStatus.SUCCESS)
                .price(orderPrice)
                .seller(seller)
                .buyer(buyer)
                .listing(null)
                .buyOrder(buyOrder)
                .itemInstance(item)
                .build();

        transactionRepository.save(transaction);
        buyOrder.setTransaction(transaction);
    }

}