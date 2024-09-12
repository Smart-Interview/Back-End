package com.fstg.JobOfferManagement.service;

import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.fstg.JobOfferManagement.dto.JobOfferRequestDto;
import com.fstg.JobOfferManagement.dto.JobOfferResponseDto;

public interface JobOfferService {
	
	JobOfferResponseDto save(JobOfferRequestDto Dto);
	
	JobOfferResponseDto findById(Integer id);
	
	JobOfferResponseDto findByTitle(String title);
	
	void delete(Integer id);
	
	List<JobOfferResponseDto>  findAll();

	JobOfferResponseDto update(JobOfferRequestDto dto,Integer id) throws NotFoundException;

	//JobOfferResponseDto findByRecruiter(Integer Rec);

}
