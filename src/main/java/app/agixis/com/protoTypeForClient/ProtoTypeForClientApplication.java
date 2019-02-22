package app.agixis.com.protoTypeForClient;

import app.agixis.com.protoTypeForClient.model.Customer;
import app.agixis.com.protoTypeForClient.model.RoleEnum;
import app.agixis.com.protoTypeForClient.model.Task;
import app.agixis.com.protoTypeForClient.repository.CustomerRepository;
import app.agixis.com.protoTypeForClient.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.*;
import java.util.stream.Stream;

@SpringBootApplication
public class ProtoTypeForClientApplication implements CommandLineRunner {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private CustomerRepository customerRepository;

    public static void main(String[] args) {
        SpringApplication.run(ProtoTypeForClientApplication.class, args);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void run(String... args) throws Exception {
        Set<RoleEnum> col = new HashSet<>();
        col.add(RoleEnum.USER);
        col.add(RoleEnum.ADMINISTRATOR);
        List<Customer> listCust = Arrays.asList(
                new Customer("toto", "titit", bCryptPasswordEncoder().encode("1234"), "titi@gmail.com", Collections.singleton(RoleEnum.USER)),
                new Customer("tata", "tata", bCryptPasswordEncoder().encode("1234"), "tata@gmail.com", Collections.singleton(RoleEnum.USER)),
                new Customer("cucu", "cucu", bCryptPasswordEncoder().encode("1234"), "cucu@gmail.com", Collections.singleton(RoleEnum.USER)),
                new Customer("admin", "admins", bCryptPasswordEncoder().encode("1234"), "admin@gmail.com", col));
        listCust.stream().forEach(p -> customerRepository.save(p));
        customerRepository.findAll().stream().filter(p->p.getRoles().contains(RoleEnum.ADMINISTRATOR)).map(c -> c.getRoles()).forEach(System.out :: println);
        System.out.println("******************");
        Stream.of(new Task("task1"), new Task("task2"), new Task("task3"), new Task("task4")).forEach(t -> taskRepository.save(t));
        taskRepository.findAll().stream().map(t -> t.getMessage()).forEach(System.out :: println);


    }
}
