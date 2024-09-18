package com.elabidisoufiane.sosouca.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.elabidisoufiane.sosouca.dao.CEORepository;
import com.elabidisoufiane.sosouca.exception.CEONotFoundException;
import com.elabidisoufiane.sosouca.model.CEO;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.elabidisoufiane.sosouca.dao.CompanyRepository;
import com.elabidisoufiane.sosouca.dto.CompanyRequestDto;
import com.elabidisoufiane.sosouca.dto.CompanyResponseDto;
import com.elabidisoufiane.sosouca.model.Company;

import jakarta.persistence.EntityNotFoundException;

@Service
@AllArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyMapper mapper;
    private final CEORepository ceoRepository;

    public Integer save(CompanyRequestDto requestDto) {
        Company company = mapper.toCompany(requestDto);
        CEO ceo = ceoRepository.findById(requestDto.getCeo())
                .orElseThrow(()-> new CEONotFoundException("No CEO with this ID"));
        company.setCeo(ceo);
        return companyRepository.save(company).getId();
    }

    public List<CompanyResponseDto> findAll(Integer id) {
        ceoRepository.findById(id).orElseThrow(()-> new CEONotFoundException("No CEO with this ID"));

        return companyRepository.findByCeoId(id).stream()
                .map(mapper::fromCompany)
                .collect(Collectors.toList());
    }

    public CompanyResponseDto findById(Integer id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("company not found"));
        return mapper.fromCompany(company);
    }


    public void delete(Integer id) {
        companyRepository.deleteById(id);
    }

    public CompanyResponseDto update(CompanyRequestDto dto, Integer id) throws NotFoundException {
        Optional<Company> offer = companyRepository.findById(id);
        if (offer.isPresent()) {
            Company company = mapper.toCompany(dto);
            company.setId(id);
            Company updated = companyRepository.save(company);
            return mapper.fromCompany(updated);
        } else {
            throw new EntityNotFoundException("offer not found");
        }
    }

}
