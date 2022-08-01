package ru.springmavc.crud.dao;

import ru.springmavc.crud.models.User;

import java.util.List;
//@Transactional
public interface UserDAOInterface {
    public List<User> index();
    public User show(int id);
    public void save(User user);
    public void update(int id, User updatedUser);
    public void delete(int id);
}
