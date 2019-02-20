package app.agixis.com.protoTypeForClient.ws;

import app.agixis.com.protoTypeForClient.model.Customer;
import app.agixis.com.protoTypeForClient.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;

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
    if (user == null) {
        throw new UsernameNotFoundException("this user doesnot existe please register" + form.getEmail());
    }


        return user;
    }
}
