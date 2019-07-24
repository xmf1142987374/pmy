package hb.xm.controller;

import hb.xm.entity.Site;
import hb.xm.entity.Warning;
import hb.xm.service.SiteService;
import hb.xm.service.WarningService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


//流量告警
@Controller
public class WarningController {

    @Autowired
    private WarningService warningService;
    @Autowired
    private SiteService siteService;

    //ajax请求查询流量告警数据
    @ResponseBody
    @RequestMapping("selWarning")
    public String selWarning(@RequestParam("start")Integer start, @RequestParam("limit") Integer limit){
        List<Site> sites=siteService.getSites();
        List<Warning> warnings=warningService.getWarningsfy(start,limit);
        JSONArray data= new JSONArray();
        for (int i = 0; i <warnings.size() ; i++) {
            JSONObject jsonObject=new JSONObject();
            jsonObject=JSONObject.fromObject(warnings.get(i));
            for(Site site:sites){
                if(site.getSite_id().toString().equals(warnings.get(i).getSite_id().toString())){
                    jsonObject.put("site_name",site.getSite_name());
                    jsonObject.put("site_type",site.getSite_type());
                }
            }

            /*for (User user:users){
                if (user.getUserid().toString().equals(xwkqs.get(i).getUser_id().toString())){
                    jsonObject.put("uname",user.getUname());
                }

            }*/
            data.add(jsonObject);
        }
        String datas="{totalCount:"+warningService.getWarnings().size()+",data:"+data.toString()+"}";
        return datas;
    }
}
