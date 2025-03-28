package com.icbt.hospitalsystem.query;

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
public class QueryService {

    private final QueryRepository queryRepository;
    private final UserRepository userRepository;

    public QueryResponseDTO createQuery(QueryRequestDTO queryRequest) {
        UserEntity patient = userRepository.findById(queryRequest.patientId())
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found"));

        Query query = Query.builder()
                .patient(patient)
                .subject(queryRequest.subject())
                .message(queryRequest.message())
                .answered(false)
                .createdAt(LocalDateTime.now())
                .build();

        Query savedQuery = queryRepository.save(query);

        return mapToResponseDTO(savedQuery);
    }

    public List<QueryResponseDTO> getAllQueries() {
        return queryRepository.findAll()
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    public QueryResponseDTO getQueryById(Long id) {
        Query query = queryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Query not found"));
        return mapToResponseDTO(query);
    }

    public QueryResponseDTO answerQuery(Long queryId, Long answeredById, String answerMessage) {
        Query query = queryRepository.findById(queryId)
                .orElseThrow(() -> new ResourceNotFoundException("Query not found"));

        UserEntity answeredBy = userRepository.findById(answeredById)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        query.setAnswered(true);
        query.setAnsweredAt(LocalDateTime.now());
        query.setAnsweredBy(answeredBy);
        query.setMessage(query.getMessage() + "\n\nResponse: " + answerMessage);

        return mapToResponseDTO(queryRepository.save(query));
    }

    private QueryResponseDTO mapToResponseDTO(Query query) {
        return new QueryResponseDTO(
                query.getId(),
                query.getPatient().getId(),
                query.getPatient().getUsername(),
                query.getSubject(),
                query.getMessage(),
                query.isAnswered(),
                query.getCreatedAt(),
                query.getAnsweredAt(),
                query.getAnsweredBy() != null ? query.getAnsweredBy().getId() : null,
                query.getAnsweredBy() != null ? query.getAnsweredBy().getUsername() : null
        );
    }
}
