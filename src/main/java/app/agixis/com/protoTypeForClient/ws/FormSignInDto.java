package app.agixis.com.protoTypeForClient.ws;

public class FormSignInDto {
    private String name;
    private String  password ;

    public FormSignInDto(String email, String password) {
        this.name = email;
        this.password = password;
    }

    public FormSignInDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
