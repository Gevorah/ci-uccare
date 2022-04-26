package co.edu.icesi.dev.uccareapp.transport.service;

import java.util.Optional;

import co.edu.icesi.dev.uccareapp.transport.model.hr.UserApp;

public interface UserService {
    public Optional<UserApp> findById(Integer id);
    public Iterable<UserApp> findAll();
}
