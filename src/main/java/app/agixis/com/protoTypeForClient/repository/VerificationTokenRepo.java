package app.agixis.com.protoTypeForClient.repository;

import app.agixis.com.protoTypeForClient.model.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenRepo extends JpaRepository<VerificationToken, Long> {
    VerificationToken findByToken (String VerificationToken);
}
