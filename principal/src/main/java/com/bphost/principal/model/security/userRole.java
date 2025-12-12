package com.bphost.principal.model.security;

public enum userRole {
    USER("user"),
    ADMIN("admin");

    private String role;

    userRole(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}
