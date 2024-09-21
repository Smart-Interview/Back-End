package com.elabidisoufiane.sosouca.dao;

import com.elabidisoufiane.sosouca.model.RH;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface RHRepository extends JpaRepository<RH, Integer> {
    List<RH> findByCompanyId(Integer id);
    RH findByEmail(String email);
}
