package com.ilyaselmabrouki.application_service.offer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyResponse {
	private Integer id;
	private String name;
	private String industry;
	private String location;
}
