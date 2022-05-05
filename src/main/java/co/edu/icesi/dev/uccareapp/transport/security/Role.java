package co.edu.icesi.dev.uccareapp.transport.security;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;

@Entity
public class Role {
    
    @Id
    @SequenceGenerator(name = "ROLE_ROLEID_GENERATOR", allocationSize = 1, sequenceName = "ROLE_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ROLE_ROLEID_GENERATOR")
    @Column(name = "role_id", nullable = false)
    private Integer roleid;

    private String name;

    private String desc;

    @ManyToMany(mappedBy = "roles")
    private List<User> users = new ArrayList<User>();

    public Role() {
    }

    public Integer getId() {
        return roleid;
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

    public void setId(Integer roleid) {
        this.roleid = roleid;
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
