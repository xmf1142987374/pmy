package hb.xm.controller;

import hb.xm.entity.Kqset;
import hb.xm.entity.Site;
import hb.xm.service.SiteService;
import hb.xm.service.XwkqService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class XwkqController {
    @Autowired
    private XwkqService xwkqService;
    @Autowired
    private SiteService siteService;

    //ajax请求查询考勤规则
    @ResponseBody
    @RequestMapping("selsitenames")
    public String selsitenames(){
        List<Site> sites=siteService.getSites();
        JSONArray data=JSONArray.fromObject(sites);
        System.out.println(data);
        return data.toString();
    }
}
