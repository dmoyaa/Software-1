package co.edu.unbosque.software1.dtos;

public class UsuarioDto {

    private String login;
    private String password;
    private String rol;

    public UsuarioDto(String login, String password, String rol){
        this.login = login;
        this.password = password;
        this.rol = rol;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getRol() {
        return rol;
    }
}
