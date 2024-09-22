package com.fstg.JobOfferManagement.service;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import com.fstg.JobOfferManagement.company.CompanyClient;
import com.fstg.JobOfferManagement.company.CompanyResponse;
import com.fstg.JobOfferManagement.exception.CompanyNotFoundException;
import com.fstg.JobOfferManagement.exception.OfferNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
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
		JobOffer offer = repository
				.findById(id).orElseThrow(()-> new OfferNotFoundException("Offer not found"));

		CompanyResponse companyResponse =
				companyClient.findCompanyById(offer.getCompany())
						.orElseThrow(()-> new CompanyNotFoundException("Company not found"));

		JobOfferResponseDto offerResponseDto = mapper.fromJobOffer(offer);
		offerResponseDto.setCompany(companyResponse);
		return offerResponseDto;
	}

	public Page<JobOfferResponseDto> findAll(Integer companyId, int page, int size){
		// Check candidate ID (add error handling if candidate not found)
		companyClient.findCompanyById(companyId);

		// Fetch applications
		Pageable pageable = PageRequest.of(page, size);
		Page<JobOffer> offers = repository.findByCompany(companyId, pageable);

		// Fetch all offer responses in a single call (to avoid N+1 problem)
		List<Integer> companyIds = offers.stream()
				.map(JobOffer::getCompany)
				.collect(Collectors.toList());

		List<CompanyResponse> companies = companyClient.findByIds(companyIds);

		Map<Integer, CompanyResponse> companyMap = companies.stream()
				.collect(Collectors.toMap(CompanyResponse::getId, company -> company));

		// Map applications to responses
		return offers.map(offer -> {
			CompanyResponse companyResponse = companyMap.get(offer.getCompany());
			if (companyResponse == null) {
				throw new CompanyNotFoundException("Company not found");
			}
			JobOfferResponseDto offerResponse = mapper.fromJobOffer(offer);
			offerResponse.setCompany(companyResponse);
			return offerResponse;
		});
	}

	public Page<JobOfferResponseDto> findAllOffers(int page, int size){
		// Fetch applications
		Pageable pageable = PageRequest.of(page, size);
		Page<JobOffer> offers = repository.findAll(pageable);

		// Fetch all offer responses in a single call (to avoid N+1 problem)
		List<Integer> companyIds = offers.stream()
				.map(JobOffer::getCompany)
				.collect(Collectors.toList());

		List<CompanyResponse> companies = companyClient.findByIds(companyIds);

		Map<Integer, CompanyResponse> companyMap = companies.stream()
				.collect(Collectors.toMap(CompanyResponse::getId, company -> company));

		// Map applications to responses
		return offers.map(offer -> {
			CompanyResponse companyResponse = companyMap.get(offer.getCompany());
			if (companyResponse == null) {
				throw new CompanyNotFoundException("Company not found");
			}
			JobOfferResponseDto offerResponse = mapper.fromJobOffer(offer);
			offerResponse.setCompany(companyResponse);
			return offerResponse;
		});
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
		Set<Integer> uniqueOfferIds = new HashSet<>(offerIds);

		// Fetch all job offers based on unique offer IDs
		List<JobOffer> offers = uniqueOfferIds.stream()
				.map(id -> repository.findById(id)
						.orElseThrow(() -> new RuntimeException("Offer with ID " + id + " not found")))
				.collect(Collectors.toList());

		// Extract unique company IDs from the offers
		Set<Integer> companyIds = offers.stream()
				.map(JobOffer::getCompany)
				.collect(Collectors.toSet());

		List<Integer> companyIdsList = new ArrayList<>(companyIds);

		// Fetch the corresponding companies in one call
		List<CompanyResponse> companies = companyClient.findByIds(companyIdsList);

		// Create a map of companyId to CompanyResponse for easy lookup
		Map<Integer, CompanyResponse> companyMap = companies.stream()
				.collect(Collectors.toMap(CompanyResponse::getId, company -> company));

		// Map job offers to JobOfferResponseDto and set the associated company details
		return offers.stream()
				.map(offer -> {
					// Find the company associated with this job offer
					CompanyResponse companyResponse = companyMap.get(offer.getCompany());
					if (companyResponse == null) {
						throw new CompanyNotFoundException("Company with ID " + offer.getCompany() + " not found");
					}

					// Map the job offer to JobOfferResponseDto
					JobOfferResponseDto offerResponse = mapper.fromJobOffer(offer);
					offerResponse.setCompany(companyResponse);  // Set the company details in the response
					return offerResponse;
				})
				.collect(Collectors.toList());
	}

}
