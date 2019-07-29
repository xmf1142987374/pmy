package hb.xm.service.impl;

import hb.xm.dao.Site_UserDao;
import hb.xm.entity.Site_User;
import hb.xm.service.Site_UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Site_UserServiceImpl implements Site_UserService {

    @Autowired
    private Site_UserDao site_userDao;

    @Override
    public List<Site_User> getSiteUser() {
        return site_userDao.findAll();
    }
}
