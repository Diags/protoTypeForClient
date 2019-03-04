package app.agixis.com.protoTypeForClient.ws;

import app.agixis.com.protoTypeForClient.model.Customer;

public interface VerificationTokenService {
    void createVerificationToken(Customer user, String token);
}
