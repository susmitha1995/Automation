package com.sunpower.automation.edp.api.enums;

public enum ResponseEnum {

    SUCCESS(200),

    BAD_REQUEST(400),

    UNAUTHORISED(401),

    NOT_FOUND(404);

    private int value;

    private ResponseEnum(int value) {
        this.value = value;
    }

    /**
     * get Value
     *
     * @return
     */
    public int getValue() {
        return value;

    }
}
