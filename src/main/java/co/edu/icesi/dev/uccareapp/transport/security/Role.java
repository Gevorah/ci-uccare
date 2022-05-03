package co.edu.icesi.dev.uccareapp.transport.security;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Role {
    
    @Id    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Integer id;

    private String name;

    private String desc;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;

    public Integer getId() {
        return id;
    }

    public String getDesc() {
        return desc;
    }

    public String getName() {
        return name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
    
}
