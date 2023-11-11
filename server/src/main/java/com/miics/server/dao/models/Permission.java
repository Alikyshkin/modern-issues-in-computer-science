package com.miics.server.dao.models;

import lombok.Getter;

@Getter
public enum Permission {
    PERMISSION_READ("access:read"),
    PERMISSION_WRITE("access:write");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

}
