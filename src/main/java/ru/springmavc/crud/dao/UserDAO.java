package ru.springmavc.crud.dao;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.springmavc.crud.models.Role;
import ru.springmavc.crud.models.User;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
        List<Role> roles = new ArrayList<>();
        try {
            TypedQuery<User> entity = entityManager.createQuery("SELECT u from User u", User.class);
            users = entity.getResultList();

//            Query query = entityManager.createNativeQuery("select roles_id from users inner join users_roles " +
//                    "on users.id = users_roles.users_id where users.id = 3");
//            List users1 = query.getResultList();

            System.out.println("users     " + " | " +
                    users.get(0).getId() + " | " +
                    users.get(0).getName() + " | " +
                    users.get(0).getAge() + " | " +
                    users.get(0).getEmail() + " | " +
                    users.get(0).getUsername() + " | " +
                    users.get(0).getPassword());

//            System.out.println("users1     " + users1);

//            users.
//            users.add(1, (User) users1.get(0));

            System.out.println("users  " + users);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }


    @Override
    public User getUserById(int id) {
        User user = null;

        List<User> userList = new ArrayList<>();
//        System.out.println(entityManager.find(User.class, id));

        TypedQuery<User> entity = entityManager.createQuery("select p from User p where p.id = :id", User.class);
        entity.setParameter("id", id);
        userList = entity.getResultList();
//        System.out.println(userList.get(0).getName());
        user = new User();
        user.setId(userList.get(0).getId());
        user.setName(userList.get(0).getName());
        user.setEmail(userList.get(0).getEmail());
        user.setAge(userList.get(0).getAge());
        user.setUsername(userList.get(0).getUsername());
        user.setPassword(userList.get(0).getPassword());
        user.setRoles_string(userList.get(0).getRoles_string());

        System.out.println("user:   " + user);
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

//        user.setPassword("111");
//        user.setUsername("name");


        System.out.println(user + "           ----");

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
        updatedUser.setUsername(updatedUser.getUsername());
        updatedUser.setPassword(updatedUser.getPassword());

//        updatedUser.setRoles_string(updatedUser.getRoles_string());
        if (updatedUser.getRoles_string() == null) {
            updatedUser.setRoles_string("USER");
            System.out.println("updatedUser.getRoles_string()   " + updatedUser.getRoles_string());
        } else {
            updatedUser.setRoles_string(updatedUser.getRoles_string());
            System.out.println("not null");
        }

        entityManager.merge(updatedUser);
        entityManager.flush();
        System.out.println("updatedUser    " + updatedUser);
    }

    @Override
    @Transactional
    public void deleteUser(int id) {
        User user = entityManager.find(User.class, id);
        System.out.println(user + " kkkkkkkkkkkk");
        entityManager.remove(user);
        entityManager.flush();

    }

    @Override
    public User getUserByLogin(String username) {
        List<User> userList = new ArrayList<>();
        TypedQuery<User> q = (entityManager.createQuery("select u from User u " +
                "join fetch u.roles where u.username = :username", User.class));
        q.setParameter("username", username);
        userList = q.getResultList();
        System.out.println("userList-------" + userList);
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" + q.getResultList().stream().findFirst().orElse(null));
        return q.getResultList().stream().findFirst().orElse(null);
    }
}