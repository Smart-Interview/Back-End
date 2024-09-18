package com.elabidisoufiane.sosouca.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.elabidisoufiane.sosouca.dao.companydao;
import com.elabidisoufiane.sosouca.dto.CompanyRequestDto;
import com.elabidisoufiane.sosouca.dto.CompanyResponseDto;
import com.elabidisoufiane.sosouca.model.Company;

import jakarta.persistence.EntityNotFoundException;

@Service()
public class CompanyImpl implements CompanyService {

    private companydao dao;
    private ModelMapper mapper;

    public CompanyImpl(companydao dao, ModelMapper mapper) {
        this.dao = dao;
        this.mapper = mapper;
    }

    @Override
    public CompanyResponseDto save(CompanyRequestDto dto) {
        // Step 1: Map DTO to entity
        Company company = mapper.map(dto, Company.class);

        Company savedCompany = dao.save(company);

        return mapper.map(savedCompany, CompanyResponseDto.class);
    }

    @Override
    public CompanyResponseDto findById(Integer id) {

        Company company = dao.findById(id).orElseThrow(() -> new EntityNotFoundException("company not found"));

        return mapper.map(company, CompanyResponseDto.class);

    }

    @Override
    public CompanyResponseDto findByName(String name) {
        Company offer = dao.findByName(name);
        return mapper.map(offer, CompanyResponseDto.class);

    }

    /*
     * @Override
     * public JobOfferResponseDto findByRecruiter(Integer Rec) {
     * JobOffer offer = dao.findByRecruiter(Rec);
     * return mapper.map(offer,JobOfferResponseDto.class);
     * }
     */
    @Override
    public void delete(Integer id) {
        dao.deleteById(id);
    }

    @Override
    public CompanyResponseDto update(CompanyRequestDto dto, Integer id) throws NotFoundException {
        Optional<Company> offer = dao.findById(id);
        if (offer.isPresent()) {
            Company company = mapper.map(dto, Company.class);
            company.setId(id);
            Company updated = dao.save(company);
            return mapper.map(updated, CompanyResponseDto.class);
        } else {
            throw new EntityNotFoundException("offer not found");
        }

    }

    @Override
    public List<CompanyResponseDto> findAll() {
        return dao.findAll().stream()
                .map(el -> mapper
                        .map(el, CompanyResponseDto.class))
                .collect(Collectors.toList());
    }

}
