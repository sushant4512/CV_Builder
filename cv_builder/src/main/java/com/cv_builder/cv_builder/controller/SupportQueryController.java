package com.cv_builder.cv_builder.controller;

import com.cv_builder.cv_builder.entity.SupportQuery;
import com.cv_builder.cv_builder.service.SupportQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/queries")
public class SupportQueryController {

    @Autowired
    private SupportQueryService service;

    @GetMapping
    public List<SupportQuery> getAllQueries() {
        return service.getAllQueries();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupportQuery> getQueryById(@PathVariable Long id) {
        return service.getQueryById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public SupportQuery createQuery(@RequestBody SupportQuery query) {
        return service.saveQuery(query);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SupportQuery> updateQuery(@PathVariable Long id, @RequestBody SupportQuery query) {
        return service.getQueryById(id)
                .map(existingQuery -> {
                    query.setId(existingQuery.getId());
                    return ResponseEntity.ok(service.saveQuery(query));
                })
                .orElse(ResponseEntity.notFound().build());
    }


}