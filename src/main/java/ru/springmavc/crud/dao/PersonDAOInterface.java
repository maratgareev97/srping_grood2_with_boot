package ru.springmavc.crud.dao;

import ru.springmavc.crud.models.Person;

import java.util.List;

public interface PersonDAOInterface {
    public List<Person> index();
    public Person show(int id);
    public void save(Person person);
    public void update(int id, Person updatedPerson);
    public void delete(int id);
}
