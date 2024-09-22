package com.elabidisoufiane.sosouca.service;

import com.elabidisoufiane.sosouca.dao.CompanyRepository;
import com.elabidisoufiane.sosouca.dao.RHRepository;
import com.elabidisoufiane.sosouca.dto.RHRequest;
import com.elabidisoufiane.sosouca.dto.RHResponse;
import com.elabidisoufiane.sosouca.email.EmailService;
import com.elabidisoufiane.sosouca.exception.CompanyNotFoundException;
import com.elabidisoufiane.sosouca.model.Company;
import com.elabidisoufiane.sosouca.model.RH;
import com.elabidisoufiane.sosouca.password.PasswordGenerator;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RHService {

	private final RHRepository rhRepository;
	private final CompanyRepository companyRepository;
	private final RHMapper mapper;
	private final EmailService emailService;

    public RHResponse save(RHRequest dto) throws MessagingException {
		RH rh = mapper.toRH(dto);
		Company company = companyRepository
				.findById(dto.getCompany()).orElseThrow(()-> new CompanyNotFoundException("No Company with this ID"));
		rh.setCompany(company);
		rh.setCode(PasswordGenerator.generateRandomPassword());
		emailService.sendAccountCreationEmail(rh.getEmail(), rh.getUserName(), rh.getCode(), rh.getCompany().getName(), rh.getCompany().getLocation());
		RH savedRH = rhRepository.save(rh);
		return mapper.fromRH(savedRH);
	}

	public List<RHResponse> getAllRHByCompany(Integer id) {
		companyRepository.findById(id)
				.orElseThrow(()-> new CompanyNotFoundException("No Company with this ID"));

		return rhRepository.findByCompanyId(id).stream()
				.map(mapper::fromRH)
				.collect(Collectors.toList());
	}

    public RHResponse getRHByEmail(String email) {
		RH rh = rhRepository.findByEmail(email);
		return mapper.fromRH(rh);
    }
}
