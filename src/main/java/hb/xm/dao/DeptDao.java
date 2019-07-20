package hb.xm.dao;

import hb.xm.entity.Dept;
import hb.xm.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DeptDao extends JpaRepository<Dept,Integer> {

    @Query(value = "select * from iotp_department limit ?1,?2 ",nativeQuery = true)
    public List<Dept> getDeptfy(Integer start, Integer limit);
}
