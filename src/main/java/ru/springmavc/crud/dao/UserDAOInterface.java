package ru.springmavc.crud.dao;

import ru.springmavc.crud.models.User;

import java.util.List;
//@Transactional
public interface UserDAOInterface {
    public List<User> index();
    public User getUserById(int id);
    public void saveUser(User user);
    public void updateUser(int id, User updatedUser);
    public void deleteUser(int id);
}
