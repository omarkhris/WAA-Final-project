package com.miu.pmtbackendapi.domain.enums;

public enum HomeConditionEnum {
    
    PERFECT("PERFECT"), EXCELLENT("EXCELLENT"), GOOD("GOOD"), NEED_MINOR_WORK("NEED_MINOR_WORK"), NEED_MAJOR_WORK("NEED_MAJOR_WORK");

    String value;

    private HomeConditionEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
