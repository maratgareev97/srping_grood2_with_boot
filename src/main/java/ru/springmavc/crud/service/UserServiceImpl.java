package ru.springmavc.crud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.springmavc.crud.dao.UserDAOInterface;
import ru.springmavc.crud.models.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAOInterface userDAOInterface;

    @Override
    public List<User> index() {
        System.out.println("foxstu       "+userDAOInterface.getUserByLogin("foxstu"));
        return userDAOInterface.index();
    }

    @Override
    public User getUserById(int id) {
        return userDAOInterface.getUserById(id);
    }

    @Override
    public void saveUser(User user) {
        userDAOInterface.saveUser(user);
    }

    @Override
    public void updateUser(int id, User updatedUser) {
        userDAOInterface.updateUser(id, updatedUser);
    }

    @Override
    public void deleteUser(int id) {
        userDAOInterface.deleteUser(id);
    }

    @Override
    public void test() {
        System.out.println("Тест");
    }
}
