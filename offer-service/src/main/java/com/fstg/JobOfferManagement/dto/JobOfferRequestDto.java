package com.fstg.JobOfferManagement.dto;


import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class JobOfferRequestDto {
	
	private Integer id;

	@NotBlank(message = "Title is required")
	private String title;
	
    @NotNull(message = "PDF file is required")
    private MultipartFile pdfFile;
	
	@NotNull(message = "Company ID is required")
	private Integer company;

	private LocalDate deadline;
}
