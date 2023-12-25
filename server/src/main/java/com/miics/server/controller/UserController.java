package com.miics.server.controller;

import com.miics.server.dao.dto.ResultDto;
import com.miics.server.dao.dto.TeacherResultDto;
import com.miics.server.dao.dto.UserDto;
import com.miics.server.dao.dto.UserResultDto;
import com.miics.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
//    @PreAuthorize("hasAuthority('access:write')")
    public ResponseEntity<UserDto> register(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.register(userDto));
    }

//    @PostMapping("/login")
//    public ResponseEntity<UserDto> login(@RequestBody UserDto userDto) {
//        return ResponseEntity.ok(userService.login(userDto));
//    }

    @GetMapping("/{userId}")
    //@PreAuthorize("hasAuthority('access:read')")
    public ResponseEntity<UserDto> showUser(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @GetMapping("")
    //@PreAuthorize("hasAuthority('access:read')")
    public ResponseEntity<List<UserDto>> showUsers() {
        List<UserDto> users = userService.getUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{userId}/page")
    public ResponseEntity<UserResultDto> getUserResults(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(userService.getUserPage(userId));
    }

    @GetMapping("/{userId}/results/{testId}")
    public ResponseEntity<ResultDto> getTestResultForUser(@PathVariable("userId") Long userId, @PathVariable("testId") Long testId) {
        return ResponseEntity.ok(userService.getTestResultForUserByTestIdAndUserId(testId, userId));
    }

    @GetMapping("/results/{testId}")
    public ResponseEntity<TeacherResultDto> getTestResultsForTeacher(@PathVariable("testId") Long testId) {
        return ResponseEntity.ok(userService.getTestResultsForTeacherById(testId));
    }

    @DeleteMapping("/delete")
    //@PreAuthorize("hasAuthority('access:write')")
    public ResponseEntity<UserDto> deleteUser(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.deleteUser(userDto));
    }

    @DeleteMapping("/delete/{userId}")
    //@PreAuthorize("hasAuthority('access:write')")
    public ResponseEntity<UserDto> deleteUserById(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(userService.deleteUserById(userId));
    }
}
