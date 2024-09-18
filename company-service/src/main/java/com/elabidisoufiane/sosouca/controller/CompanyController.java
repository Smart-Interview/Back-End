package com.elabidisoufiane.sosouca.controller;

import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.elabidisoufiane.sosouca.service.CompanyService;
import com.elabidisoufiane.sosouca.dto.CompanyResponseDto;
import com.elabidisoufiane.sosouca.dto.CompanyRequestDto;
import jakarta.validation.Valid;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/companies")
@AllArgsConstructor
public class CompanyController {

	private final CompanyService service;

	@PostMapping
	public ResponseEntity<Integer> save(@Valid @RequestBody CompanyRequestDto request) {
		return ResponseEntity.ok(service.save(request));
	}

	@GetMapping("/ceo/{id}")
	public ResponseEntity<List<CompanyResponseDto>> getCompanies(@PathVariable Integer id) {
		return new ResponseEntity<>(service.findAll(id), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CompanyResponseDto> findById(@PathVariable Integer id) {
		return ResponseEntity.ok(service.findById(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<CompanyResponseDto> update(@Valid @RequestBody CompanyRequestDto produitDto,
			@PathVariable Integer id) throws NotFoundException {
		CompanyResponseDto dto = service.update(produitDto, id);
		return ResponseEntity.accepted().body(dto);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
