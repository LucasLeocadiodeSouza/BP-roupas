package com.bphost.principal.controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bphost.principal.infra.security.tokenService;
import com.bphost.principal.model.user_account;
import com.bphost.principal.model.security.AuthenticationDTO;
import com.bphost.principal.model.security.loginResponseDTO;
import com.bphost.principal.model.security.registerDTO;
import com.bphost.principal.repository.user_accountRepo;
import jakarta.validation.Valid;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/auth")
public class authentController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private user_accountRepo userRepository;

    @Autowired
    private tokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthenticationDTO data, HttpServletResponse response) {
        var userNamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.passkey());
        var auth = this.authenticationManager.authenticate(userNamePassword);
        var token = tokenService.generatedToken((user_account) auth.getPrincipal());

        Cookie cookie = new Cookie("authToken", token);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/"); //disponivel em todo dominio
        cookie.setMaxAge(1 * 24 * 60 * 60); //expira em 1 dia
        response.addCookie(cookie);

        return ResponseEntity.ok(new loginResponseDTO(token));
    }   

    @PostMapping("/logout")
    public ResponseEntity logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("authToken", "");
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge(0); //expira imaediatamente
        response.addCookie(cookie);

        return ResponseEntity.ok("OK");
    }   

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid registerDTO data) {
        if(this.userRepository.findByUsername(data.login()) != null) return ResponseEntity.badRequest().build();

        String encryptedPasskey = new BCryptPasswordEncoder().encode(data.passkey());
        user_account user            = new user_account(data.login(), encryptedPasskey, data.role());
        userRepository.save(user);

        return ResponseEntity.ok().build();
    } 
}
