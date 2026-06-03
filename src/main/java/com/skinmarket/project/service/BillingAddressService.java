package com.skinmarket.project.service;

import com.skinmarket.project.model.entity.BillingAddress;
import com.skinmarket.project.repository.BillingAddressRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class BillingAddressService {
    private final BillingAddressRepository billingAddressRepository;

    public BillingAddressService(BillingAddressRepository billingAddressRepository) {
        this.billingAddressRepository = billingAddressRepository;
    }

    @Transactional
    public BillingAddress save(BillingAddress ba) {
        return billingAddressRepository.save(ba);
    }

    @Transactional(readOnly = true)
    public List<BillingAddress> getAll() {
        return billingAddressRepository.findAll();
    }
}