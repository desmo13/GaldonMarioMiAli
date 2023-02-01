package es.cifpcm.galdonmariomiali.model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "user_id", nullable = false)
    private Short userId;

    @Column(name = "user_name", nullable = false, length = 50)
    private String userName;

    @Column(name = "password", nullable = false, length = 256)
    private String password;


    public Set<Group> getRoles() {
        return roles;
    }

    public void setRoles(Set<Group> roles) {
        this.roles = roles;
    }

    @ManyToMany(fetch = FetchType.EAGER,cascade =CascadeType.ALL )
    @JoinTable(
        name = "users_groups",
            joinColumns = @JoinColumn(name = "user_name",referencedColumnName = "user_name"),
            inverseJoinColumns = @JoinColumn(name = "group_id",referencedColumnName = "group_id")

    )
    private Set<Group> roles=new HashSet<>();

    public Short getUserId() {
        return userId;
    }

    public void setUserId(Short userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }



    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }

}