package com.fstg.JobOfferManagement.service;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import  com.fstg.JobOfferManagement.dao.jobofferdao;
import com.fstg.JobOfferManagement.dto.JobOfferRequestDto;
import com.fstg.JobOfferManagement.dto.JobOfferResponseDto;
import com.fstg.JobOfferManagement.exception.EntityNotFoundException;
import com.fstg.JobOfferManagement.model.JobOffer;

@Service()
public  class JobOfferImpl implements JobOfferService {
	private jobofferdao dao;
	private ModelMapper mapper ;
	private FileStorageService fileStorageService;

	public JobOfferImpl(jobofferdao dao ,ModelMapper mapper,FileStorageService fileStorageService) {
		this.dao=dao;
		this.mapper = mapper ;
		this.fileStorageService = fileStorageService;
	}
	    @Override
	    public JobOfferResponseDto save(JobOfferRequestDto dto) {
	        // Step 1: Map DTO to entity
	        JobOffer offer = mapper.map(dto, JobOffer.class);

	        // Step 2: Save the offer to generate the jobOfferId
	        JobOffer savedOffer = dao.save(offer);

	        try {
	            // Step 3: Save the PDF description file using jobOfferId and title
	            MultipartFile pdfFile = (MultipartFile) dto.getPdfFile();
	            String title = dto.getTitle();

	            // Pass the jobOfferId and title to the file storage service
	            String descriptionPath = fileStorageService.saveDescriptionToFile(pdfFile, (Integer) savedOffer.getJobOfferId());

	            dao.save(savedOffer);  // Save again with updated description path
	        } catch (IOException e) {
	            throw new RuntimeException("Failed to store job description PDF.", e);
	        }
	        // Step 5: Return the response DTO
	        return mapper.map(savedOffer, JobOfferResponseDto.class);
	    }

	
	@Override
	public JobOfferResponseDto findById(Integer id) {
		
		JobOffer offer = dao.findById(id).orElseThrow(()-> new EntityNotFoundException("offer not found"));
		
		return mapper.map(offer,JobOfferResponseDto.class);

	}
	
	@Override
	public JobOfferResponseDto findByTitle(String title) {
		JobOffer offer = dao.findByTitle(title);
		return mapper.map(offer,JobOfferResponseDto.class);

	}
	
/*
	@Override
	public JobOfferResponseDto findByRecruiter(Integer Rec) {
		JobOffer offer = dao.findByRecruiter(Rec);
		return mapper.map(offer,JobOfferResponseDto.class);
	}
	*/
	@Override
	public void delete(Integer id) {
		dao.deleteById(id);
	}
	@Override
	public JobOfferResponseDto update(JobOfferRequestDto dto , Integer id) throws  NotFoundException{
		Optional<JobOffer> offer = dao.findById(id);
		if(offer.isPresent()) {
			JobOffer joboffer = mapper.map(dto, JobOffer.class);
			joboffer.setId(id);
			JobOffer updated = dao.save(joboffer);
			return mapper.map(updated, JobOfferResponseDto.class);
		}else {
			throw new EntityNotFoundException("offer not found");
		}

	}

	@Override
	public List<JobOfferResponseDto> findOffersByIds(List<Integer> offerIds) {
		// Remove duplicates by converting the list to a set
		Set<Integer> uniqueCandidateIds = new HashSet<>(offerIds);

		// Stream over the unique IDs and map each to a CandidateResponse
		return uniqueCandidateIds.stream()
				.map(id -> {
					JobOffer offer = dao.findById(id)
							.orElseThrow(() -> new RuntimeException("Offer with ID " + id + " not found"));
					return mapper.map(offer,JobOfferResponseDto.class);
				})
				.collect(Collectors.toList());
	}

	@Override
	public List<JobOfferResponseDto> findAll(){
		return dao.findAll().stream()
				.map(el -> mapper
				.map(el, JobOfferResponseDto.class))
				.collect(Collectors.toList());
	}
	

}
