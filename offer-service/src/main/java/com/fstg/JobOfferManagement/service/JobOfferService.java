package com.fstg.JobOfferManagement.service;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.fstg.JobOfferManagement.company.CompanyClient;
import com.fstg.JobOfferManagement.company.CompanyResponse;
import com.fstg.JobOfferManagement.exception.CompanyNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fstg.JobOfferManagement.dao.JobOfferRepository;
import com.fstg.JobOfferManagement.dto.JobOfferRequestDto;
import com.fstg.JobOfferManagement.dto.JobOfferResponseDto;
import com.fstg.JobOfferManagement.exception.EntityNotFoundException;
import com.fstg.JobOfferManagement.model.JobOffer;

@Service
@AllArgsConstructor
public  class JobOfferService {
	private final JobOfferRepository repository;
	private final OfferMapper mapper;
	private final FileStorageService fileStorageService;
	private final CompanyClient companyClient;

	public Integer save(MultipartFile file, JobOfferRequestDto dto) {
		//Fetch offer details from the Offer Service
		companyClient.findCompanyById(dto.getCompany());
		JobOffer offer = mapper.toJobOffer(dto);
		JobOffer savedOffer = repository.save(offer);
		try {
			String descriptionPath = fileStorageService.saveDescriptionToFile(file, savedOffer.getId());
			offer.setDescriptionPath(descriptionPath);
			repository.save(offer);
		}
		catch (IOException e) {
			throw new RuntimeException("Failed to store job description PDF.", e);
		}
		return savedOffer.getId();
	}

	public JobOfferResponseDto findById(Integer id) {
		//Fetch offer details from the Offer Service
		CompanyResponse companyResponse =
				companyClient.findCompanyById(id).orElseThrow(()-> new CompanyNotFoundException("Company not found"));
		JobOffer offer = repository
				.findById(id).orElseThrow(()-> new EntityNotFoundException("offer not found"));
		JobOfferResponseDto offerResponseDto = mapper.fromJobOffer(offer);
		offerResponseDto.setCompany(companyResponse);
		return offerResponseDto;
	}

	public void delete(Integer id) {
		repository.deleteById(id);
	}

	public JobOfferResponseDto update(JobOfferRequestDto dto , Integer id) throws  NotFoundException{
		Optional<JobOffer> offer = repository.findById(id);
		if(offer.isPresent()) {
			JobOffer joboffer = mapper.toJobOffer(dto);
			joboffer.setId(id);
			JobOffer updated = repository.save(joboffer);
			return mapper.fromJobOffer(updated);
		}
		else {
			throw new EntityNotFoundException("offer not found");
		}
	}

	public List<JobOfferResponseDto> findOffersByIds(List<Integer> offerIds) {
		// Remove duplicates by converting the list to a set
		Set<Integer> uniqueCandidateIds = new HashSet<>(offerIds);

		// Stream over the unique IDs and map each to a CandidateResponse
		return uniqueCandidateIds.stream()
				.map(id -> {
					JobOffer offer = repository.findById(id)
							.orElseThrow(() -> new RuntimeException("Offer with ID " + id + " not found"));
					return mapper.fromJobOffer(offer);
				})
				.collect(Collectors.toList());
	}

	public List<JobOfferResponseDto> findAll(){
		return repository.findAll().stream()
				.map(mapper::fromJobOffer)
				.collect(Collectors.toList());
	}
}
