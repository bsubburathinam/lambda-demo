package com.example.demo.service.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import com.example.demo.model.Case;
import com.example.demo.service.CaseService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Service
public class CaseS3ServiceImpl implements CaseService {

    private final ObjectMapper objectMapper;

    private final AmazonS3 s3Client;

    public CaseS3ServiceImpl(ObjectMapper objectMapper, AmazonS3 s3Client) {
        this.objectMapper = objectMapper;
        this.s3Client = s3Client;
    }

    public Optional<Case> fetchCase(String bucketName, String objectKey) {
        S3Object s3Object = s3Client.getObject(bucketName, objectKey);
        try (BufferedReader reader = getReader(s3Object)) {
            Case newCase = objectMapper.readValue(reader, Case.class);
            return Optional.of(newCase);
        } catch (IOException e) {
            return Optional.empty();
        }
    }

    private static BufferedReader getReader(S3Object s3Object) {
        return new BufferedReader(new InputStreamReader(s3Object.getObjectContent(), StandardCharsets.UTF_8));
    }
}
