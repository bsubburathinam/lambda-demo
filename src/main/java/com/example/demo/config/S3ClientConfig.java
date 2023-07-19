package com.example.demo.config;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class S3ClientConfig {

    @Bean
    public AmazonS3 s3Client() {
        return
            AmazonS3Client
                .builder()
                .withCredentials(new DefaultAWSCredentialsProviderChain())
                .build();
    }

}
