package co.edu.icesi.dev.uccareapp.transport.security;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;

@Entity
public class User {
    @Id
    @SequenceGenerator(name = "USER_USERID_GENERATOR", allocationSize = 1, sequenceName = "USER_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_USERID_GENERATOR")
    @Column(name = "user_id", nullable = false)
    private Integer userid;

    private String username;

    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "users_roles",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles = new ArrayList<Role>();

    public User() {
    }

    public void addRole(Role role) {
        roles.add(role);
    }

    public Integer getUserid() {
        return userid;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
