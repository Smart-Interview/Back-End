package com.elabidisoufiane.sosouca.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyRequestDto {

    @NotBlank(message = "Company name is required")
    @Size(min = 3, message = "The name must have at least 3 characters")
    @Size(max = 100, message = "The name must not exceed 100 characters")
    private String name;

    @NotBlank(message = "Industry is required")
    @Size(min = 3, message = "The industry must have at least 3 characters")
    @Size(max = 50, message = "The industry must not exceed 50 characters")
    private String industry;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String mail;

    @NotBlank(message = "Address is required")
    @Size(min = 10, message = "The address must have at least 10 characters")
    @Size(max = 200, message = "The address must not exceed 200 characters")
    private String address;

    // Getters and setters for validation purposes
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String email) {
        this.mail = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
