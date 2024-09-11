package com.fstg.JobOfferManagement.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;



import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.fstg.JobOfferManagement.dto.JobOfferRequestDto;
import com.fstg.JobOfferManagement.dto.JobOfferResponseDto;
import com.fstg.JobOfferManagement.service.JobOfferService;
import com.fstg.JobOfferManagement.service.FileStorageService;


import jakarta.validation.Valid;

import java.nio.file.Files;
import java.io.File;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

@RestController
@RequestMapping("/api/v1/offers")
public class JobOfferController {

	private final  FileStorageService fileStorageService;
	private final JobOfferService service ;
	
	public JobOfferController(JobOfferService service,FileStorageService fileStorageService) {
		this.service= service ;
		this.fileStorageService=fileStorageService;
	}
	
	@GetMapping
	public ResponseEntity< List<JobOfferResponseDto> > getJobOffers(){
		//return service.findAll();	
		return new ResponseEntity<> (service.findAll(),HttpStatus.OK) ;
	}

	@PostMapping
	public ResponseEntity<JobOfferResponseDto> save(
        @RequestParam("pdfFile") MultipartFile file,
        @Valid @ModelAttribute JobOfferRequestDto request) {
    
    // Call the service to save the job offer details
    JobOfferResponseDto dto = service.save(request);
    
    try {
        // Call the file storage service to save the uploaded file
        fileStorageService.saveDescriptionToFile(file, dto.getId());
    } catch (IOException e) {
        // Handle file storage exceptions
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    return new ResponseEntity<>(dto, HttpStatus.CREATED);
}
	
	@PutMapping("/update/{id}")
    public ResponseEntity<JobOfferResponseDto> update(@Valid @RequestBody JobOfferRequestDto produitDto,@PathVariable Integer id) throws NotFoundException {
		JobOfferResponseDto dto = service.update(produitDto, id);
        return ResponseEntity.accepted().body(dto);
    }
	
	@GetMapping("/id/{id}")
	public ResponseEntity<JobOfferResponseDto> findById(@PathVariable Integer id) {
		JobOfferResponseDto dto = service.findById(id) ;
		return ResponseEntity.ok(dto);
	}
	
	@GetMapping("/title/{title}")
	public JobOfferResponseDto findByTitle(@PathVariable String title) {
		return service.findByTitle(title);
	}
	
	
	/*@GetMapping("/Offers/recruiter/{id}")
	public JobOfferResponseDto findByRecruiter(@PathVariable Integer id) {
		return service.findByRecruiter(id);
	}
	*/
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delet(@PathVariable Integer id) {
		service.delete(id);
        return ResponseEntity.noContent().build();
	}

	@GetMapping("/file/{id}")
public ResponseEntity<byte[]> getJobOfferFileById(@PathVariable Integer id) throws IOException {
    // Define the path to the PDF file based on the provided ID
    String filePath = "/app/jobOffersDescriptions/jobOffer_" + id + ".pdf";
    File file = new File(filePath);

    // Check if the file exists
    if (!file.exists()) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    try {
        // Read the PDF file content into a byte array
        byte[] fileContent = Files.readAllBytes(file.toPath());

        // Create HTTP headers for the response
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=jobOffer_" + id + ".pdf");
        headers.setContentType(MediaType.APPLICATION_PDF);

        // Return the PDF file content with appropriate headers
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .body(fileContent);
    } catch (IOException e) {
        // Handle the exception and return a server error status
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}



	
}
