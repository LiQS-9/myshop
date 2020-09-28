package service;

import entity.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    int delUser(int nid);

    List<User> findByNameSex(String username, String gender);

    boolean check(String username);

    int addUser(User user);

    int activeUser(String decode);

    User login(String username, String password);

    List<User> findAllInvalid();
}
