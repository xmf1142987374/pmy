package hb.xm.service.impl;

import hb.xm.dao.UserDao;
import hb.xm.entity.User;
import hb.xm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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
    public List<User> getUserfy(Integer start, Integer limit) {
        return userDao.getUserfy(start,limit);
    }

    @Override
    public void addUser(User user) {
        userDao.save(user);
    }

    @Override
    public void deleteUser(Integer userid) {
        userDao.deleteById(userid);
    }

    @Override
    public void updateUser(User user) {
        userDao.saveAndFlush(user);
    }

    @Override
    public List<User> gjSeleteUser(User user) {
        return userDao.findAll(new Specification<User>() {

            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate pc = cb.conjunction();
                if (user != null) {
                    if (user.getUname() != null && !StringUtils.isEmpty(user.getUname())) {
                        pc.getExpressions().add(cb.like(root.get("uname"), "%" + user.getUname() + "%"));
                    }
                    if (null != user.getUsername() && !StringUtils.isEmpty(user.getUsername())) {
                        pc.getExpressions().add(cb.like(root.get("username"), "%" + user.getUsername() + "%"));
                    }
                    if (null != user.getUser_sex() && !StringUtils.isEmpty(user.getUser_sex())) {
                        pc.getExpressions().add(cb.like(root.get("user_sex"), "%" + user.getUser_sex() + "%"));
                    }
                }
                return pc;
            }
        });
    }
}
