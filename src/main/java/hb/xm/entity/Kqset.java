package hb.xm.entity;

import javax.persistence.*;

//考勤规则
@Entity
@Table(name = "iotp_site_log_sets")
public class Kqset {
    @Id
    @Column(name = "sets_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer sets_id;//规则设置id
    @Column(name = "site_type")
    private String site_type;//站点类型
    @Column(name = "valid_time")
    private String valid_time;//有效时长
    @Column(name = "log_cycle")
    private String log_cycle;//周期
    @Column(name = "log_count")
    private String log_count;//次数
    @Column(name = "valid_start_time")
    private String valid_start_time;//有效开始时间
    @Column(name = "valid_end_time")
    private String valid_end_time;//有效结束时间

    public Kqset() {
    }

    public Kqset(String site_type, String valid_time, String log_cycle, String log_count, String valid_start_time, String valid_end_time) {
        this.site_type = site_type;
        this.valid_time = valid_time;
        this.log_cycle = log_cycle;
        this.log_count = log_count;
        this.valid_start_time = valid_start_time;
        this.valid_end_time = valid_end_time;
    }

    public Integer getSets_id() {
        return sets_id;
    }

    public void setSets_id(Integer sets_id) {
        this.sets_id = sets_id;
    }

    public String getSite_type() {
        return site_type;
    }

    public void setSite_type(String site_type) {
        this.site_type = site_type;
    }

    public String getValid_time() {
        return valid_time;
    }

    public void setValid_time(String valid_time) {
        this.valid_time = valid_time;
    }

    public String getLog_cycle() {
        return log_cycle;
    }

    public void setLog_cycle(String log_cycle) {
        this.log_cycle = log_cycle;
    }

    public String getLog_count() {
        return log_count;
    }

    public void setLog_count(String log_count) {
        this.log_count = log_count;
    }

    public String getValid_start_time() {
        return valid_start_time;
    }

    public void setValid_start_time(String valid_start_time) {
        this.valid_start_time = valid_start_time;
    }

    public String getValid_end_time() {
        return valid_end_time;
    }

    public void setValid_end_time(String valid_end_time) {
        this.valid_end_time = valid_end_time;
    }
}
