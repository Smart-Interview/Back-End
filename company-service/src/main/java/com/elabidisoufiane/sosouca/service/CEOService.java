package com.elabidisoufiane.sosouca.service;

import com.elabidisoufiane.sosouca.dao.CEORepository;
import com.elabidisoufiane.sosouca.dto.CEORequest;
import com.elabidisoufiane.sosouca.dto.CEOResponse;
import com.elabidisoufiane.sosouca.exception.CEONotFoundException;
import com.elabidisoufiane.sosouca.model.CEO;
import lombok.AllArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CEOService {

    public final CEOMapper mapper;
    public final CEORepository repository;

    public CEOResponse createCEO(CEORequest request) {
        CEO candidate = mapper.toCEO(request);
        CEO saved = repository.save(candidate);
        return mapper.fromCEO(saved);
    }

    public void updateCEO(CEORequest request) {
        var candidate = repository.findById(request.getId())
                .orElseThrow(()-> new CEONotFoundException("Cannot found CEO"));
        mergerCEO(candidate, request);
        repository.save(candidate);
    }

    private void mergerCEO(CEO candidate, CEORequest request){
        if (StringUtils.isNotBlank(request.getUserName())){
            candidate.setUserName(request.getUserName());
        }
        if (StringUtils.isNotBlank(request.getEmail())){
            candidate.setEmail(request.getEmail());
        }
    }

    public List<CEOResponse> getCEOs() {
        return repository.findAll()
                .stream()
                .map(mapper::fromCEO)
                .collect(Collectors.toList());
    }

    public CEOResponse findCEO(Integer id) {
        var candidate = repository.findById(id)
                .orElseThrow(()-> new CEONotFoundException("Cannot found CEO"));
        return mapper.fromCEO(candidate);
    }


    public void deleteCEO(Integer id) {
        var candidate = repository.findById(id)
                .orElseThrow(()-> new CEONotFoundException("Cannot found CEO"));
        repository.delete(candidate);
    }

    public List<CEOResponse> findCEOsByIds(List<Integer> candidateIds) {
        // Remove duplicates by converting the list to a set
        Set<Integer> uniqueCEOIds = new HashSet<>(candidateIds);

        // Stream over the unique IDs and map each to a CEOResponse
        return uniqueCEOIds.stream()
                .map(id -> {
                    CEO candidate = repository.findById(id)
                            .orElseThrow(() -> new CEONotFoundException("CEO with ID " + id + " not found"));
                    return mapper.fromCEO(candidate);
                })
                .collect(Collectors.toList());
    }
}
