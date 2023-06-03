package ru.kata.spring.boot_security.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.annotation.PostConstruct;
import java.util.HashSet;

@SpringBootApplication
public class Application {

    private final RoleService roleService;

    private final UserService userService;

    @Autowired
    public Application(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @PostConstruct
    public void create() {
        Role role_admin = new Role("ROLE_ADMIN");
        Role role_user = new Role("ROLE_USER");
        roleService.save(role_admin);
        roleService.save(role_user);
        HashSet<Role> setAdmin = new HashSet<>();
        setAdmin.add(role_admin);
        setAdmin.add(role_user);
        HashSet<Role> setUser = new HashSet<>();
        setUser.add(role_user);
        userService.createUser(new User("admin", "admin", "Oleg", "Petrov", "ivan@mail.com", setAdmin));
        userService.createUser(new User("user", "user", "Petr", "Ivanov", "petr@mail.com", setUser));
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
