package ru.springmavc.crud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.springmavc.crud.dao.UserDAO;
import ru.springmavc.crud.dao.UserDAOInterface;
import ru.springmavc.crud.models.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAOInterface userDAOInterface;

    @Override
    public List<User> index() {
        return userDAOInterface.index();
    }

    @Override
    public User show(int id) {
        return userDAOInterface.show(id);
    }

    @Override
    public void save(User user) {
        userDAOInterface.save(user);
    }

    @Override
    public void update(int id, User updatedUser) {
        userDAOInterface.update(id, updatedUser);
    }

    @Override
    public void delete(int id) {
        userDAOInterface.delete(id);
    }

    @Override
    public void test() {
        System.out.println("Тест");
    }
}
