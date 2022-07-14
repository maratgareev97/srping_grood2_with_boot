package ru.springmavc.crud.dao;

import ru.springmavc.crud.models.Users;

import java.util.List;
//@Transactional
public interface UsersDAOInterface {
    public List<Users> index();
    public Users show(int id);
    public void save(Users users);
    public void update(int id, Users updatedUsers);
    public void delete(int id);
}
