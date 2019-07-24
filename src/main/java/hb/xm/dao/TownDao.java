package hb.xm.dao;

import hb.xm.entity.Site;
import hb.xm.entity.Town;
import hb.xm.entity.Xwkq;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TownDao extends JpaRepository<Town,Integer> {
    @Query(value = "select * from iotp_town a right join iotp_site b on a.TOWN_NAME=b.SITE_LOCATION ",nativeQuery = true)
    public List<Town> getTowns();

    @Query(value="select * from iotp_town a join iotp_site b on a.SITE_LOCATION=b.TOWN_NAME limit ?1,?2 ",nativeQuery = true)
    public List<Town> getTownsfy(Integer start, Integer limit);
}
