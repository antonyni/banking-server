package com.codedifferently.cdbankapi.domain.account;

import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class BaseControllerTest {
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}