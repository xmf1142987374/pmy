package hb.xm.service.impl;

import hb.xm.dao.RoleDao;
import hb.xm.entity.Role;
import hb.xm.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;

    @Override
    public List<Role> getRole() {
        return roleDao.findAll();
    }

    @Override
    public List<Role> getRolefy(Integer start, Integer limit) {
        return roleDao.getRolefy(start,limit);
    }

    @Override
    public void addRole(Role role) {
        roleDao.save(role);
    }

    @Override
    public void deleteRole(Integer role_id) {
        roleDao.deleteById(role_id);
    }

    @Override
    public void updateRole(Role role) {
        roleDao.saveAndFlush(role);
    }
}
