package hb.xm.dao;

import hb.xm.entity.Role;
import hb.xm.entity.User;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserDao extends JpaRepository<User,Integer> , JpaSpecificationExecutor<User> {
     @Query("from  User  where username=:#{#user.username} and password=:#{#user.password}")
     public User getUser(User user);

     @Query(value = "select * from iotp_user limit ?1,?2 ",nativeQuery = true)
     public List<User> getUserfy(Integer start, Integer limit);

}
