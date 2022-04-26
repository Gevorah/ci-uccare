package co.edu.icesi.dev.uccareapp.transport.repository;

import java.util.Iterator;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.edu.icesi.dev.uccareapp.transport.model.hr.UserApp;

@Repository
public interface UserRepository extends CrudRepository<UserApp, Integer> {
    Iterator<UserApp> findByUsername(String username);
}
