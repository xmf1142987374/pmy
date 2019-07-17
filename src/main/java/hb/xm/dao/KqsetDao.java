package hb.xm.dao;

import hb.xm.entity.Kqset;
import hb.xm.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface KqsetDao extends JpaRepository<Kqset,Integer> {
    @Query(value = "select * from iotp_site_log_sets limit ?1,?2 ",nativeQuery = true)
    public List<Kqset> getKqsetsfy(Integer start,Integer limit);
}
