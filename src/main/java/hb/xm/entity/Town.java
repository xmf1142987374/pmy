package hb.xm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
//村镇实体表

@Entity
@Table(name = "iotp_town")
public class Town {
    @Id
    @Column(name = "town_id")
    private String town_id; //村镇行政码
    @Column(name = "TOWN_NAME")
    private String town_name;//村镇名
    @Column(name = "TOWN_X_NUM")
    private String town_x_num;//村镇经度
    @Column(name = "TOWN_Y_NUM")
    private String town_y_num;//村镇纬度
    @Column(name = "TOWN_PID")
    private String town_pid;//村镇父级行政码
    private String site_type;

    public Town() {
    }

    public String getSite_type() {
        return site_type;
    }

    public void setSite_type(String site_type) {
        this.site_type = site_type;
    }


    public String getTown_id() {
        return town_id;
    }

    public void setTown_id(String town_id) {
        this.town_id = town_id;
    }

    public String getTown_name() {
        return town_name;
    }

    public void setTown_name(String town_name) {
        this.town_name = town_name;
    }

    public String getTown_x_num() {
        return town_x_num;
    }

    public void setTown_x_num(String town_x_num) {
        this.town_x_num = town_x_num;
    }

    public String getTown_y_num() {
        return town_y_num;
    }

    public void setTown_y_num(String town_y_num) {
        this.town_y_num = town_y_num;
    }

    public String getTown_pid() {
        return town_pid;
    }

    public void setTown_pid(String town_pid) {
        this.town_pid = town_pid;
    }
}
