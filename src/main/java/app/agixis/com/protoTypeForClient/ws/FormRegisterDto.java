package app.agixis.com.protoTypeForClient.ws;

public class FormRegisterDto {
    private String  name;
    private String  lasName;
    private String  password ;
    private String confirmPassword;
    private String email;

    public FormRegisterDto(String name, String lasName, String password, String confirmPassword, String email) {
        this.name = name;
        this.lasName = lasName;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.email = email;
    }

    public FormRegisterDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLasName() {
        return lasName;
    }

    public void setLasName(String lasName) {
        this.lasName = lasName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
