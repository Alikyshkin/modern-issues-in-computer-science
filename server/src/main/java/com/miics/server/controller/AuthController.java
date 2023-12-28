package com.miics.server.controller;

import ch.qos.logback.core.model.Model;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//@Controller
//public class AuthController {
//    @GetMapping("/login")
//    public String showLoginForm(Model model) {
//
//        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
//        if(authentication==null || authentication instanceof AnonymousAuthenticationToken) {
//            return "login";
//        }
//        return "redirect:/";
//    }
//
//    @GetMapping("/")
//    public String home() {
//        return "home";
//    }
//
//    @GetMapping("/logout")
//	public String handleLogoutRequest(HttpServletRequest requests) {
//
//		requests.getSession().invalidate();
//		return "redirect:/login";
//	}
//}
