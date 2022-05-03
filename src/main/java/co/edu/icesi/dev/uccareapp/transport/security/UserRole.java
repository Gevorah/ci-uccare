package co.edu.icesi.dev.uccareapp.transport.security;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_role")
public class UserRole implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "user_id")
    private Integer userId;
    
    @Column(name = "role_id")
    private Integer roleId;
}
