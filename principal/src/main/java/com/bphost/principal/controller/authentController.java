package com.bphost.principal.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bphost.principal.infra.security.tokenService;
import com.bphost.principal.model.user_account;
import com.bphost.principal.model.security.loginResponseDTO;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/auth")
public class authentController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private tokenService tokenService;

    @GetMapping("/me")
    public ResponseEntity<?> me(Authentication authentication) {
        if (authentication == null ||
            !authentication.isAuthenticated() ||
            authentication.getPrincipal().equals("anonymousUser")) {

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        UserDetails user = (UserDetails) authentication.getPrincipal();

        return ResponseEntity.ok(
            Map.of(
                "username", user.getUsername(),
                "roles", user.getAuthorities()
            )
        );
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody user_account userAccount,
                                   HttpServletResponse response) {

        var userNamePassword = new UsernamePasswordAuthenticationToken(userAccount.getUsername(), userAccount.getPassword());
        var auth = this.authenticationManager.authenticate(userNamePassword);
        var token = tokenService.generatedToken((user_account) auth.getPrincipal());

        Cookie cookie = new Cookie("authToken", token);
        cookie.setHttpOnly(true);
        cookie.setSecure(false);
        cookie.setPath("/"); //disponivel em todo dominio
        cookie.setMaxAge(1 * 24 * 60 * 60); //expira em 1 dia
        response.addCookie(cookie);

        return ResponseEntity.ok(new loginResponseDTO(token));
    }   

    @PostMapping("/logout")
    public ResponseEntity logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("authToken", "");
        cookie.setHttpOnly(true);
        cookie.setSecure(false);
        cookie.setPath("/");
        cookie.setMaxAge(0); //expira imaediatamente
        response.addCookie(cookie);

        return ResponseEntity.ok("OK");
    }
}
