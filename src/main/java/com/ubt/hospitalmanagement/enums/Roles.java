package com.ubt.hospitalmanagement.enums;

public enum Roles {

    ADMIN("ADMIN"),
    DOCTOR("DOCTOR"),
    PATIENT("PATIENT");

    private String roles;

    Roles(String role) {
        this.roles = role;
    }
}
