package com.saidworks.backend.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EntityScan("com.saidworks.backend.domain")
@EnableJpaRepositories("com.saidworks.backend.repos")
@EnableTransactionManagement
public class DomainConfig {
}
