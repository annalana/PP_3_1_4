package ru.kata.spring.boot_security.demo.models;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    // Поля
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long id;
    @Column(name="login", unique = true)
    private String login;
    @Column(name="passworduser")
    private String password;
    @Column(name = "username")
    private String name;
    @Column(name = "lastname")
    private String lastName;
    @Column(name = "phonenumber")
    private BigInteger phoneNumber;
    @Column
    private String email;
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    Set<Role> roles = new HashSet<>();

    // Конструкторы
    public User() {}
    // Методы
    public void addNewRole(Role role) {
        roles.add(role);
    }
    @Override
    public String toString() {
        return new StringBuilder("User \n with id-").append(getId()).append(" \n")
                .append(getLogin()).append(" pwd:").append(getPassword()).append("\n")
                .append(getName()).append(getLastName()).append("\n")
                .append(" has Email: ").append(getEmail())
                .append(" and phone number: ").append(getPhoneNumber())
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return login.equals(user.login) && password.equals(user.password) && Objects.equals(name, user.name)
                && Objects.equals(lastName, user.lastName) && Objects.equals(phoneNumber, user.phoneNumber)
                && Objects.equals(email, user.email) && roles.equals(user.roles);
    }
    @Override
    public int hashCode() {
        return Objects.hash(login, name, lastName, email);
    }

    // Геттеры и сеттеры класса
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public BigInteger getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(BigInteger phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Set<Role> getRoles() {
        return roles;
    }
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

}
