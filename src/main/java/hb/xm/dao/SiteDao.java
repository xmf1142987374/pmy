package hb.xm.dao;

import hb.xm.entity.Site;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SiteDao extends JpaRepository<Site,Integer> {
    @Query(value="select * from iotp_site a join iotp_site_log b on a.SITE_ID=b.SITE_ID",nativeQuery = true)
    public List<Site> getSiteAreas();
}
