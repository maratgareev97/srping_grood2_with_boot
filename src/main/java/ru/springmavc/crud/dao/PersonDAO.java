package ru.springmavc.crud.dao;

import org.hibernate.sql.Select;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.springmavc.crud.models.Person;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO implements PersonDAOInterface {
    private static int PEOPLE_COUNT;

    private static final String URL = "jdbc:postgresql://localhost:5432/first_db";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "GOGUDAserver123!";

    private static Connection connection;

    @PersistenceContext
    private EntityManager entityManager;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List<Person> index() {
        List<Person> people = new ArrayList<>();

        try {
            TypedQuery<Person> entity = entityManager.createQuery("SELECT u from Person u", Person.class);
            people = entity.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        try {
//            Statement statement = connection.createStatement();
//            String SQL = "SELECT * FROM Person";
//            ResultSet resultSet = statement.executeQuery(SQL);
//
//            while (resultSet.next()) {
//                Person person = new Person();
//
//                person.setId(resultSet.getInt("id"));
//                person.setName(resultSet.getString("name"));
//                person.setEmail(resultSet.getString("email"));
//                person.setAge(resultSet.getInt("age"));
//
//                people.add(person);
//            }
//
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }

        return people;
    }

    @Override
    public Person show(int id) {
        Person person = null;

        List<Person> people = new ArrayList<>();


        TypedQuery<Person> entity = entityManager.createQuery("select p from Person p where p.id = :id", Person.class);
        entity.setParameter("id", id);
        people = entity.getResultList();
        System.out.println(people.get(0).getName());
        person = new Person();
        person.setId(people.get(0).getId());
        person.setName(people.get(0).getName());
        person.setEmail(people.get(0).getEmail());
        person.setAge(people.get(0).getAge());

//        person = new Person();
//        person.setName("sd");


//        try {
//            PreparedStatement preparedStatement =
//                    connection.prepareStatement("SELECT * FROM Person WHERE id=?");
//
//            preparedStatement.setInt(1, id);
//
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            resultSet.next();
//
//            person = new Person();
//
//            person.setId(resultSet.getInt("id"));
//            person.setName(resultSet.getString("name"));
//            person.setEmail(resultSet.getString("email"));
//            person.setAge(resultSet.getInt("age"));
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        System.out.println(person.getId());
        return person;
    }

    @Override
    @Transactional()
    public void save(Person person) {
//        person = new Person();
//        System.out.println(person.getName());
//        entityManager.persist(person);


        person.setName(person.getName());
        person.setAge(person.getAge());
        person.setEmail(person.getEmail());
        System.out.println(person.getName());
        this.entityManager.persist(person);
//        entityManager.persist(person);

//        entityManager.createNativeQuery("INSERT INTO person (name, age, email) VALUES (?,?,?)")
//                .setParameter(1, person.getName())
//                .setParameter(2, person.getAge())
//                .setParameter(3, person.getEmail())
//                .executeUpdate();


//        try {
//            PreparedStatement preparedStatement =
//                    connection.prepareStatement("INSERT INTO Person VALUES(1, ?, ?, ?)");
//
//            preparedStatement.setString(1, person.getName());
//            preparedStatement.setInt(2, person.getAge());
//            preparedStatement.setString(3, person.getEmail());
//
//            preparedStatement.executeUpdate();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
    }

    @Override
    public void update(int id, Person updatedPerson) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("UPDATE Person SET name=?, age=?, email=? WHERE id=?");

            preparedStatement.setString(1, updatedPerson.getName());
            preparedStatement.setInt(2, updatedPerson.getAge());
            preparedStatement.setString(3, updatedPerson.getEmail());
            preparedStatement.setInt(4, id);

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        PreparedStatement preparedStatement =
                null;
        try {
            preparedStatement = connection.prepareStatement("DELETE FROM Person WHERE id=?");

            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}