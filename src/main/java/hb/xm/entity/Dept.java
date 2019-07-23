package hb.xm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

//部门表
@Entity
@Table(name = "iotp_department")
public class Dept implements Serializable {
    static final long serialVersionUID = 1L;
    @Id
    @Column(name = "DEP_ID")
    private Integer dep_id;//部门id
    @Column(name = "DEP_NAME")
    private String dep_name;//部门名称
    @Column(name = "DEP_PID")
    private String dep_pid;//上一级部门名称
    @Column(name = "DEP_DESC")
    private String dep_desc;//部门描述
    @Column(name = "DEP_STATE")
    private String dep_state;//部门状态
    @Column(name = "CREATE_USER")
    private String create_user;//创建者
    @Column(name = "CREATE_TIME")
    private String create_time;//创建时间
    @Column(name = "MODIFY_USER")
    private String modify_user;//修改者
    @Column(name = "MODIFY_TIME")
    private String modify_time;//修改时间

    public Dept() {

    }

    public Dept(Integer dep_id, String dep_name, String dep_desc, String dep_state, String create_user, String create_time) {
        this.dep_id = dep_id;
        this.dep_name = dep_name;
        this.dep_desc = dep_desc;
        this.dep_state = dep_state;
        this.create_user = create_user;
        this.create_time = create_time;
    }

    public Dept(Integer dep_id, String dep_name, String dep_desc, String dep_state, String create_user, String create_time, String modify_user, String modify_time) {
        this.dep_id = dep_id;
        this.dep_name = dep_name;
        this.dep_desc = dep_desc;
        this.dep_state = dep_state;
        this.create_user = create_user;
        this.create_time = create_time;
        this.modify_user = modify_user;
        this.modify_time = modify_time;
    }

    public Integer getDep_id() {
        return dep_id;
    }

    public void setDep_id(Integer dep_id) {
        this.dep_id = dep_id;
    }

    public String getDep_name() {
        return dep_name;
    }

    public void setDep_name(String dep_name) {
        this.dep_name = dep_name;
    }

    public String getDep_pid() {
        return dep_pid;
    }

    public void setDep_pid(String dep_pid) {
        this.dep_pid = dep_pid;
    }

    public String getDep_desc() {
        return dep_desc;
    }

    public void setDep_desc(String dep_desc) {
        this.dep_desc = dep_desc;
    }

    public String getDep_state() {
        return dep_state;
    }

    public void setDep_state(String dep_state) {
        this.dep_state = dep_state;
    }

    public String getCreate_user() {
        return create_user;
    }

    public void setCreate_user(String create_user) {
        this.create_user = create_user;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
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
