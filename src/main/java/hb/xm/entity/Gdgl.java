package hb.xm.entity;


import javax.persistence.*;

//工单管理
@Entity
@Table(name = "iotp_order")
public class  Gdgl {
    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer order_id;//工单Id

    @Column(name = "order_name")//工单名称
    private String order_name;

    @Column(name = "site_id ")//站点
    private Integer site_id;

    // @Column(name = "machine_name  ")//设备名称
    // private String machine_name;

    @Column(name = "order_machine")//检修设备
    private String order_machine;

    @Column(name = "order_problem_from")//问题来源
    private String order_problem_from;

    @Column(name = "order_desc")//状态
    private String order_desc;

    public Gdgl() {

    }

    public Gdgl(Integer order_id, String order_name, Integer site_id, String order_machine, String order_problem_from, String order_desc) {
        this.order_id = order_id;
        this.order_name = order_name;
        this.site_id = site_id;
        this.order_machine = order_machine;
        this.order_problem_from = order_problem_from;
        this.order_desc = order_desc;
    }

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public String getOrder_name() {
        return order_name;
    }

    public void setOrder_name(String order_name) {
        this.order_name = order_name;
    }

    public Integer getSite_id() {
        return site_id;
    }

    public void setSite_id(Integer site_id) {
        this.site_id = site_id;
    }

    // public String getMachine_name() {
    //     return machine_name;
    // }
    //
    // public void setMachine_name(String machine_name) {
    //     this.machine_name = machine_name;
    // }

    public String getOrder_machine() {
        return order_machine;
    }

    public void setOrder_machine(String order_machine) {
        this.order_machine = order_machine;
    }

    public String getOrder_problem_from() {
        return order_problem_from;
    }

    public void setOrder_problem_from(String order_problem_from) {
        this.order_problem_from = order_problem_from;
    }

    public String getOrder_desc() {
        return order_desc;
    }

    public void setOrder_desc(String order_desc) {
        this.order_desc = order_desc;
    }
}
