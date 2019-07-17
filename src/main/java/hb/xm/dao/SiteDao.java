package hb.xm.dao;

import hb.xm.entity.Site;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SiteDao extends JpaRepository<Site,Integer> {
    // @Query(value = "select SITE_NAME form iotp_site",nativeQuery = true)
    // public List<String> getSiteNames();
}
