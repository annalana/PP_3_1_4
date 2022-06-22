package ru.kata.spring.boot_security.demo.models;

import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "users")
//Имплементировать юзер-детейлс
public class User  implements UserDetails {
    // Поля
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long id;
    @Column(name="login", unique = true)
    private String username;
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
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    Set<Role> authorities = new HashSet<>();

    // Конструкторы
    public User() {}

    // Методы
    @Override
    public String toString() {
        return new StringBuilder("User \n with id-").append(getId()).append(" \n")
                .append(getUsername()).append(" pwd:").append(getPassword()).append("\n")
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
        return username.equals(user.username) && password.equals(user.password) && Objects.equals(name, user.name)
                && Objects.equals(lastName, user.lastName) && Objects.equals(phoneNumber, user.phoneNumber)
                && Objects.equals(email, user.email) && authorities.equals(user.authorities);
    }
    @Override
    public int hashCode() {
        return Objects.hash(username, name, lastName, email);
    }
    @Override
    public Set<Role> getAuthorities() {
        return authorities;
    }
    @Override
    public String getPassword() {
        return password;
    }
    @Override
    public String getUsername() {
        return username;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
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
    public void setAuthorities(Set<Role> roles) {
        this.authorities = roles;
    }
    public void setUsername(String login) {
        this.username = login;
    }
    public void setPassword(String password) {
        this.password = password;
    }

}
