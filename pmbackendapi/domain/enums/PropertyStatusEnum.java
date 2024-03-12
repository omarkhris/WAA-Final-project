package com.miu.pmtbackendapi.domain.enums;

public enum PropertyStatusEnum {
    AVAILABLE("AVAILABLE"), PENDING("PENDING"), CONTINGENT("CONTINGENT");

    String value;

    private PropertyStatusEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

//    public String  setValue(String value){
//      return   this.value = value;
//    }
}
