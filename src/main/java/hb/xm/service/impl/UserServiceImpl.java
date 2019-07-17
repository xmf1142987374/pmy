package hb.xm.service.impl;

import hb.xm.dao.UserDao;
import hb.xm.entity.User;
import hb.xm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public User getUser(User user) {
        return userDao.getUser(user);
    }

    @Override
    public List<User> getUsers() {
        return userDao.findAll();
    }

    @Override
    public void addUser(User user) {
        userDao.save(user);
    }
}
