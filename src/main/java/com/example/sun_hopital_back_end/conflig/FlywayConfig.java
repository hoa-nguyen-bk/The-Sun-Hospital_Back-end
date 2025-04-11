package com.example.sun_hopital_back_end.conflig;


import org.springframework.beans.factory.annotation.Value;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.flywaydb.core.Flyway;


@Configuration
public class FlywayConfig {

    @Value("$spring.flyway.locations")
    private String locations;
    @Value("$spring.datasource.url")
    private String dataUrl;

    @Value("$spring.datasource.username")
    private String dataUsername;
    @Value("$spring.datasource.password")
    private String dataPassword;

//    @Bean
//    public Flyway flyway(){
//        Flyway flyway = Flyway.configure()
//                .dataSource(DataSource))
//
//    }


}
