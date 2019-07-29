package hb.xm.dao;

import hb.xm.entity.Site;
import hb.xm.entity.Site_User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Site_UserDao extends JpaRepository<Site_User,Integer> {

    /*@Query(value="select * from iotp_user_site_rel limit ?1,?2 ",nativeQuery = true)
    public List<Site> getSiteUserfy(Integer start, Integer limit);*/
}
