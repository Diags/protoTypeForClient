package app.agixis.com.protoTypeForClient.ws;

public class FormSignUpDto {
    private String email;
    private String  password ;

    public FormSignUpDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public FormSignUpDto() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
