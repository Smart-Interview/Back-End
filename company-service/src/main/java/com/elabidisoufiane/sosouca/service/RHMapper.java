package com.elabidisoufiane.sosouca.service;

import com.elabidisoufiane.sosouca.dto.CompanyRequestDto;
import com.elabidisoufiane.sosouca.dto.CompanyResponseDto;
import com.elabidisoufiane.sosouca.dto.RHRequest;
import com.elabidisoufiane.sosouca.dto.RHResponse;
import com.elabidisoufiane.sosouca.model.Company;
import com.elabidisoufiane.sosouca.model.RH;
import org.springframework.stereotype.Service;

@Service
public class RHMapper {

    public RH toRH(RHRequest request){
        if (request == null) return null;
        return RH.builder()
                .id(request.getId())
                .userName(request.getUserName())
                .email(request.getEmail())
                .code(request.getCode())
                .build();
    }

    public RHResponse fromRH(RH rh){
        return new RHResponse(
                rh.getId(),
                rh.getUserName(),
                rh.getEmail(),
                rh.getCompany().getId()
        );
    }
}
