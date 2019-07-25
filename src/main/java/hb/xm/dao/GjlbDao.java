package hb.xm.dao;


import hb.xm.entity.Gjlb;
import hb.xm.entity.Site;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GjlbDao extends JpaRepository<Gjlb, Integer> {
    @Query(value = "select * from iotp_warning a left JOIN iotp_site b ON  a.site_id=b.SITE_ID limit ?1,?2", nativeQuery = true)
    public List<Gjlb> getgjlbfy(Integer start, Integer limit);
}
