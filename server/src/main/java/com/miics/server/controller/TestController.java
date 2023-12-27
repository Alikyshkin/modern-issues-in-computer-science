package com.miics.server.controller;

import com.miics.server.dao.dto.TestDto;
import com.miics.server.dao.dto.ResultDto;
import com.miics.server.service.TestTakingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tests")
public class TestController {

    private final TestTakingService testService;

    @Autowired
    public TestController(TestTakingService testService) {
        this.testService = testService;
    }

    @PostMapping("/createTestAndQuestions")
    @PreAuthorize("hasAuthority('access:write')")
    //@ApiOperation(value = "создание теста", response = TestDto.class)
    public ResponseEntity<TestDto> createTest(@RequestBody TestDto testDto) {
        return ResponseEntity.ok(testService.createTestAndQuestions(testDto));
    }



    @GetMapping("/{testId}")
    //@ApiOperation(value = "получение теста", response = TestDto.class)
    @PreAuthorize("hasAuthority('access:write')")
    public ResponseEntity<TestDto> showTest(@PathVariable("testId") Long testId) {
        return ResponseEntity.ok(testService.getTestById(testId));
    }

    @PostMapping("/sendResults")
    @PreAuthorize("hasAuthority('access:read')")
    public ResponseEntity<ResultDto> sendResults(@RequestBody ResultDto resultDto){
        return ResponseEntity.ok(testService.calculateResults(resultDto));
    }

    @GetMapping("/getAllTests")
    //@ApiOperation(value = "получение теста", response = TestDto.class)
    //@PreAuthorize("hasAuthority('access:read')")
    public ResponseEntity<List<TestDto>> getAllTests() {
        return ResponseEntity.ok(testService.getAllTests());
    }
}
