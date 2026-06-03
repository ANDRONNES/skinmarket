package com.skinmarket.project.service;

import com.skinmarket.project.model.entity.BuyOrder;
import com.skinmarket.project.repository.BuyOrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class BuyOrderService {
    private final BuyOrderRepository buyOrderRepository;

    public BuyOrderService(BuyOrderRepository buyOrderRepository) {
        this.buyOrderRepository = buyOrderRepository;
    }

    @Transactional
    public BuyOrder save(BuyOrder bo) {
        return buyOrderRepository.save(bo);
    }

    @Transactional(readOnly = true)
    public List<BuyOrder> getAll() {
        return buyOrderRepository.findAll();
    }
}