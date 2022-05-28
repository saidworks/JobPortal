package com.saidworks.backend.domain;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class JobApplication {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String user;

    @Column
    private String jobListing;

    @Column
    private LocalDate applicationDate;

    @Column
    private String resumeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_listings_id")
    private JobListings jobListings;

    @OneToOne(mappedBy = "jobApplication", fetch = FetchType.LAZY)
    private User jobApplication;

}
