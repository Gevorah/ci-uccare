package co.edu.icesi.dev.uccareapp.transport.service;

import java.util.Optional;

import co.edu.icesi.dev.uccareapp.transport.security.User;

public interface UserService {
    public Optional<User> findById(Integer id);
    public Iterable<User> findAll();
}
