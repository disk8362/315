package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.User;
import javassist.NotFoundException;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User getUserByUsername(String username);

   // User getUserById(Long id);

    User getByIdService(Long id);

    boolean save(User user);

    void saveTestUser(User user);

    boolean update(User updatedUser) throws NotFoundException;

    void delete(Long id);
}
