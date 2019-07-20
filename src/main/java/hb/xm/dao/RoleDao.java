package hb.xm.dao;

import hb.xm.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoleDao extends JpaRepository<Role,Integer> {
    @Query(value = "select * from iotp_role limit ?1,?2 ",nativeQuery = true)
    public List<Role> getRolefy(Integer start, Integer limit);
}
