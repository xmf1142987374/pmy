package hb.xm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//站点考勤纪录
@Entity
@Table(name = "iotp_site_log")
public class Xwkq {
    @Id
    @Column(name = "SITE_ID")
    private  String site_id;//站点id
    @Column(name = "USER_ID")
    private  String user_id;//巡维人员号（用户id）
    @Column(name = "Arrive_TIME")
    private  String arrive_time;//巡维人员到达时间
    @Column(name = "leave_time")
    private  String leave_time;//巡维人员离开时间
    @Column(name = "is_vaild")
    private  String is_vaild;//考勤状态

    public Xwkq() {
    }

    public Xwkq(String site_id, String user_id, String arrive_time, String leave_time, String is_vaild) {
        this.site_id = site_id;
        this.user_id = user_id;
        this.arrive_time = arrive_time;
        this.leave_time = leave_time;
        this.is_vaild = is_vaild;
    }

    public String getSite_id() {
        return site_id;
    }

    public void setSite_id(String site_id) {
        this.site_id = site_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getArrive_time() {
        return arrive_time;
    }

    public void setArrive_time(String arrive_time) {
        this.arrive_time = arrive_time;
    }

    public String getLeave_time() {
        return leave_time;
    }

    public void setLeave_time(String leave_time) {
        this.leave_time = leave_time;
    }

    public String getIs_vaild() {
        return is_vaild;
    }

    public void setIs_vaild(String is_vaild) {
        this.is_vaild = is_vaild;
    }
}
