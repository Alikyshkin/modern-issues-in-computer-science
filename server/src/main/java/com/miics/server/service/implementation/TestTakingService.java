package com.miics.server.service.implementation;

import com.miics.server.dao.dto.TestDto;
import com.miics.server.dao.mappers.ITestMapper;
import com.miics.server.dao.models.Question;
import com.miics.server.dao.models.Result;
import com.miics.server.dao.models.Test;
import com.miics.server.dao.models.User;
import com.miics.server.dao.repositories.IResultRepository;
import com.miics.server.dao.repositories.ITestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//@Service
//public class TestTakingService {
//
//    private final ITestRepository testRepository;
//
//    private final IResultRepository resultRepository;
//
//    private final ITestMapper testMapper;
//
//    @Autowired
//    public TestTakingService(ITestRepository testRepository, IResultRepository resultRepository, ITestMapper testMapper) {
//        this.testRepository = testRepository;
//        this.resultRepository = resultRepository;
//        this.testMapper = testMapper;
//    }
//
////    public void submitTest(Long testId, User student, int score) {
////        Test test = testRepository.findById(testId).orElseThrow(() -> new RuntimeException("Test not found"));
////        Result result = new Result();
////        result.setTest(test);
////        result.setUser(student);
////        result.setScore(score);
////        resultRepository.save(result);
////    }
//
//    public TestDto createTestAndQuestions(TestDto testDto) {
//        Test test = testMapper.unDto(testDto);
//        testRepository.save(test);
//        return testMapper.toDto(test);
//    }
//
//    public TestDto getTestById(Long testId){
//
//        Optional<Test> testOptional = testRepository.findById(testId);
//        if(testOptional.isPresent()){
//            Test test = testOptional.get();
//            return testMapper.toDto(test);
//        }else throw new NullPointerException("Теста не существует");
//    }
//
//}
