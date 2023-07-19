package com.example.demo.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "insurance_case")
public class CaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "case_seq_generator")
    @SequenceGenerator(name = "case_seq_generator", sequenceName = "case_seq", allocationSize = 1)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "insuranceCase", cascade = CascadeType.ALL)
    private List<PlanEntity> plans;

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

    public List<PlanEntity> getPlans() {
        return plans;
    }

    public void setPlans(List<PlanEntity> plans) {
        this.plans = plans;
    }
}
