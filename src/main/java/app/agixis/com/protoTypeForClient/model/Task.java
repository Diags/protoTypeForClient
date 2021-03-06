package app.agixis.com.protoTypeForClient.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @NotBlank(message = "tasks must not be empty !!")
    private String message;
    @Lob
    @Column(name = "Image", length = Integer.MAX_VALUE, nullable = true)
    private byte[] image;
    public Task(String message) {
        this.message = message;
    }

    public Task() {
    }
    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
