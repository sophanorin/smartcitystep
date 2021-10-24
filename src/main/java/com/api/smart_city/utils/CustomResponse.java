package com.api.smart_city.utils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomResponse {
    @JsonProperty("success")
    private boolean success;
    @JsonProperty("info")
    private List<?> workerList;
    @JsonProperty("message")
    private String message;

    public CustomResponse(boolean success, List<?> info, String message) {
        this.success = success;
        this.workerList = info;
        this.message = message;
    }
}
