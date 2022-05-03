package co.edu.icesi.dev.uccareapp.transport.repository;

import org.springframework.data.repository.CrudRepository;

import co.edu.icesi.dev.uccareapp.transport.security.Role;

public interface RoleRepository extends CrudRepository<Role, Integer> {
    
}
