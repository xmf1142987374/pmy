package hb.xm.dao;

import hb.xm.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserDao extends JpaRepository<User,Integer> {
     @Query("from  User  where username=:#{#user.username} and password=:#{#user.password}")
     public User getUser(User user);
}
