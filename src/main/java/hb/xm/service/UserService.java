package hb.xm.service;

import hb.xm.entity.User;

import java.util.List;

public interface UserService {
    public User getUser(User user);
    public List<User> getUsers();
}
