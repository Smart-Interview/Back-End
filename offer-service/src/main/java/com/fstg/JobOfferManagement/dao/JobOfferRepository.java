package com.fstg.JobOfferManagement.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.fstg.JobOfferManagement.model.JobOffer;

public interface JobOfferRepository extends JpaRepository<JobOffer,Integer>{

   Page<JobOffer> findByCompany(Integer id, Pageable pageable);
}
