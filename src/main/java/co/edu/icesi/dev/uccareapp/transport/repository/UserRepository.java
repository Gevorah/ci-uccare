package co.edu.icesi.dev.uccareapp.transport.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.edu.icesi.dev.uccareapp.transport.security.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    List<User> findByUsername(String username);
}
