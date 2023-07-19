package com.example.demo.service;

import com.example.demo.model.Case;

import java.util.Optional;

public interface CaseService {
    Optional<Case> fetchCase(String caseContainerId, String caseId);
}
