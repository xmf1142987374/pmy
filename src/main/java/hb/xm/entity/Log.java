package hb.xm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//日志表
@Entity
@Table(name="iotp_log")
public class Log {
    @Id
    @Column(name="LOG_ID")
    private Integer log_id;//日志id
    @Column(name="LOG_TYPE")
    private String log_type;//日志类型
    @Column(name="LOG_DESC")
    private String log_desc;//日志内容
    @Column(name="USER_ID")
    private String user_id;//操作者id
    @Column(name="LOG_TIME")
    private String log_time;//日志记录时间
    @Column(name="USER_IP")
    private String user_ip;//操作者ip
    @Column(name="IS_SUCCESS")
    private String is_success;//操作是否成功

    public Log() {
    }

    public Log(Integer log_id, String log_type, String log_desc, String user_id, String log_time, String user_ip, String is_success) {
        this.log_id = log_id;
        this.log_type = log_type;
        this.log_desc = log_desc;
        this.user_id = user_id;
        this.log_time = log_time;
        this.user_ip = user_ip;
        this.is_success = is_success;
    }

    public Log(String log_type, String log_desc, String user_id, String log_time, String user_ip, String is_success) {
        this.log_type = log_type;
        this.log_desc = log_desc;
        this.user_id = user_id;
        this.log_time = log_time;
        this.user_ip = user_ip;
        this.is_success = is_success;
    }

    public Integer getLog_id() {
        return log_id;
    }

    public void setLog_id(Integer log_id) {
        this.log_id = log_id;
    }

    public String getLog_type() {
        return log_type;
    }

    public void setLog_type(String log_type) {
        this.log_type = log_type;
    }

    public String getLog_desc() {
        return log_desc;
    }

    public void setLog_desc(String log_desc) {
        this.log_desc = log_desc;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getLog_time() {
        return log_time;
    }

    public void setLog_time(String log_time) {
        this.log_time = log_time;
    }

    public String getUser_ip() {
        return user_ip;
    }

    public void setUser_ip(String user_ip) {
        this.user_ip = user_ip;
    }

    public String getIs_success() {
        return is_success;
    }

    public void setIs_success(String is_success) {
        this.is_success = is_success;
    }
}
