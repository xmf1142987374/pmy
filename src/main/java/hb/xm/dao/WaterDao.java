package hb.xm.dao;

import hb.xm.entity.Water;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WaterDao extends JpaRepository<Water,Integer> {
@Query(value = "SELECT * from iotp_site_water a left  join iotp_site b on  a.site_id=b.SITE_ID where b.SITE_NAME= ?1",nativeQuery = true)
    public List<Water> getWaters(String site_name);
}
