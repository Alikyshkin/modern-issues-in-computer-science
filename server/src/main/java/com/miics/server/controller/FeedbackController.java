package com.miics.server.controller;

import com.miics.server.dao.dto.FeedbackDto;
import com.miics.server.dao.dto.ResultDto;
import com.miics.server.dao.dto.UserDto;
import com.miics.server.dao.models.Feedback;
import com.miics.server.service.AnalyticsService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feedbacks")
public class FeedbackController {

    private final AnalyticsService feedbackService;

    public FeedbackController(AnalyticsService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @PostMapping("/{testId}")
    public ResponseEntity<FeedbackDto> addFeedback(@PathVariable Long testId, @RequestBody FeedbackDto feedbackDto) {
        return ResponseEntity.ok(feedbackService.addFeedback(testId, feedbackDto));
    }

    @GetMapping("/{testId}")
    @PreAuthorize("hasAuthority('access:read')")
    public ResponseEntity<FeedbackDto> showFeedback(@PathVariable("testId") Long testId) {
        return ResponseEntity.ok(feedbackService.getFeedbackById(testId));
    }

    @GetMapping("")
    @PreAuthorize("hasAuthority('access:read')")
    public List<ResponseEntity<FeedbackDto>> showFeedbacks() {
        return ResponseEntity.of(feedbackService.getFeedbacks());
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('access:write')")
    public ResponseEntity<FeedbackDto> deleteFeedback(@RequestBody FeedbackDto feedbackDto) {
        return ResponseEntity.ok(feedbackService.deleteFeedback(feedbackDto));
    }

    @DeleteMapping("/delete/{feedbackId}")
    @PreAuthorize("hasAuthority('access:write')")
    public ResponseEntity<FeedbackDto> deleteFeedbackById(@PathVariable("feedbackId") Long feedbackId) {
        return ResponseEntity.ok(feedbackService.deleteFeedBackById(feedbackId));
    }
}
