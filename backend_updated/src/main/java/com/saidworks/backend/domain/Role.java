package com.saidworks.backend.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.saidworks.backend.model.Roles;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import javax.persistence.*;


@Entity
@Table(name = "\"role\"")
@Getter
@Setter
@NoArgsConstructor
public class Role {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Roles roleName;
    @JsonBackReference
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

}
