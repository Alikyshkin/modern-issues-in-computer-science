//package com.miics.server.controller;
//
//import com.miics.server.dao.dto.QuestionDto;
//import com.miics.server.dao.dto.ResultDto;
//import com.miics.server.dao.dto.TestDto;
//import com.miics.server.dao.dto.UserDto;
//import com.miics.server.dao.models.Result;
//import com.miics.server.service.AnalyticsService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/results")
//public class ResultController {
//
//    private final AnalyticsService resultService;
//
//    @Autowired
//    public ResultController(AnalyticsService resultService) {
//        this.resultService = resultService;
//    }
//
//    @PostMapping("/{testId}")
//    public ResponseEntity<ResultDto> submitTest(@PathVariable Long testId, @RequestBody List<QuestionDto> questionDtos) {
//        return ResponseEntity.ok(resultService.submitTest(testId, questionDtos));
//    }
//
//    @GetMapping("/{testId}")
//    @PreAuthorize("hasAuthority('access:read')")
//    public ResponseEntity<ResultDto> showResult(@PathVariable("testId") Long testId) {
//        return ResponseEntity.ok(resultService.getResultById(testId));
//    }
//
//    @GetMapping("")
//    @PreAuthorize("hasAuthority('access:read')")
//    public List<ResponseEntity<ResultDto>> showResults() {
//        return ResponseEntity.of(resultService.getResults());
//    }
//
//    @DeleteMapping("/delete")
//    @PreAuthorize("hasAuthority('access:write')")
//    public ResponseEntity<ResultDto> deleteResult(@RequestBody ResultDto resultDto) {
//        return ResponseEntity.ok(resultService.deleteResult(resultDto));
//    }
//
//    @DeleteMapping("/delete/{resultId}")
//    @PreAuthorize("hasAuthority('access:write')")
//    public ResponseEntity<UserDto> deleteTestById(@PathVariable("resultId") Long resultId) {
//        return ResponseEntity.ok(resultService.deleteResultById(resultId));
//    }
//}
