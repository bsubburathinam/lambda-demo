package com.example.demo.persistence;

import com.example.demo.entity.CaseEntity;
import org.springframework.data.repository.CrudRepository;

public interface CaseRepository extends CrudRepository<CaseEntity, Long> {
}
