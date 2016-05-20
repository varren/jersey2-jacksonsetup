package ru.varren.model;

import com.fasterxml.jackson.annotation.JsonProperty;


public interface TestBeanMixin {
    @JsonProperty("n")
    String getName();

    @JsonProperty("MyTestId")
    int getId();
}
