package pe.cibertec.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pe.cibertec.model.UserCredential;
import pe.cibertec.repository.IUserCredentialRepository;

@Service
@Slf4j
public class AuthService {
    @Autowired
    private IUserCredentialRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    public String saveUser(UserCredential credential) {
        try{
            credential.setPassword(passwordEncoder.encode(credential.getPassword()));
            repository.save(credential);
            return "Usuario registrado.";
        } catch (Exception e) {
            log.error("Usuario no registrado:".concat(e.getMessage()));
            return "Usuario No registrado.";
        }
    }

    public String generateToken(String username) {
        return jwtService.generateToken(username);
    }

    public void validateToken(String token) {
        jwtService.validateToken(token);
    }
}
