package app.agixis.com.protoTypeForClient.ws;

import app.agixis.com.protoTypeForClient.model.Customer;
import app.agixis.com.protoTypeForClient.model.RoleEnum;
import app.agixis.com.protoTypeForClient.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;

import static app.agixis.com.protoTypeForClient.config.SecurityConstants.HEADER_STRING;

@RestController
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
//    @Autowired
//    private EmailSenderService emailSenderService;
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
        Customer user = customerRepository.findByNameOrEmail(form.getEmail(),form.getEmail());
        if (user != null) {
            throw new UsernameNotFoundException("this user  exists  please register with  " + form.getEmail());
        }
        if(!form.getPassword().equals(form.getConfirmPassword()))  new Exception("password not be confirmed"+ form.getConfirmPassword());
        Customer cost = new Customer();
        cost.setName(form.getName());
        cost.setLastName(form.getLasName());
        cost.setEmail(form.getEmail());
        cost.setPasseWord(bCryptPasswordEncoder.encode(form.getPassword()));
        cost.setRoles(new HashSet<>(Collections.singleton(RoleEnum.USER)));
        customerRepository.save(cost);
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        Enumeration<String> headers= request.getHeaders(HEADER_STRING);
        System.out.println(headers);
        mailMessage.setTo(cost.getEmail());
        mailMessage.setSubject("Complete Registration!");
        mailMessage.setFrom(cost.getEmail());
        mailMessage.setText("To confirm your account, please click here : "
                +"http://localhost:8082/confirm-account?token=");

//        emailSenderService.sendEmail(mailMessage);

        return customerRepository.findByNameOrEmail(form.getEmail(),form.getEmail());
    }

}
