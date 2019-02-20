package app.agixis.com.protoTypeForClient.ws;

import app.agixis.com.protoTypeForClient.model.Customer;
import app.agixis.com.protoTypeForClient.model.RoleEnum;
import app.agixis.com.protoTypeForClient.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashSet;

@RestController
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/signin")
    public Customer signUp(@RequestBody FormSignUpDto form) {
        Customer user = customerRepository.findByEmail(form.getEmail());
        if (user == null) {
            throw new UsernameNotFoundException("this user doesnot existe please register" + form.getEmail());
        }
        return user;
    }

    @PostMapping("/register")
    public Customer register(@RequestBody FormRegisterDto form) {
        Customer user = customerRepository.findByEmail(form.getEmail());
        if (user != null) {
            throw new UsernameNotFoundException("this user  exists  please register with" + form.getEmail());
        }
        Customer cost = new Customer();
        cost.setName(form.getName());
        cost.setLastName(form.getLasName());
        cost.setEmail(form.getEmail());
        cost.setPasseWord(bCryptPasswordEncoder.encode(form.getPassword()));
        cost.setRoles(new HashSet<>(Collections.singleton(RoleEnum.USER)));
        return cost;
    }
}
