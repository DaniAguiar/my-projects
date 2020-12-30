package com.tcc.diagnosticando.domain;

import java.io.Serializable;
import java.util.ArrayList;

public class Question implements Serializable {
    private Long id;
    private String text;
    private ArrayList<String> disease;

    public ArrayList<String> getDisease() {
        return disease;
    }

    public void setDisease(ArrayList<String> disease) {
        this.disease = disease;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text.toString();
    }
}
