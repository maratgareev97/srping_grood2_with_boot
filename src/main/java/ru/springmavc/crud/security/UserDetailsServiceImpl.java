package ru.springmavc.crud.security;

import ru.springmavc.crud.dao.UserDAO;
import ru.springmavc.crud.models.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service()
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    UserDAO userDAO;

    public UserDetailsServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userDAO.getUserByLogin(s);
        if(user == null){
            throw new UsernameNotFoundException("user not found");
        }
        return user;
    }
}