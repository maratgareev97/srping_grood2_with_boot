package ru.springmavc.crud.service;

import ru.springmavc.crud.models.User;

import java.util.List;

public interface UserService {
    public List<User> index();
    public User show(int id);
    public void save(User user);
    public void update(int id, User updatedUser);
    public void delete(int id);

    public void test();
}
