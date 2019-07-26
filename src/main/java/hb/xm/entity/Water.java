package hb.xm.entity;


import javax.persistence.*;
import java.io.Serializable;

//水流量表
@Entity
@Table(name = "iotp_site_water")
public class Water implements Serializable {
    static final long serialVersionUID = 1L;
    @Id
    @Column(name = "water_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer water_id;//水流量id
    @Column(name = "site_id")
    private Integer site_id;//站点id
    @Column(name = "water_time")
    private String water_time;//水流量时间
    @Column(name = "water_num")
    private Integer water_num;//水流量

    public Water() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getWater_id() {
        return water_id;
    }

    public void setWater_id(Integer water_id) {
        this.water_id = water_id;
    }

    public Integer getSite_id() {
        return site_id;
    }

    public void setSite_id(Integer site_id) {
        this.site_id = site_id;
    }

    public String getWater_time() {
        return water_time;
    }

    public void setWater_time(String water_time) {
        this.water_time = water_time;
    }

    public Integer getWater_num() {
        return water_num;
    }

    public void setWater_num(Integer water_num) {
        this.water_num = water_num;
    }
}
