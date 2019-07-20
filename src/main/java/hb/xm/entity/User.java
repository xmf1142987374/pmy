package hb.xm.entity;

import javax.naming.Name;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

//用户类
@Entity
@Table(name = "iotp_user")
public class User implements Serializable {
    @Id
    @Column(name = "USER_ID")
    private Integer userid;//用户id
    @Column(name = "USER_NAME")
    private String uname;//用户姓名
    @Column(name = "USER_LOGIN_NAME")
    private String username;//用户登录账号
    @Column(name = "USER_PASSWORD")
    private String password;//用户密码
    @Column(name = "USER_DEPARTMENT")
    private String user_dept;//用户所在部门
    @Column(name = "USER_GENDER")
    private String user_sex;//用户性别
    @Column(name = "USER_TEL")
    private String user_tel;//用户电话
    @Column(name = "USER_PHONE")
    private String user_phone;//用户手机号
    @Column(name = "USER_JUDGE_STATE")
    private String user_state;//用户状态
    @Column(name = "USER_ROLE_ID")
    private String role_id;//角色id
    @Column(name = "CREATE_TIME")
    private String create_time;//创建角色时间


    public User() {
    }

    public User(Integer userid, String uname, String username, String password, String user_dept, String user_sex, String user_tel, String user_phone, String user_state,  String create_time) {
        this.userid = userid;
        this.uname = uname;
        this.username = username;
        this.password = password;
        this.user_dept = user_dept;
        this.user_sex = user_sex;
        this.user_tel = user_tel;
        this.user_phone = user_phone;
        this.user_state = user_state;
        this.create_time = create_time;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUser_dept() {
        return user_dept;
    }

    public void setUser_dept(String user_dept) {
        this.user_dept = user_dept;
    }

    public String getUser_sex() {
        return user_sex;
    }

    public void setUser_sex(String user_sex) {
        this.user_sex = user_sex;
    }

    public String getUser_tel() {
        return user_tel;
    }

    public void setUser_tel(String user_tel) {
        this.user_tel = user_tel;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getUser_state() {
        return user_state;
    }

    public void setUser_state(String user_state) {
        this.user_state = user_state;
    }

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }
}
