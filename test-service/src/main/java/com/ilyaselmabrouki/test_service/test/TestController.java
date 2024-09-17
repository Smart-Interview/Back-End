package com.ilyaselmabrouki.test_service.test;

import com.ilyaselmabrouki.test_service.question.QuestionResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/tests")
@RequiredArgsConstructor
public class TestController {

    private final TestService service;

    @PostMapping
    public ResponseEntity<Integer> createTest(@RequestBody @Valid TestRequest test){
        return ResponseEntity.ok(service.createTest(test));
    }

    @GetMapping
    public ResponseEntity<Page<TestResponse>> getAllTests(
            @RequestParam Integer candidateId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        return ResponseEntity.ok(service.getTests(candidateId, page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<QuestionResponse>> findTest(@PathVariable Integer id){
        return ResponseEntity.ok(service.findTest(id));
    }

    @PostMapping("{id}/result")
    public ResponseEntity<Double> calculateResult(@PathVariable Integer id, @RequestBody @Valid List<ResultRequest> answers){
        return ResponseEntity.ok(service.calculateResult(id,answers));
    }
}
