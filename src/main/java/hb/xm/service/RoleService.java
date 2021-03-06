package hb.xm.service;

import hb.xm.entity.Role;

import java.util.List;

public interface RoleService {
    public List<Role> getRole();

    public List<Role> getRolefy(Integer start, Integer limit);

    public void addRole(Role role);

    public void deleteRole(Integer role_id);

    public void updateRole(Role role);
}
