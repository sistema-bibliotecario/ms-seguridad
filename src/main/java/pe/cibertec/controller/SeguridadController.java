package pe.cibertec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import pe.cibertec.dto.SeguridadRequest;
import pe.cibertec.model.UserCredential;
import pe.cibertec.service.AuthService;

@RequestMapping("/ms-seguridad")
@RestController
public class SeguridadController {

    @Autowired
    private AuthService authService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public String addNewUser(@RequestBody UserCredential user) {
        return authService.saveUser(user);
    }

    @PostMapping("/token")
    public String getToken(@RequestBody SeguridadRequest request) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        if (authentication.isAuthenticated()) {
            return authService.generateToken(request.getUsername());
        }else {
            throw new RuntimeException("Token invalido");
        }
    }

    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token) {
        authService.validateToken(token);
        return "Token validado";
    }
}
