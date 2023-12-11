package com.miics.server.controller;

import com.miics.server.dao.dto.QuestionDto;
import com.miics.server.dao.dto.TestDto;
import com.miics.server.dao.dto.UserDto;
import com.miics.server.dao.models.Question;
import com.miics.server.dao.models.Test;

//import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//
//@RestController
//@RequestMapping("/tests")
//public class TestController {
//
//    private final TestTakingService testService;
//
//    @Autowired
//    public TestController(TestTakingService testService) {
//        this.testService = testService;
//    }
//
//    @PostMapping("/createTestAndQuestions")
//    //@ApiOperation(value = "создание теста", response = TestDto.class)
//    public ResponseEntity<TestDto> createTest(@RequestBody TestDto testDto) {
//        return ResponseEntity.ok(testService.createTestAndQuestions(testDto));
//    }


//
//    @GetMapping("/{testId}")
//    //@ApiOperation(value = "получение теста", response = TestDto.class)
//    //@PreAuthorize("hasAuthority('access:read')")
//    public ResponseEntity<TestDto> showTest(@PathVariable("testId") Long testId) {
//        return ResponseEntity.ok(testService.getTestById(testId));
//    }

//    @PostMapping("/{testId}/questions")
//    public ResponseEntity<QuestionDto> addQuestion(@PathVariable Long testId, @RequestBody QuestionDto questionDto) {
//        return ResponseEntity.ok(testService.addQuestion(testId, questionDto));
//    }

//    @GetMapping("")
//    @PreAuthorize("hasAuthority('access:read')")
//    public List<ResponseEntity<TestDto>> showTests() {
//        return ResponseEntity.of(testService.getTests());
//    }
//
//    @DeleteMapping("/delete")
//    @PreAuthorize("hasAuthority('access:write')")
//    public ResponseEntity<TestDto> deleteTest(@RequestBody TestDto testDto) {
//        return ResponseEntity.ok(testService.deleteTest(testDto));
//    }
//
//    @DeleteMapping("/delete/{testId}")
//    @PreAuthorize("hasAuthority('access:write')")
//    public ResponseEntity<UserDto> deleteTestById(@PathVariable("testId") Long testId) {
//        return ResponseEntity.ok(testService.deleteTestById(testId));
//    }
//}