package com.codedifferently.cdbankapi.domain.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Medium {

    @JsonProperty("Balance")
    BALANCE,
    @JsonProperty("Rewards")
    REWARDS;

    Medium() {

    }

    private String medium;

    Medium(String medium) {
        this.medium = medium;
    }

    public String getMedium() {
        return medium;
    }
}