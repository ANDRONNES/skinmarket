package com.skinmarket.project.service;

import com.skinmarket.project.model.entity.CharmInstance;
import com.skinmarket.project.repository.CharmInstanceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class CharmInstanceService {
    private final CharmInstanceRepository charmInstanceRepository;

    public CharmInstanceService(CharmInstanceRepository charmInstanceRepository) {
        this.charmInstanceRepository = charmInstanceRepository;
    }

    @Transactional
    public CharmInstance save(CharmInstance ci) {
        return charmInstanceRepository.save(ci);
    }

    @Transactional(readOnly = true)
    public List<CharmInstance> getAll() {
        return charmInstanceRepository.findAll();
    }
}