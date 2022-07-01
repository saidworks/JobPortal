package com.saidworks.backend.domain;

import java.util.Set;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;


@Entity
@Getter
@Setter
@NoArgsConstructor

@JsonIgnoreProperties({ "roles", "candidateJobApplications", })
public class User {

    public User( String email, String password, String username, String firstName, String lastName, String course, String campus) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.course = course;
        this.campus = campus;
    }

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String username;

    @Column(nullable = true)
    private String firstName;

    @Column(nullable = true)
    private String lastName;

    @Column(nullable = true)
    private String course;

    @Column
    private String campus;
    @JsonManagedReference
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;
    @JsonManagedReference
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "resume_id")
    private Resume resume;
    @JsonManagedReference
    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.DETACH)
    @JoinTable(
            schema = "jobportalonprogress",name = "user_role",
            joinColumns ={ @JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")}
    )
    private Set<Role> roles;
    @JsonManagedReference
    @OneToMany(mappedBy = "candidate",cascade = CascadeType.ALL)
    private Set<JobApplication> candidateJobApplications;

}
