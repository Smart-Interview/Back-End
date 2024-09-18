package com.elabidisoufiane.sosouca.controller;

import com.elabidisoufiane.sosouca.dto.CEORequest;
import com.elabidisoufiane.sosouca.dto.CEOResponse;
import com.elabidisoufiane.sosouca.service.CEOService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/ceos")
@RequiredArgsConstructor
public class CEOController {

    private final CEOService service;

    @PostMapping
    public ResponseEntity<Integer> createCEO(
            @RequestBody @Valid CEORequest candidate
    ){
        return ResponseEntity.ok(service.createCEO(candidate));
    }

    @PutMapping
    public ResponseEntity<Void> updateCEO(
            @RequestBody @Valid CEORequest candidate
    ){
        service.updateCEO(candidate);
        return ResponseEntity.accepted().build();
    }

    @GetMapping
    public ResponseEntity<List<CEOResponse>> getAllCEOs(){
        return ResponseEntity.ok(service.getCEOs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CEOResponse> findCEO(@PathVariable Integer id){
        return ResponseEntity.ok(service.findCEO(id));
    }

    @PostMapping("/ids")
    public ResponseEntity<List<CEOResponse>> findCEOsByIds(@RequestBody List<Integer> candidateIds){
        return ResponseEntity.ok(service.findCEOsByIds(candidateIds));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCEO(@PathVariable Integer id){
        service.deleteCEO(id);
        return ResponseEntity.accepted().build();
    }

}
