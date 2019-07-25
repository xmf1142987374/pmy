package hb.xm.dao;
//工单管理

import hb.xm.entity.Gdgl;
import hb.xm.entity.Gjlb;
import hb.xm.entity.Xwkq;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GdglDao extends JpaRepository<Gdgl, Integer> {
    @Query(value = "select * from iotp_order a left JOIN iotp_site b ON  a.site_id=b.SITE_ID limit ?1,?2", nativeQuery = true)
    public List<Gdgl> getgdglfy(Integer start, Integer limit);

    @Query(value = "select * from iotp_order a JOIN iotp_site b ON  a.site_id=b.SITE_ID", nativeQuery = true)
    public List<Gdgl> getgdgls();
}
