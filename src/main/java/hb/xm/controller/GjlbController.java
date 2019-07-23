package hb.xm.controller;


import hb.xm.entity.Gjlb;

import hb.xm.entity.Site;
import hb.xm.service.GjlbService;
import hb.xm.service.SiteService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
//告警列表数据处理
@Controller
public class GjlbController {
    @Autowired
    private GjlbService gjlbService;
    @Autowired
    private SiteService siteService;
    //ajax请求查询告警列表所有数据
    @ResponseBody
    @RequestMapping("findAll")
    public String findAll() {
        List<Gjlb> gjlbs = gjlbService.getGjlb();
        List<Site> sites = siteService.getSiteAreas();
        JSONArray data = new JSONArray();
        for (int i = 0; i < gjlbs.size(); i++) {
            JSONObject jsonObject = new JSONObject();
            jsonObject = JSONObject.fromObject(gjlbs.get(i));
            for (Site site : sites) {
                if (site.getSite_id().toString().equals(gjlbs.get(i).getSite_id().toString())) {
                    jsonObject.put("site_location", site.getSite_location());
                }
            }
            data.add(jsonObject);
        }
        //String datas = "{totalCount:" + gjlbService.getGjlb().size() + ",data:" + data.toString() + "}";分页
        return data.toString();
    }

    //ajax请求删除告警列表选择数据
    @ResponseBody
    @RequestMapping("delegjlb")
    public void deleteGjlb(@RequestParam("data") Integer[] data) {
        for (int i = 0; i < data.length; i++) {
            gjlbService.deleteGjlb(data[i]);
        }
    }

}
