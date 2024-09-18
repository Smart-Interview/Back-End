package com.fstg.JobOfferManagement.company;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@FeignClient(
        name = "company-service",
        url = "${application.config.company-url}"
)
public interface CompanyClient {

    @GetMapping("/{id}")
    Optional<CompanyResponse> findCompanyById(@PathVariable("id") Integer id);

}
