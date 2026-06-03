package com.skinmarket.project.service;

import com.skinmarket.project.model.entity.Transaction;
import com.skinmarket.project.repository.TransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Transactional
    public Transaction save(Transaction t) {
        return transactionRepository.save(t);
    }

    @Transactional(readOnly = true)
    public List<Transaction> getAll() {
        return transactionRepository.findAll();
    }
}