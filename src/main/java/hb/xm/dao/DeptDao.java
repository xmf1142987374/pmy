package hb.xm.dao;

import hb.xm.entity.Dept;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeptDao extends JpaRepository<Dept,Integer> {
}
