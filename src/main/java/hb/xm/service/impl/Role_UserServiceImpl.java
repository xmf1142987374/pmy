package hb.xm.service.impl;

import hb.xm.dao.Role_UserDao;
import hb.xm.entity.Role_User;
import hb.xm.service.Role_UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Role_UserServiceImpl implements Role_UserService {

    @Autowired
    private Role_UserDao role_userDao;

    @Override
    public List<Role_User> getRoleUser() {
        return role_userDao.findAll();
    }
}
