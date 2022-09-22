package ru.springmavc.crud.dao;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.springmavc.crud.models.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Component
@Repository
public class UserDAO implements UserDAOInterface {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> index() {
        List<User> users = new ArrayList<>();
        try {
            TypedQuery<User> entity = entityManager.createQuery("SELECT u from User u", User.class);
            users = entity.getResultList();

            System.out.println("users     " + " | " +
                    users.get(0).getId() + " | " +
                    users.get(0).getName() + " | " +
                    users.get(0).getAge() + " | " +
                    users.get(0).getEmail() + " | " +
                    users.get(0).getUsername() + " | " +
                    users.get(0).getPassword());

            System.out.println("users  " + users);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public User getUserById(int id) {
        User user;
        List<User> userList;
        TypedQuery<User> entity = entityManager.createQuery("select p from User p where p.id = :id", User.class);
        entity.setParameter("id", id);
        userList = entity.getResultList();
        user = new User();
        user.setId(userList.get(0).getId());
        user.setName(userList.get(0).getName());
        user.setEmail(userList.get(0).getEmail());
        user.setAge(userList.get(0).getAge());
        user.setUsername(userList.get(0).getUsername());
        user.setPassword(userList.get(0).getPassword());
        user.setRoles_string(userList.get(0).getRoles_string());
        user.setRoles(userList.get(0).getRoles());
        return user;
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        user.setName(user.getName());
        user.setAge(user.getAge());
        user.setEmail(user.getEmail());
        user.setUsername(user.getUsername());
        user.setPassword(user.getPassword());
        user.setRoles_string("USER" + user.getRoles_string());
        entityManager.persist(user);
        entityManager.flush();
    }

    @Override
    @Transactional
    public void updateUser(int id, User updatedUser) {
        updatedUser.setName(updatedUser.getName());
        updatedUser.setAge(updatedUser.getAge());
        updatedUser.setEmail(updatedUser.getEmail());
        updatedUser.setUsername(updatedUser.getUsername());
        updatedUser.setPassword(updatedUser.getPassword());

        if (updatedUser.getRoles_string() == null) {
            updatedUser.setRoles_string("USER");
        } else {
            updatedUser.setRoles_string(updatedUser.getRoles_string());
        }
        entityManager.merge(updatedUser);
        entityManager.flush();
    }

    @Override
    @Transactional
    public void deleteUser(int id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
        entityManager.flush();

    }

    @Override
    public User getUserByLogin(String username) {
        List<User> userList;
        TypedQuery<User> q = (entityManager.createQuery("select u from User u " +
                "join fetch u.roles where u.username = :username", User.class));
        q.setParameter("username", username);
        return q.getResultList().stream().findFirst().orElse(null);
    }
}