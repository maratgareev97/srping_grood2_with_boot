package ru.springmavc.crud.dao;

import ru.springmavc.crud.models.Role;
import java.util.List;
import java.util.Set;

public interface RoleDAO {
    List<Role> getAllRoles ();
    void addRole(Role role);
    Role findById(long id);
    Set<Role> findByIdRoles(List<Long>roles);
}