//工单管理
package hb.xm.controller;

import hb.xm.entity.Gdgl;
import hb.xm.entity.Gjlb;
import hb.xm.entity.Sbb;
import hb.xm.entity.Site;
import hb.xm.service.GdglService;
import hb.xm.service.GjlbService;
import hb.xm.service.SbbService;
import hb.xm.service.SiteService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class GdglController {
    @Autowired
    private GdglService gdglService;
    @Autowired
    private SiteService siteService;
    @Autowired
    private SbbService sbbService;
    @Autowired
    private GjlbService gjlbService;


    @ResponseBody//查询
    @RequestMapping("all")
    public String findAll(@RequestParam("start") Integer start, @RequestParam("limit") Integer limit) {
        List<Gdgl> gdgls = gdglService.getgjlbfy(start, limit); //分页
        List<Site> sites = siteService.getSiteAreas();
        List<Sbb> sbbs = sbbService.getSbb();
        JSONArray datas = new JSONArray();
        for (int i = 0; i < gdgls.size(); i++) {
            JSONObject jsonObject = new JSONObject();
            jsonObject = JSONObject.fromObject(gdgls.get(i));
            for (Site site : sites) {
                if (site.getSite_id().toString().equals(gdgls.get(i).getSite_id().toString())) {
                    jsonObject.put("site_name", site.getSite_name());
                }
            }
            for (Sbb sbb : sbbs) {
                if (sbb.getSite_id().toString().equals(gdgls.get(i).getSite_id().toString())) {
                    jsonObject.put("machine_name", sbb.getMachine_name());
                }
            }
            datas.add(jsonObject);
        }
        String data = "{totalCount:" + gdglService.getGdgl().size() + ",data:" + datas.toString() + "}";
        return data;
    }

    //添加工单
    @ResponseBody
    @RequestMapping(value = "addgdgl")
    public void addGdgl(@RequestParam("order_name") String order_name,
                        @RequestParam("site_name") String site_name,
                        @RequestParam("order_machine") String order_machine,
                        @RequestParam("order_problem_from") String order_problem_from) {
        Gdgl gdgl = new Gdgl();
        Site site = new Site();
        Integer s_id = null;
        List<Gdgl> gdgls = gdglService.getGdgl();
        List<Site> sites = siteService.getSites();
        for (Site s : sites) {
            if (s.getSite_name().equals(site_name)) {
                s_id = s.getSite_id();
            }
        }
        gdgl.setOrder_name(order_name);
        gdgl.setOrder_machine(order_machine);
        gdgl.setOrder_problem_from(order_problem_from);
        gdgl.setSite_id(s_id);
        gdglService.addGdgl(gdgl);
    }

    //添加设备工单
    @ResponseBody
    @RequestMapping(value = "addgd")
    public void addgd(@RequestParam("warning_type") String order_name,
                      @RequestParam("site_name") String site_name,
                      @RequestParam("machine_name") String order_machine,
                      @RequestParam("warning_desc") String order_problem_from) {
        Gdgl gdgl = new Gdgl();
        Gjlb gjlb =new Gjlb();
        Integer s_id = null;
        List<Gdgl> gdgls = gdglService.getGdgl();
        List<Gjlb> gjlbs = gjlbService.getGjlb();
        List<Site> sites = siteService.getSites();
        for (Site s : sites) {
            if (s.getSite_name().equals(site_name)) {
                s_id = s.getSite_id();
            }
        }
        gdgl.setOrder_name(order_name);
        gdgl.setOrder_machine(order_machine);
        gdgl.setSite_id(s_id);
        gdgl.setOrder_problem_from(order_problem_from);
        gdglService.addGdgl(gdgl);
    }

    //ajax请求删除选择工单
    @ResponseBody
    @RequestMapping("delegdgl")
    public void deleteGdgl(@RequestParam("data") Integer[] data) {
        for (int i = 0; i < data.length; i++) {
            gdglService.deleteGdgl(data[i]);
        }
    }
}
