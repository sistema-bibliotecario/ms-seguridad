package pe.cibertec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.cibertec.model.UserCredential;

import java.util.Optional;

public interface IUserCredentialRepository extends JpaRepository<UserCredential, Integer> {
    Optional<UserCredential> findByEmail(String email);
}
