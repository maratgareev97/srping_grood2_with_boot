package ru.springmavc.crud.models;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
//    @NotEmpty(message = "Name should not be empty")
//    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    private String name;

    @Column(name = "age")
//    @Min(value = 0, message = "Age should be greater than 0")
    private int age;

    @Column(name = "email")
//    @NotEmpty(message = "Email should not be empty")
//    @Email(message = "Email should be valid")
    private String email;

    //    @NotEmpty(message = "Username cannot be empty")
    @Column(name = "username")
//    @Size(min = 2, max = 15, message = "Name should be between 2 and 15 latin characters")
    private String username;

    //    @NotEmpty(message = "Password cannot be empty")
    @Column(name = "password")
//    @Size(min = 4, message = "Password should be greater then 4 symbols")
    private String password;

    @Column(name = "roles_string")
    private String roles_string;
//    @Transient
//    transient private String confirmPassword;
//
//    public String getConfirmPassword() {
//        return confirmPassword;
//    }

//    public void setConfirmPassword(String confirmPassword) {
//        this.confirmPassword = confirmPassword;
//    }

    @ManyToMany
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "roleId"))
    private Set<Role> roles;


    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRoles_string() {
        return roles_string;
    }

    public void setRoles_string(String roles_string) {
        this.roles_string = roles_string;
    }

    public User() {}

    public User(int id, String name, int age, String email,
                Set<Role> roles, String username, String password,
//                String confirmPassword,
                String roles_string) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.roles = roles;
        this.username = username;
        this.password = password;
        this.roles_string = roles_string;
//        this.confirmPassword = confirmPassword;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
//                ", confirmPassword='" + confirmPassword + '\'' +
                ", roles=" + roles_string+
                '}';
    }

}