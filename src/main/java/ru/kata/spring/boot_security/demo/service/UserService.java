package ru.kata.spring.boot_security.demo.service;

import javassist.NotFoundException;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();
    User getUserByUsername(String email);
    User getByIdService(Long id);
    boolean save(User user);
    void createUser(User user);
    boolean update(User updatedUser) throws NotFoundException;
    void delete(Long id);
}
