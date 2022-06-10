package com.saidworks.backend.model;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.saidworks.backend.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class CustomUserBean implements UserDetails {
    private static final long serialVersionUID = -4709084843450077569L;
    private Long id;
    private String email;

    private String username;

    private String firstName;

    private String lastName;

    private String course;

    private String campus;

    @JsonIgnore
    private String password;
    private Collection<? extends GrantedAuthority> authorities;
    CustomUserBean(Long id, String username, String email,
                   String password, String firstName,String lastName,
                   String course,String campus,
                   Collection<? extends GrantedAuthority> authorities){
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.course = course;
        this.campus = campus;
        this.authorities = authorities;
    }

    public static CustomUserBean createInstance(User user) {
        List<GrantedAuthority> authorities = user.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleName().name()))
                .collect(Collectors.toList());
        return new CustomUserBean(user.getId(), user.getUsername(),
                user.getEmail(), user.getPassword(),user.getCampus(),user.getFirstName(),
                user.getLastName(),user.getCourse()
                , authorities);
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCourse() {
        return course;
    }

    public String getCampus() {
        return campus;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    @Override
    public boolean equals(Object rhs) {
        if (rhs instanceof CustomUserBean) {
            return username.equals(((CustomUserBean) rhs).username);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return username.hashCode();
    }
}