package app.agixis.com.protoTypeForClient.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Set;
@Entity
public class Customer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private String name;
    private String lastName;
    private String passeWord;
    @NotBlank
    @Email(message = "please email must be correct")
    private String email;
    @ElementCollection(targetClass = RoleEnum.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_genres", joinColumns = @JoinColumn(name = "customer_id"))
    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Set<RoleEnum> roles;

    public Customer() { }

    public Customer(String name, String lastName, String passeWord, String email ,Set<RoleEnum> roles) {
        this.name = name;
        this.lastName = lastName;
        this.passeWord = passeWord;
        this.email = email;
        this.roles = roles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPasseWord() {
        return passeWord;
    }
    @JsonIgnore
    public void setPasseWord(String passeWord) {
        this.passeWord = passeWord;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<RoleEnum> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleEnum> roles) {
        this.roles = roles;
    }
}
