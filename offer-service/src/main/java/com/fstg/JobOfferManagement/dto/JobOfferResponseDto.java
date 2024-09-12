package com.fstg.JobOfferManagement.dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobOfferResponseDto {
	
	private Integer id ;
	
	private String title;
	
	private String descriptionFilePath;  // File path for the description

	private String requirements ;
	
	private Integer recruiter ;

	public String getDescriptionFilePath() {
		return descriptionFilePath;
	}

	public void setDescriptionFilePath(String requirements) {
		this.descriptionFilePath = requirements;
	}

	public String getRequirements() {
		return requirements;
	}

	public void setRequirements(String requirements) {
		this.requirements = requirements;
	}

	public Integer getRecruiter() {
		return recruiter;
	}

	public void setRecruiter(Integer recruiter) {
		this.recruiter = recruiter;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	
}
