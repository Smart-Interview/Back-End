package com.elabidisoufiane.sosouca.service;

import com.elabidisoufiane.sosouca.dao.CompanyRepository;
import com.elabidisoufiane.sosouca.dao.RHRepository;
import com.elabidisoufiane.sosouca.dto.RHRequest;
import com.elabidisoufiane.sosouca.dto.RHResponse;
import com.elabidisoufiane.sosouca.exception.CompanyNotFoundException;
import com.elabidisoufiane.sosouca.model.Company;
import com.elabidisoufiane.sosouca.model.RH;
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
	//private final JavaMailSender emailSender;

    public RHResponse save(RHRequest dto) {
		RH rh = mapper.toRH(dto);
		Company company = companyRepository
				.findById(dto.getCompany()).orElseThrow(()-> new CompanyNotFoundException("No Company with this ID"));
		rh.setCompany(company);
		//sendEmail(dto.getEmail(), dto.getCode());
		RH savedRH = rhRepository.save(rh);
		return mapper.fromRH(savedRH);
	}

//	private void sendEmail(String to, String code) {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setTo(to);
//        message.setSubject("Your RH Code");
//        message.setText("Hello, \n\nYour code is: " + code);
//        emailSender.send(message);
//    }

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
