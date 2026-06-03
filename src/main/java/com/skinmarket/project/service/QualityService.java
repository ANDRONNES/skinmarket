package com.skinmarket.project.service;

import com.skinmarket.project.model.entity.Quality;
import com.skinmarket.project.repository.QualityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class QualityService {
    private final QualityRepository qualityRepository;

    public QualityService(QualityRepository qualityRepository) {
        this.qualityRepository = qualityRepository;
    }

    @Transactional
    public Quality save(Quality q) {
        return qualityRepository.save(q);
    }

    @Transactional(readOnly = true)
    public List<Quality> getAll() {
        return qualityRepository.findAll();
    }
}