package com.skinmarket.project.service;

import com.skinmarket.project.model.entity.SkinInstance;
import com.skinmarket.project.repository.SkinInstanceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class SkinInstanceService {
    private final SkinInstanceRepository skinInstanceRepository;

    public SkinInstanceService(SkinInstanceRepository skinInstanceRepository) {
        this.skinInstanceRepository = skinInstanceRepository;
    }

    @Transactional
    public SkinInstance save(SkinInstance si) {
        return skinInstanceRepository.save(si);
    }

    @Transactional(readOnly = true)
    public List<SkinInstance> getAll() {
        return skinInstanceRepository.findAll();
    }
}