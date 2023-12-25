package com.miics.server.dao.models;

//import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public enum Role {
    TEACHER(Set.of(Permission.PERMISSION_WRITE, Permission.PERMISSION_READ)),
    STUDENT(Set.of(Permission.PERMISSION_READ)),
    ADMIN(Set.of(Permission.PERMISSION_READ, Permission.PERMISSION_WRITE));

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public List<SimpleGrantedAuthority> grantedAuthorities() {
        return getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
    }
}
