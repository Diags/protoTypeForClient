package app.agixis.com.protoTypeForClient.ws;

import app.agixis.com.protoTypeForClient.errorHandle.CustomerNotfoundException;
import app.agixis.com.protoTypeForClient.model.Customer;
import app.agixis.com.protoTypeForClient.model.RoleEnum;
import app.agixis.com.protoTypeForClient.model.VerificationToken;
import app.agixis.com.protoTypeForClient.repository.CustomerRepository;
import app.agixis.com.protoTypeForClient.repository.VerificationTokenRepo;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import java.util.*;

@RestController
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private VerificationTokenRepo verificationTokenRepo;
    @Autowired
    private EmailSenderService emailSenderService;
    @Autowired
    private VerificationTokenServiceImpl verificationTokenService;

    @PostMapping("/signin")
    public Customer signUp(@RequestBody FormSignInDto form) {
        Customer user = customerRepository.findByNameOrEmail(form.getName(), form.getName());
        if (user == null) {
            throw new UsernameNotFoundException("this user doesnot existe please register" + form.getName());
        }
        return user;
    }

    @PostMapping("/register")
    public Customer register(@RequestBody FormRegisterDto form, @Context HttpServletRequest request) {
        Logger logger = LoggerFactory.getLogger(this.getClass());
        Customer user = customerRepository.findByNameOrEmail(form.getEmail(), form.getEmail());
        logger.info("Customer find", user);
        if (user != null) {
            throw new UsernameNotFoundException("this user already  exists  please register with  " + form.getEmail());
        }
        if (!form.getPassword().equals(form.getConfirmPassword()))
            new Exception("password not be confirmed" + form.getConfirmPassword());
        Customer cost = new Customer();
        cost.setName(form.getName());
        cost.setLastName(form.getLasName());
        cost.setEmail(form.getEmail());
        cost.setPasseWord(bCryptPasswordEncoder.encode(form.getPassword()));
        cost.setRoles(new HashSet<>(Collections.singleton(RoleEnum.USER)));
        String token = UUID.randomUUID().toString();
        String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        String from = "diaguilybouna@gmail.com";
        String to = cost.getEmail();
        String subject = "Confirm Registration";
        String body = " To confirm your account, please click here :" + " <a href= "
                + url + "/registrationConfirm?token=" + token +" ><a>";
        customerRepository.save(cost);
        verificationTokenService.createVerificationToken(cost, token);
        emailSenderService.sendMail(from, to, subject, body);
        return customerRepository.findByNameOrEmail(form.getEmail(), form.getEmail());
    }
    @GetMapping("/registrationConfirm")
    public String confirmRegistration(@RequestParam("token") String token) throws Exception {
        if (Strings.isBlank(token)){
            throw new IllegalArgumentException("Token is required in confirmRegistration {token} = "+ token);
        }
        VerificationToken verificationToken = verificationTokenRepo.findByToken(token);
        if (verificationToken == null) {
            throw new Exception("This token is not available at confirmRegistration" + token);
        }
        Customer customer = verificationToken.getCustomer();
        if (customer == null) throw new CustomerNotfoundException(customer);
        verificationToken.setToken(null);
        return "Confirmation success "+  " redirect to:"+" <a href=http://localhost:4200/login>Longing</a>";
    }
}
