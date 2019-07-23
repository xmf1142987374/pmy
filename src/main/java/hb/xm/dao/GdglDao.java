package hb.xm.dao;
//工单管理

import hb.xm.entity.Gdgl;
import hb.xm.entity.Xwkq;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GdglDao extends JpaRepository<Gdgl, Integer> {

}
