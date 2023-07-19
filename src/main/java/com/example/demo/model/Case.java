package com.example.demo.model;

import java.util.List;

public class Case {
    private String name;

    private List<Plan> plans;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Plan> getPlans() {
        return plans;
    }

    public void setPlans(List<Plan> plans) {
        this.plans = plans;
    }

    @Override
    public String toString() {
        return "Case{" +
            "name='" + name + '\'' +
            ", plans=" + plans +
            '}';
    }
}
