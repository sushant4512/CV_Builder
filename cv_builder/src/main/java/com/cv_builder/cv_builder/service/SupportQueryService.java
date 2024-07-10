package com.cv_builder.cv_builder.service;

import com.cv_builder.cv_builder.entity.SupportQuery;
import com.cv_builder.cv_builder.repository.SupportQueryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupportQueryService {

    @Autowired
    private SupportQueryRepository repository;

    public List<SupportQuery> getAllQueries() {
        return repository.findAll();
    }

    public Optional<SupportQuery> getQueryById(Long id) {
        return repository.findById(id);
    }

    public SupportQuery saveQuery(SupportQuery query) {
        return repository.save(query);
    }

    public void deleteQuery(Long id) {
        repository.deleteById(id);
    }
}