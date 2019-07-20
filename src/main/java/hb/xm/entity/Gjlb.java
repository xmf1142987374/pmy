package hb.xm.entity;

import org.springframework.stereotype.Controller;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

//告警列表
@Entity
@Table(name = "iotp_warning")
public class Gjlb implements Serializable {
    static final long serialVersionUID = 1L;
    @Id
    @Column(name = "warning_type")//告警分类
    private String warning_type;

    @Column(name = "site_id ")//站点ID
    private String site_id;

    @Column(name = "warning_level")//告警级别
    private String warning_level;

    @Column(name = "warning_desc")//告警内容
    private String warning_desc;

    @Column(name = "is_valid ")//是否有效
    private String is_valid;

    @Column(name = "warning_state")//是否处理
    private String warning_state;

    @Column(name = "operate_time")//告警时间
    private String operate_time;

    public Gjlb(){
        
    }

    public String getWarning_type() {
        return warning_type;
    }

    public void setWarning_type(String warning_type) {
        this.warning_type = warning_type;
    }

    public String getSite_id() {
        return site_id;
    }

    public void setSite_id(String site_id) {
        this.site_id = site_id;
    }

    public String getWarning_level() {
        return warning_level;
    }

    public void setWarning_level(String warning_level) {
        this.warning_level = warning_level;
    }

    public String getWarning_desc() {
        return warning_desc;
    }

    public void setWarning_desc(String warning_desc) {
        this.warning_desc = warning_desc;
    }

    public String getIs_valid() {
        return is_valid;
    }

    public void setIs_valid(String is_valid) {
        this.is_valid = is_valid;
    }

    public String getWarning_state() {
        return warning_state;
    }

    public void setWarning_state(String warning_state) {
        this.warning_state = warning_state;
    }

    public String getOperate_time() {
        return operate_time;
    }

    public void setOperate_time(String operate_time) {
        this.operate_time = operate_time;
    }
}
