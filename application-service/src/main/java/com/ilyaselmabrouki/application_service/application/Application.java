package com.ilyaselmabrouki.application_service.application;

import com.ilyaselmabrouki.application_service.file.File;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Application {
    @Id @GeneratedValue
    private Integer id;
    private Integer candidateId;
    private Integer offerId;
    @Enumerated(EnumType.STRING)
    private ApplicationStatus status;
    @OneToOne
    @JoinColumn(name = "cv_id", referencedColumnName = "id")
    private File cv;
    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;
    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime lastModifiedDate;
}
