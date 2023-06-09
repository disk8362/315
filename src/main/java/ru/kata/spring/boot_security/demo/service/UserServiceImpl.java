package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserByUsername(String email) {
        User user = null;
        try {
            user = userRepository.findByUsername(email);
        } catch (Exception e) {
            System.err.println("Error search user in database .....");
            System.err.println("User with name -> " + email + "not found in database");
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User getByIdService(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public boolean save(User user) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            System.err.println("Error create new user .....");
            System.err.println("An account already exists for this username -> " + user.getUsername());
            System.err.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(User updatedUser) {
        try {
            if (!updatedUser.getPassword().equals(getByIdService(updatedUser.getId()).getPassword())) {
                updatedUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
            }
            userRepository.save(updatedUser);
            return true;
        } catch (Exception e) {
            System.err.println("Error edit user .....");
            System.err.println("User with username -> " + updatedUser.getUsername() + " <- already exist in db");
            System.err.println(e.getMessage());
            return false;
        }
    }

    @Override
    public void delete(Long id) {
        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            System.err.println("Error delete user .....");
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
}
