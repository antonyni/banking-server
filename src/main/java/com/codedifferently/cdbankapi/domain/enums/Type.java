package com.codedifferently.cdbankapi.domain.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Type {

    @JsonProperty("Savings")
    SAVINGS,
    @JsonProperty("Checkings")
    CHECKINGS,
    @JsonProperty("Credit")
    CREDIT;

    Type() {

    }

    private String type;

    Type(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }



}