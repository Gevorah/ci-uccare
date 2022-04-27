package co.edu.icesi.dev.uccareapp.transport.repository;

import java.util.List;

import org.hibernate.usertype.UserType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.edu.icesi.dev.uccareapp.transport.model.login.UserApp;

@Repository
public interface UserRepository extends CrudRepository<UserApp, Integer> {
    List<UserApp> findByUsername(String username);
    List<UserApp> findByType(UserType type);
}
