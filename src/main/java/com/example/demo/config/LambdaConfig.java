package com.example.demo.config;

import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.lambda.runtime.events.models.s3.S3EventNotification;
import com.example.demo.entity.CaseEntity;
import com.example.demo.entity.PlanEntity;
import com.example.demo.model.Case;
import com.example.demo.model.Plan;
import com.example.demo.persistence.CaseRepository;
import com.example.demo.service.CaseService;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class LambdaConfig {
    private final CaseService caseService;

    private final CaseRepository caseRepository;

    public LambdaConfig(CaseService caseService, CaseRepository caseRepository) {
        this.caseService = caseService;
        this.caseRepository = caseRepository;
    }

    @Bean
    public Function<S3Event, Boolean> processS3ObjectPut() {
        return
            (s3Event) -> {
                s3Event.getRecords().forEach(this::processS3Event);
                return Boolean.TRUE;
            };
    }

    private void processS3Event(S3EventNotification.S3EventNotificationRecord s3EventNotificationRecord) {
        var bucketName = s3EventNotificationRecord.getS3().getBucket().getName();
        var objectKey = s3EventNotificationRecord.getS3().getObject().getKey();
        caseService
            .fetchCase(bucketName, objectKey)
            .map(this::buildCaseEntity)
            .ifPresent(caseRepository::save);
    }

    private CaseEntity buildCaseEntity(Case casePojo) {
        CaseEntity caseEntity = new CaseEntity();
        caseEntity.setName(casePojo.getName());
        caseEntity.setPlans(
            casePojo
                .getPlans()
                .stream()
                .map(planPojo -> buildPlanEntity(planPojo, caseEntity))
                .collect(Collectors.toList())
        );
        return caseEntity;
    }

    private PlanEntity buildPlanEntity(Plan planPojo, CaseEntity caseEntity) {
        PlanEntity planEntity = new PlanEntity();
        planEntity.setName(planPojo.getName());
        planEntity.setStatus(planPojo.getStatus());
        planEntity.setCase(caseEntity);
        return planEntity;
    }

}
