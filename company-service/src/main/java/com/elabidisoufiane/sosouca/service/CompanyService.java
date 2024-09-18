package com.elabidisoufiane.sosouca.service;

import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.elabidisoufiane.sosouca.dto.CompanyRequestDto;
import com.elabidisoufiane.sosouca.dto.CompanyResponseDto;

public interface CompanyService {

	CompanyResponseDto save(CompanyRequestDto Dto);

	CompanyResponseDto findById(Integer id);

	CompanyResponseDto findByName(String title);

	void delete(Integer id);

	List<CompanyResponseDto> findAll();

	CompanyResponseDto update(CompanyRequestDto dto, Integer id) throws NotFoundException;

}
