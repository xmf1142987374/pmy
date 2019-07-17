package hb.xm.dao;

import hb.xm.entity.Site;
import hb.xm.entity.Xwkq;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface XwkqDao extends JpaRepository<Xwkq,Integer> {


}
