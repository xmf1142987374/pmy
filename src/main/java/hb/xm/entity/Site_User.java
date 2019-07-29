package hb.xm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


@Entity
@Table(name="iotp_user_site_rel")
public class Site_User implements Serializable {

    static final long serialVersionUID = 1L;
    @Id
    @Column(name = "SITE_ID")
    private Integer site_id;
    @Column(name = "USER_ID")
    private Integer user_id;

    public Integer getSite_id() {
        return site_id;
    }

    public void setSite_id(Integer site_id) {
        this.site_id = site_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }
}
