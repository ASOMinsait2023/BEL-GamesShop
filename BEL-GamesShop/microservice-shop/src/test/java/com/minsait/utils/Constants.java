package com.minsait.utils;

public enum Constants {
    URL_SHOP("/api/v1/shop"),
    URL_STOCK("/api/v1/stock"),
    CONTENT_TYPE("application/json");
    String value;

    private Constants(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
