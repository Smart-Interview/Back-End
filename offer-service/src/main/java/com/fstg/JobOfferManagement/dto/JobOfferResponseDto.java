package com.fstg.JobOfferManagement.dto;

import com.fstg.JobOfferManagement.company.CompanyResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobOfferResponseDto {
	private Integer id;
	private String title;
	private CompanyResponse company;
	private LocalDate deadline;
}
