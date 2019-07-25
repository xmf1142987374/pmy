package hb.xm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//动力设备表

@Entity
@Table(name = "iotp_power_machine")
public class Sbb {
    @Id
    @Column(name = "SITE_ID")
    private String site_id; //所属站点

    @Column(name = "machine_state ")
    private String machine_state; //设备状态

    @Column(name = "machine_name")
    private String machine_name; //设备名称

    @Column(name = "monitor_time")
    private String monitor_time; //监测时间

    public String getSite_id() {
        return site_id;
    }

    public void setSite_id(String site_id) {
        this.site_id = site_id;
    }

    public String getMachine_state() {
        return machine_state;
    }

    public void setMachine_state(String machine_state) {
        this.machine_state = machine_state;
    }

    public String getMachine_name() {
        return machine_name;
    }

    public void setMachine_name(String machine_name) {
        this.machine_name = machine_name;
    }

    public String getMonitor_time() {
        return monitor_time;
    }

    public void setMonitor_time(String monitor_time) {
        this.monitor_time = monitor_time;
    }
}

