package com.elabidisoufiane.sosouca.service;

import com.elabidisoufiane.sosouca.dto.CEORequest;
import com.elabidisoufiane.sosouca.dto.CEOResponse;
import com.elabidisoufiane.sosouca.model.CEO;
import org.springframework.stereotype.Service;

@Service
public class CEOMapper {

    public CEO toCEO(CEORequest request){
        if (request == null) return null;
        return CEO.builder()
                .id(request.getId())
                .userName(request.getUserName())
                .email(request.getEmail())
                .build();
    }

    public CEOResponse fromCEO(CEO candidate){
        return new CEOResponse(
                candidate.getId(),
                candidate.getUserName(),
                candidate.getEmail()
        );
    }
}
