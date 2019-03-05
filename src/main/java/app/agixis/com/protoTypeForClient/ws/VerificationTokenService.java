package app.agixis.com.protoTypeForClient.ws;

import app.agixis.com.protoTypeForClient.model.Customer;
import app.agixis.com.protoTypeForClient.model.VerificationToken;

public interface VerificationTokenService {
    void createVerificationToken(Customer user, String token);
    void deleteVerificationToken(VerificationToken v);

}
