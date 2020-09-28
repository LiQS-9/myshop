package service;

import entity.User;

public interface AdminService {
    User findAdmin(String username, String password);
}
