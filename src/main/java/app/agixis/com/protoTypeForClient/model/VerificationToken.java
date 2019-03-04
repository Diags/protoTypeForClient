package app.agixis.com.protoTypeForClient.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class VerificationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    @NotBlank
    @Column(nullable = false, unique = true)
    private String token;
    @OneToOne(cascade=CascadeType.ALL)
    private Customer customer;

    public VerificationToken(String token, Customer user) {
        this.token = token;
        this.customer = user;
    }

    public VerificationToken() {
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }


}
