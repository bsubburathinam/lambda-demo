package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "plan")
public class PlanEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "plan_seq_generator")
    @SequenceGenerator(name = "plan_seq_generator", sequenceName = "plan_seq", allocationSize = 1)
    private Long id;

    private String name;

    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "case_id", nullable = false)
    private CaseEntity insuranceCase;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public CaseEntity getCase() {
        return insuranceCase;
    }

    public void setCase(CaseEntity insuranceCase) {
        this.insuranceCase = insuranceCase;
    }
}
