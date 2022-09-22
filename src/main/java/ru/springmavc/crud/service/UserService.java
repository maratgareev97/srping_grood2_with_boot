package ru.springmavc.crud.service;

import ru.springmavc.crud.models.User;

import java.util.List;

public interface UserService {
    public List<User> index();
    public User getUserById(int id);
    public void saveUser(User user);
    public void updateUser(int id, User updatedUser);
    public void deleteUser(int id);

    public void test();
}
