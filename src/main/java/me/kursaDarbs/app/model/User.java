package me.kursaDarbs.app.model;


import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String username;
    private String password;
    @ManyToMany
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @ManyToOne
    @JoinColumn(name = "cinema_id")
    private Cinema cinema;





    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }





    public Set<Role> getRoles() {
        return roles;
    }
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }


    public String RolesToString() {
        String rolesToString = "";
        for(Role role : roles ) {
            rolesToString = rolesToString + " " + role.getName();
        }
        return rolesToString;
    }

    @ManyToOne
    @JoinColumn(name = "cinema_id")
    public Cinema GetCinema() {
        return cinema;
    }
    public void SetCinema(Cinema cinema) {
        this.cinema = cinema;
    }
}