package app.agixis.com.protoTypeForClient.ws;

import app.agixis.com.protoTypeForClient.model.Customer;
import app.agixis.com.protoTypeForClient.model.VerificationToken;
import app.agixis.com.protoTypeForClient.repository.VerificationTokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class VerificationTokenServiceImpl implements VerificationTokenService {
    @Autowired
    private VerificationTokenRepo verificationTokenRepo;

    @Override
    public void createVerificationToken(Customer user, String token) {
            VerificationToken myToken = new VerificationToken(token, user);
            verificationTokenRepo.save(myToken);
    }

    @Override
    public void deleteVerificationToken(VerificationToken v) {
        verificationTokenRepo.delete(v);
    }


}