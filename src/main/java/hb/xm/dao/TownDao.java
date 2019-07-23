package hb.xm.dao;

import hb.xm.entity.Town;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TownDao extends JpaRepository<Town,Integer> {
    @Query(value = "select * from iotp_town a right join iotp_site b on a.TOWN_NAME=b.SITE_LOCATION ",nativeQuery = true)
    public List<Town> getTowns();
}
