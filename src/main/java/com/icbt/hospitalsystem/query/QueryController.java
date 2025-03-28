package com.icbt.hospitalsystem.query;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/queries")
@RequiredArgsConstructor
public class QueryController {

    private final QueryService queryService;

    @PostMapping
    public ResponseEntity<QueryResponseDTO> createQuery(@RequestBody QueryRequestDTO queryRequest) {
        return ResponseEntity.ok(queryService.createQuery(queryRequest));
    }

    @GetMapping
    public ResponseEntity<List<QueryResponseDTO>> getAllQueries() {
        return ResponseEntity.ok(queryService.getAllQueries());
    }

    @GetMapping("/{id}")
    public ResponseEntity<QueryResponseDTO> getQueryById(@PathVariable Long id) {
        return ResponseEntity.ok(queryService.getQueryById(id));
    }

    @PatchMapping("/{queryId}/answer")
    public ResponseEntity<QueryResponseDTO> answerQuery(
            @PathVariable Long queryId,
            @RequestParam Long answeredById,
            @RequestParam String answerMessage) {
        return ResponseEntity.ok(queryService.answerQuery(queryId, answeredById, answerMessage));
    }
}
