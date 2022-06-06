package com.saidworks.backend.service;

import javax.transaction.Transactional;

import com.saidworks.backend.domain.User;
import com.saidworks.backend.model.CustomUserBean;
import com.saidworks.backend.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with "
                        + "user name "+ username + " not found"));
        return CustomUserBean.createInstance(user);
    }
}