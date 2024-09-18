package com.elabidisoufiane.sosouca.service;

import com.elabidisoufiane.sosouca.dto.CEORequest;
import com.elabidisoufiane.sosouca.dto.CEOResponse;
import com.elabidisoufiane.sosouca.dto.CompanyRequestDto;
import com.elabidisoufiane.sosouca.dto.CompanyResponseDto;
import com.elabidisoufiane.sosouca.model.CEO;
import com.elabidisoufiane.sosouca.model.Company;
import org.springframework.stereotype.Service;

@Service
public class CompanyMapper {

    public Company toCompany(CompanyRequestDto request){
        if (request == null) return null;
        return Company.builder()
                .id(request.getId())
                .name(request.getName())
                .industry(request.getIndustry())
                .location(request.getLocation())
                .build();
    }

    public CompanyResponseDto fromCompany(Company company){
        return new CompanyResponseDto(
                company.getId(),
                company.getName(),
                company.getIndustry(),
                company.getLocation()
        );
    }
}
