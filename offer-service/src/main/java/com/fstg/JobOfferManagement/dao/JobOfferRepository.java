package com.fstg.JobOfferManagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.fstg.JobOfferManagement.model.JobOffer;

@Repository
public interface JobOfferRepository extends JpaRepository<JobOffer,Integer>{
	
   JobOffer findByTitle(String title) ;
   //JobOffer findByRecruiter(Integer recruiter) ;
   
	
}
