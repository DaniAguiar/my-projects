package com.tcc.diagnosticando.domain;

import java.io.Serializable;

public class Diagnosis implements Serializable {
    private Long id;
    private String disease;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    @Override
    public String toString() {
        return disease.toString();
    }
}
