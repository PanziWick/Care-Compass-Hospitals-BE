package com.icbt.hospitalsystem.feedback;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feedback")
@RequiredArgsConstructor
public class FeedbackController {

    private final FeedbackService feedbackService;

    @PostMapping
    public ResponseEntity<FeedbackResponseDTO> createFeedback(@RequestBody FeedbackRequestDTO feedbackRequest) {
        return ResponseEntity.ok(feedbackService.createFeedback(feedbackRequest));
    }

    @GetMapping
    public ResponseEntity<List<FeedbackResponseDTO>> getAllFeedback() {
        return ResponseEntity.ok(feedbackService.getAllFeedback());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FeedbackResponseDTO> getFeedbackById(@PathVariable Long id) {
        return ResponseEntity.ok(feedbackService.getFeedbackById(id));
    }
}
