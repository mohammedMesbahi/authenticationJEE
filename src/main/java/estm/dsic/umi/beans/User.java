package estm.dsic.umi.beans;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable,CrudAble<User, Integer>{
    
    private Integer id;
    private String name;
    private String email;
    private String password;

    public User() {
    }

    

    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ",name='" + name + "'
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
