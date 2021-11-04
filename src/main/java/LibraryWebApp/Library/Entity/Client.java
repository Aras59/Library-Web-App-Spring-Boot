package LibraryWebApp.Library.Entity;


import com.sun.istack.NotNull;
import org.checkerframework.common.value.qual.MinLen;
import javax.validation.constraints.Size;

import javax.persistence.*;

@Entity
@Table(name = "Client")
public class Client{

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Column(name = "name")
    private String Name;
    @NotNull
    @Column(name = "password")
    @Size(min=5,max=15)
    private String Password;
    @NotNull
    @Size(min=5,max=15)
    @Column(name = "login")
    private String Login;
    @Column(name = "userType")
    private String UserType;


    public Client() {
        this.UserType = "User";
    }

    public Client(Long id, String name, String surname, String login) {
        this.id = id;
        this.Name = name;
        this.Password = surname;
        this.Login = login;
        this.UserType = "User";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return Login;
    }

    public void setLogin(String login) {
        Login = login;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getUserType() {
        return UserType;
    }

    public void setUserType(String userType) {
        UserType = userType;
    }
}
