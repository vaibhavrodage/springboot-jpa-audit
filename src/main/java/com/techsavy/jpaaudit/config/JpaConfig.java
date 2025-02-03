package com.techsavy.jpaaudit.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.envers.repository.support.EnversRevisionRepositoryFactoryBean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaAuditing
@Configuration
@EnableJpaRepositories(value = "com.techsavy", repositoryFactoryBeanClass = EnversRevisionRepositoryFactoryBean.class)
public class JpaConfig {

}

