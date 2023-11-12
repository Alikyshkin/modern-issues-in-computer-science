package com.miics.server.service;

import com.miics.server.dao.models.Result;
import com.miics.server.dao.models.Test;
import com.miics.server.dao.models.User;
import com.miics.server.dao.repositories.IResultRepository;
import com.miics.server.dao.repositories.ITestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestTakingService {

    @Autowired
    private ITestRepository testRepository;

    @Autowired
    private IResultRepository resultRepository;

    public Test startTest(Long testId) {
        return testRepository.findById(testId).orElseThrow(() -> new RuntimeException("Test not found"));
    }

    public void submitTest(Long testId, User student, int score) {
        Test test = testRepository.findById(testId).orElseThrow(() -> new RuntimeException("Test not found"));
        Result result = new Result();
        result.setTest(test);
        result.setUser(student);
        result.setScore(score);
        resultRepository.save(result);
    }
}
