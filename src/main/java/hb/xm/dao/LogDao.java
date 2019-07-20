package hb.xm.dao;

import hb.xm.entity.Log;
import hb.xm.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface LogDao extends JpaRepository<Log,Integer> {
    @Transactional
    @Modifying
    @Query(value = "insert into iotp_log(USER_NAME,LOG_TYPE,LOG_DESC,USER_ID,LOG_TIME,USER_IP,IS_SUCCESS) values(?1,?2,?3,?4,?5,?6,?7)",nativeQuery = true)
    public void addLog(@Param("user_name") String user_name,@Param("log_type") String log_type,@Param("log_desc") String log_desc,@Param("user_id") Integer user_id,@Param("log_time") String log_time,@Param("user_ip") String user_ip,@Param("is_success") String is_success);

    /*@Query(value = "insert into iotp_log(LOG_TYPE,LOG_DESC,USER_ID,LOG_TIME,USER_IP,IS_SUCCESS) values(log_type,log_desc,user_id,log_time,user_ip,is_success)",nativeQuery = true)
    public void addLog(@Param("log_type") String log_type,@Param("log_desc") String log_desc,@Param("user_id") String user_id,@Param("log_time") String log_time,@Param("user_ip") String user_ip,@Param("is_success") String is_success);*/

    @Query(value = "select * from iotp_log limit ?1,?2 ",nativeQuery = true)
    public List<Log> getLogfy(Integer start, Integer limit);
}
