package hb.xm.dao;

import hb.xm.entity.Site;
import hb.xm.entity.Xwkq;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SiteDao extends JpaRepository<Site,Integer> {
    @Query(value="select * from iotp_site a join iotp_site_log b on a.SITE_ID=b.SITE_ID",nativeQuery = true)
    public List<Site> getSiteAreas();

    @Query(value = "select * FROM iotp_site a right JOIN iotp_town b on a.SITE_LOCATION=b.TOWN_NAME where SITE_LOCATION= ?1",nativeQuery = true)
    public List<Site> getSiteByName(String sitename);

    @Query(value="select * from iotp_site a join iotp_town b on a.SITE_LOCATION=b.TOWN_NAME limit ?1,?2 ",nativeQuery = true)
    public List<Site> getSitefy(Integer start, Integer limit);
}
