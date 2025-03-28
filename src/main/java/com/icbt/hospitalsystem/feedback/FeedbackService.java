package com.icbt.hospitalsystem.feedback;

import com.icbt.hospitalsystem.common.ResourceNotFoundException;
import com.icbt.hospitalsystem.user.UserEntity;
import com.icbt.hospitalsystem.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final UserRepository userRepository;

    public FeedbackResponseDTO createFeedback(FeedbackRequestDTO feedbackRequest) {
        UserEntity patient = userRepository.findById(feedbackRequest.patientId())
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found"));

        UserEntity doctor = null;
        if (feedbackRequest.doctorId() != null) {
            doctor = userRepository.findById(feedbackRequest.doctorId())
                    .orElseThrow(() -> new ResourceNotFoundException("Doctor not found"));
        }

        Feedback feedback = Feedback.builder()
                .patient(patient)
                .doctor(doctor)
                .comment(feedbackRequest.comment())
                .rating(feedbackRequest.rating())
                .createdAt(LocalDateTime.now())
                .build();

        return mapToResponseDTO(feedbackRepository.save(feedback));
    }

    public List<FeedbackResponseDTO> getAllFeedback() {
        return feedbackRepository.findAll()
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    public FeedbackResponseDTO getFeedbackById(Long id) {
        Feedback feedback = feedbackRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Feedback not found"));
        return mapToResponseDTO(feedback);
    }

    private FeedbackResponseDTO mapToResponseDTO(Feedback feedback) {
        return new FeedbackResponseDTO(
                feedback.getId(),
                feedback.getPatient().getId(),
                feedback.getPatient().getUsername(),
                feedback.getDoctor() != null ? feedback.getDoctor().getId() : null,
                feedback.getDoctor() != null ? feedback.getDoctor().getUsername() : null,
                feedback.getComment(),
                feedback.getRating(),
                feedback.getCreatedAt()
        );
    }
}
