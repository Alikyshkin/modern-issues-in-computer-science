package com.miics.server.service;

import com.miics.server.dao.models.Result;
import com.miics.server.dao.repositories.IResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnalyticsService {

    private final IResultRepository resultRepository;

    @Autowired
    public AnalyticsService(IResultRepository resultRepository) {
        this.resultRepository = resultRepository;
    }

    public List<Result> getResultsForTest(Long testId) {
        return resultRepository.findAllById(testId);
    }
}
