package com.miics.server.controller;

import com.miics.server.dao.dto.UserDto;
import com.miics.server.dao.models.User;
import com.miics.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.register(userDto));
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.login(userDto));
    }

    @GetMapping("/{userId}")
    @PreAuthorize("hasAuthority('access:read')")
    public ResponseEntity<UserDto> showUser(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @GetMapping("")
    @PreAuthorize("hasAuthority('access:read')")
    public List<ResponseEntity<UserDto>> showUsers() {
        return ResponseEntity.of(userService.getUsers());
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('access:write')")
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.addUser(userDto));
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('access:write')")
    public ResponseEntity<UserDto> deleteUser(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.deleteUser(userDto));
    }

    @DeleteMapping("/delete/{userId}")
    @PreAuthorize("hasAuthority('access:write')")
    public ResponseEntity<UserDto> deleteUserById(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(userService.deleteUserById(userId));
    }
}
