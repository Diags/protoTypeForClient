package app.agixis.com.protoTypeForClient.ws;

import app.agixis.com.protoTypeForClient.errorHandle.CustomerNotfoundException;
import app.agixis.com.protoTypeForClient.model.Customer;
import app.agixis.com.protoTypeForClient.model.RoleEnum;
import app.agixis.com.protoTypeForClient.model.VerificationToken;
import app.agixis.com.protoTypeForClient.repository.CustomerRepository;
import app.agixis.com.protoTypeForClient.repository.VerificationTokenRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.RequestContext;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import java.util.Collections;
import java.util.HashSet;
import java.util.Locale;

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
        String from = "diaguilybouna@gmail.com";
        String to = "diaguilybouna@gmail.com";
        String subject = "JavaMailSender";
        String token = request.getHeader("postman-token");
        String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        System.out.println(token);
        logger.info("Customer find", cost);
        String body = "To confirm your account, please click here :" + " rn "
                + url + "/signin?token=" + token;
        customerRepository.save(cost);
        verificationTokenService.createVerificationToken(cost, token);
        emailSenderService.sendMail(from, to, subject, body);
        return customerRepository.findByNameOrEmail(form.getEmail(), form.getEmail());
    }

    @GetMapping("/registrationConfirm")
    public Customer confirmRegistration(@RequestParam("token") String token, @Context HttpServletRequest request) throws Exception {
        String confirmToken = request.getHeader("postman-token");
        String url = request.getScheme() + "//" + request.getServerName() + ":" + request.getServerPort();
        System.out.println(token);
        Locale locale = request.getLocale();
        VerificationToken verificationToken = verificationTokenRepo.findByToken(token);
        if (verificationToken == null) {
            throw new Exception("This token is not available" + token);
        }
        Customer customer = verificationToken.getCustomer();
        if (customer == null) throw new CustomerNotfoundException(customer);
        return customer;

    }
}
