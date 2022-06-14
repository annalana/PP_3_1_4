package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Set;

@Repository
public class UserDaoImpl implements UserDao{
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }
    @Override
    public void removeUser(long id) {
        entityManager.remove(getUser(id));
    }
    @Override
    public User getUser(long id) {
        return entityManager.find(User.class, id);
    }
    public User getUser(String login) {
        List<User> list = entityManager.createQuery("from User where login=?1", User.class)
                .setParameter(1, login).getResultList();
        return list.size() > 0 ? list.get(0) : null;
    }
    private void basicRedact(User user, User updated) {
        user.setEmail(updated.getEmail());
        user.setLogin(updated.getLogin());
        user.setPassword(updated.getPassword());
        user.setLastName(updated.getLastName());
        user.setPhoneNumber(updated.getPhoneNumber());
        user.setName(updated.getName());
    }
    @Override
    public User redactUser(long id, User updated) {
        User user = entityManager.find(User.class, id);
        basicRedact(user, updated);
        entityManager.merge(user);
        return user;
    }
    @Override
    public User redactUser(long id, User updated, Set<Role> userroles) {
        User user = entityManager.find(User.class, id);
        basicRedact(user, updated);
        user.getRoles().addAll(userroles);
        user.getRoles().retainAll(userroles);
        entityManager.merge(user);
        return user;
    }
    @Override
    public List<User> getUserList() {
        return entityManager
                .createQuery("from User", User.class).getResultList();
    }
}
