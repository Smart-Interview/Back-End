package com.ilyaselmabrouki.test_service.test;

import com.ilyaselmabrouki.test_service.question.QuestionResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<List<TestResponse>> getAllTests(){
        return ResponseEntity.ok(service.getTests());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TestResponse> findTest(@PathVariable Integer id){
        return ResponseEntity.ok(service.findTest(id));
    }

//    @GetMapping("{id}/questions")
//    public ResponseEntity<List<QuestionResponse>> getQuestions(@PathVariable Integer id){
//        return ResponseEntity.ok(service.getQuestions(id));
//    }

    //Find Tests by Offer

    //Find Tests by Candidate

    //Find Tests by application

}
