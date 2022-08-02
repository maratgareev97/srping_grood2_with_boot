package ru.springmavc.crud.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.springmavc.crud.models.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDAO implements UserDAOInterface {


    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<User> index() {
        List<User> users = new ArrayList<>();

        try {
            TypedQuery<User> entity = entityManager.createQuery("SELECT u from User u", User.class);
            users = entity.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }


        return users;
    }

    @Override
    public User getUserById(int id) {
        User user = null;

        List<User> userList = new ArrayList<>();


        TypedQuery<User> entity = entityManager.createQuery("select p from User p where p.id = :id", User.class);
        entity.setParameter("id", id);
        userList = entity.getResultList();
        System.out.println(userList.get(0).getName());
        user = new User();
        user.setId(userList.get(0).getId());
        user.setName(userList.get(0).getName());
        user.setEmail(userList.get(0).getEmail());
        user.setAge(userList.get(0).getAge());

        return user;
    }


    @Override
    @Transactional
    public void saveUser(User user) {

        user.setName(user.getName());
        user.setAge(user.getAge());
        user.setEmail(user.getEmail());

        System.out.println(user.getId());

        entityManager.persist(user);
        entityManager.flush();
    }

    @Override
    @Transactional
    public void updateUser(int id, User updatedUser) {
        System.out.println(updatedUser.getId() + "  !!!!!!!!!!!!!!!!!!!!! " + id);
        updatedUser.setName(updatedUser.getName());
        updatedUser.setAge(updatedUser.getAge());
        updatedUser.setEmail(updatedUser.getEmail());

        entityManager.merge(updatedUser);
        entityManager.flush();
    }

    @Override
    @Transactional
    public void deleteUser(int id) {
        User user = entityManager.find(User.class, id);
        System.out.println(user + " kkkkkkkkkkkk");
        entityManager.remove(user);
        entityManager.flush();

    }
}