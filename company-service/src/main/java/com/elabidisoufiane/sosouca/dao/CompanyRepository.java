package com.elabidisoufiane.sosouca.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.elabidisoufiane.sosouca.model.Company;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
	List<Company> findByCeoId(Integer id);
}
