package hb.xm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

//角色表
@Entity
@Table(name="iotp_role")
public class Role implements Serializable {
    static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ROLE_ID")
    private Integer role_id;//角色id
    @Column(name = "ROLE_NAME")
    private String role_name;//角色名称
    @Column(name = "ROLE_STATE")
    private String role_state;//角色状态
    @Column(name = "CREATE_TIME")
    private String create_time;//角色创建时间
    @Column(name = "CREATE_USER")
    private String create_user;//角色创建者
    @Column(name = "MODIFY_USER")
    private String modify_user;//角色修改者
    @Column(name = "MODIFY_TIME")
    private String modify_time;//修改时间

    public Role() {
    }

    public Role(Integer role_id, String role_name, String role_state, String create_time, String create_user) {
        this.role_id = role_id;
        this.role_name = role_name;
        this.role_state = role_state;
        this.create_time = create_time;
        this.create_user = create_user;
    }

    public Integer getRole_id() {
        return role_id;
    }

    public void setRole_id(Integer role_id) {
        this.role_id = role_id;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public String getRole_state() {
        return role_state;
    }

    public void setRole_state(String role_state) {
        this.role_state = role_state;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getCreate_user() {
        return create_user;
    }

    public void setCreate_user(String create_user) {
        this.create_user = create_user;
    }

    public String getModify_user() {
        return modify_user;
    }

    public void setModify_user(String modify_user) {
        this.modify_user = modify_user;
    }

    public String getModify_time() {
        return modify_time;
    }

    public void setModify_time(String modify_time) {
        this.modify_time = modify_time;
    }
}
