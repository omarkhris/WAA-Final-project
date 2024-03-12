package com.miu.pmtbackendapi.domain.enums;

public enum UserStatusEnum {
    ACTIVE("ACTIVE"), DEACTIVE("DEACTIVE");

    String value;

    private UserStatusEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value.toUpperCase();
    }
}
