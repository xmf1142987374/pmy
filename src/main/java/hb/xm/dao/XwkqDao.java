package hb.xm.dao;

import hb.xm.entity.Xwkq;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface XwkqDao extends JpaRepository<Xwkq,Integer>{
   @Query(value="select * from iotp_site a join iotp_site_log b on a.SITE_ID=b.SITE_ID limit ?1,?2 ",nativeQuery = true)
   public List<Xwkq> getXwkqfy(Integer start,Integer limit);
}
