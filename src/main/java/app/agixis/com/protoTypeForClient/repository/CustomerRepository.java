package app.agixis.com.protoTypeForClient.repository;

import app.agixis.com.protoTypeForClient.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByEmail(String email);
    Customer findByName(String name);
    Customer findByNameOrEmail(String name);

}
