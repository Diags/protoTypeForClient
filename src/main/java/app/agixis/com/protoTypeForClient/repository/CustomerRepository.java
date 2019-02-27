package app.agixis.com.protoTypeForClient.repository;

import app.agixis.com.protoTypeForClient.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByNameOrEmail(String name,String email);
}
