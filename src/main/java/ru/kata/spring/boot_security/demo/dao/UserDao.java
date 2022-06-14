package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;
import java.util.Set;

public interface UserDao {
    void addUser(User user);
    void removeUser(long id);
    User getUser(long id);
    User getUser(String login);
    User redactUser(long id, User updated);
    User redactUser(long id, User updated, Set<Role> userroles);
    List<User> getUserList();
}
