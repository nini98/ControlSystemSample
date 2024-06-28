package com.example.controlsystemsample.enums;

public enum UserStatus {
    ACTIVE("00", "활성화"),
    INACTIVE("01", "비활성화"),
    SUSPENDED("90", "일시정지"),
    DELETED("99", "삭제");

    private final String code;
    private final String description;

    UserStatus(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
