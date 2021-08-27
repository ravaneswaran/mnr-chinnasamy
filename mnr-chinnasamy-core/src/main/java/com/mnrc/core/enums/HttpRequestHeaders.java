package com.mnrc.core.enums;

public enum HttpRequestHeaders {

    CONTENT_TYPE("Content-Type");

    private String value;

    HttpRequestHeaders(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
