package hb.xm.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//站点
@Entity
@Table(name = "iotp_site")
public class Site implements Serializable {
    @Id
    @Column(name = "SITE_ID")
    private String site_id;//站点id
    @Column(name = "SITE_NAME")
    private String site_name;//站点名称
    @Column(name = "SITE_LOCATION")
    private String site_location;//站点地址
    @Column(name = "SITE_TYPE")
    private String site_type;//站点类型
    @Column(name = "SITE_DESC")
    private String site_desc;//站点描述
    @Column(name = "SITE_PIC")
    private String site_pic;//站点图片


    public Site() {
    }

    public Site(String site_id, String site_name, String site_location, String site_type, String site_desc, String site_pic) {
        this.site_id = site_id;
        this.site_name = site_name;
        this.site_location = site_location;
        this.site_type = site_type;
        this.site_desc = site_desc;
        this.site_pic = site_pic;
    }



    public String getSite_id() {
        return site_id;
    }

    public void setSite_id(String site_id) {
        this.site_id = site_id;
    }

    public String getSite_name() {
        return site_name;
    }

    public void setSite_name(String site_name) {
        this.site_name = site_name;
    }

    public String getSite_location() {
        return site_location;
    }

    public void setSite_location(String site_location) {
        this.site_location = site_location;
    }

    public String getSite_type() {
        return site_type;
    }

    public void setSite_type(String site_type) {
        this.site_type = site_type;
    }

    public String getSite_desc() {
        return site_desc;
    }

    public void setSite_desc(String site_desc) {
        this.site_desc = site_desc;
    }

    public String getSite_pic() {
        return site_pic;
    }

    public void setSite_pic(String site_pic) {
        this.site_pic = site_pic;
    }
}
