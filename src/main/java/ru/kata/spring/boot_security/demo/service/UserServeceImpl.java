package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.RoleDao;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;
import java.util.Set;

@Service
public class UserServeceImpl implements UserService{
    private UserDao userDao;
    private RoleDao roleDao;
    @Autowired
    UserServeceImpl(UserDao userDao, RoleDao roleDao) {
        this.userDao = userDao;
        this.roleDao = roleDao;
    }
    @Transactional
    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }
    @Transactional
    @Override
    public void removeUser(long id) {
        userDao.removeUser(id);
    }
    @Transactional
    @Override
    public User getUser(long id) {
        return userDao.getUser(id);
    }
    @Transactional
    @Override
    public User redactUser(long id, User updated) {
        return userDao.redactUser(id, updated);
    }
    @Transactional
    public User redactUser(long id, User updated, Set<Role> userroles) {
        return userDao.redactUser(id, updated, userroles);
    }
    @Transactional
    @Override
    public List<User> getUserList() {
        return userDao.getUserList();
    }
    @Override
    public void addUser(User user, List<Role> roles) {
        userDao.addUser(user);
        for (Role role: roles) {
            user.addNewRole(role);
        }

    }
}
