package com.elabidisoufiane.sosouca.controller;

import com.elabidisoufiane.sosouca.dto.CompanyResponseDto;
import com.elabidisoufiane.sosouca.dto.RHRequest;
import com.elabidisoufiane.sosouca.dto.RHResponse;
import com.elabidisoufiane.sosouca.service.RHService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/rhs")
@AllArgsConstructor
public class RHController {

	private final RHService service;

	@PostMapping
	public ResponseEntity<RHResponse> save(@Valid @RequestBody RHRequest request) {
		return ResponseEntity.ok(service.save(request));
	}

	@GetMapping
	public ResponseEntity<RHResponse> getRHs(@RequestParam String email) {
		return ResponseEntity.ok(service.getRHByEmail(email));
	}

	@GetMapping("/company/{id}")
	public ResponseEntity<List<RHResponse>> getRHs(@PathVariable Integer id) {
		return ResponseEntity.ok(service.getAllRHByCompany(id));
	}

}
