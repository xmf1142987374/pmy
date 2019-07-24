package hb.xm.dao;

import hb.xm.entity.Warning;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WarningDao extends JpaRepository<Warning,Integer> {

    @Query(value="select * from iotp_site_warning_sets a join iotp_site b on a.SITE_ID=b.SITE_ID limit ?1,?2 ",nativeQuery = true)
    public List<Warning> getWarningfy(Integer start, Integer limit);
}
