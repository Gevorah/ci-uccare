package co.edu.icesi.dev.uccareapp.transport.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.dev.uccareapp.transport.repository.UserRepository;
import co.edu.icesi.dev.uccareapp.transport.security.User;

@Service
public class UserServiceImp implements UserService {
    
    private UserRepository userrepository;

    @Autowired
    public UserServiceImp(UserRepository userrepository) {
        this.userrepository = userrepository;
    }

    @Override
    public Optional<User> findById(Integer id) {
        return userrepository.findById(id);
    }

    @Override
    public Iterable<User> findAll() {
        return userrepository.findAll();
    }
    
}
