package hb.xm.service;

import hb.xm.entity.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {
    public User getUser(User user);
    public List<User> getUsers();

    public List<User> getUserfy(Integer start, Integer limit);

    public void addUser(User user);

    public void deleteUser(Integer userid);

    public void updateUser(User user);

    public List<User> gjSeleteUser(User user);

    public Page<User> gjSeleteUser(User user, Integer start, Integer limit);

}
