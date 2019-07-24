package hb.xm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="iotp_site_warning_sets")
public class Warning {
    @Id
    @Column(name = "SITE_ID")
    private Integer site_id;//站点编号
    @Column(name = "WARNING_TYPE")
    private String warning_type;//站点类型
    @Column(name = "WARNING_MAX")
    private String warning_max;//上限值倍数
    @Column(name = "WARNING_MIN")
    private String warning_min;//下限值倍数

    public Warning(Integer site_id, String warning_type, String warning_max, String warning_min) {
        this.site_id = site_id;
        this.warning_type = warning_type;
        this.warning_max = warning_max;
        this.warning_min = warning_min;
    }

    public Warning() {
    }

    public Integer getSite_id() {
        return site_id;
    }

    public void setSite_id(Integer site_id) {
        this.site_id = site_id;
    }

    public String getWarning_type() {
        return warning_type;
    }

    public void setWarning_type(String warning_type) {
        this.warning_type = warning_type;
    }

    public String getWarning_max() {
        return warning_max;
    }

    public void setWarning_max(String warning_max) {
        this.warning_max = warning_max;
    }

    public String getWarning_min() {
        return warning_min;
    }

    public void setWarning_min(String warning_min) {
        this.warning_min = warning_min;
    }
}
