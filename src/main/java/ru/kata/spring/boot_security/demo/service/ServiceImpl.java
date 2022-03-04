package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.Dao.UserDAO;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Transactional
    public User findByUserName (String username){
        return userDAO.findByUsername(username);
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        userDAO.save(user);
    }

    @Override
    @Transactional
    public void removeUserById(long id) {
        userDAO.deleteById(id);
    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
        return userDAO.findAll();
    }

    @Override
    @Transactional
    public User getUserById(long id) {
        return userDAO.getById(id);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        userDAO.save(user);
    }

    @Transactional
    public void updateUserById(long id, User user){
        User newUser = userDAO.getById(id);
        newUser.setAge(user.getAge());
        newUser.setUsername(user.getUsername());
        newUser.setJob(user.getJob());
        newUser.setRoles(user.getRoles());
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUserName(username);
        if(user == null){
            throw new UsernameNotFoundException(String.format("'%s' not found", username));
        }
        return user;
    }

}