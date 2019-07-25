package hb.xm.controller;

import hb.xm.entity.Site;
import hb.xm.entity.Town;
import hb.xm.entity.User;
import hb.xm.entity.Xwkq;
import hb.xm.service.SiteService;
import hb.xm.service.TownService;
import javafx.scene.control.Alert;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

//处理站点关于站点信息的请求
@Controller
public class SiteController {
    @Autowired
    private TownService townService;
    @Autowired
    private SiteService siteService;
    @ResponseBody
    @RequestMapping("selSiteByName")
    public String selSiteByName(@RequestParam("sitename") String sitename){
        List<Town> towns=townService.getTowns();
        List<Site> sites=siteService.getSiteByName(sitename);
        JSONArray data=new JSONArray();
        for (int i = 0; i <sites.size() ; i++) {
            JSONObject jsonObject=new JSONObject();
            jsonObject=JSONObject.fromObject(sites.get(i));
            for(Town town:towns){
                if (sites.get(i).getSite_location().equals(town.getTown_name())){
                    jsonObject.put("site_pic",town.getSite_pic());
                    jsonObject.put("town_x_num",town.getTown_x_num());
                    jsonObject.put("town_y_num",town.getTown_y_num());
                }
            }
            data.add(jsonObject);
        }
        return  data.toString();
    }

    //ajax请求查询站点数据
    @ResponseBody
    @RequestMapping("selSite")
    public String selSite(@RequestParam("start")Integer start, @RequestParam("limit") Integer limit){
        List<Town> towns=townService.getTowns();
        List<Site> sites=siteService.getSitefy(start,limit);
        JSONArray data= new JSONArray();
        for (int i = 0; i <sites.size() ; i++) {
            JSONObject jsonObject=new JSONObject();
            jsonObject=JSONObject.fromObject(sites.get(i));
            for(Town town:towns){
                if(town.getTown_name().toString().equals(sites.get(i).getSite_location().toString())){
                    jsonObject.put("town_x_num",town.getTown_x_num());
                    jsonObject.put("town_y_num",town.getTown_y_num());
                }
            }

            /*for (User user:users){
                if (user.getUserid().toString().equals(xwkqs.get(i).getUser_id().toString())){
                    jsonObject.put("uname",user.getUname());
                }

            }*/
            data.add(jsonObject);
        }
        String datas="{totalCount:"+siteService.getSites().size()+",data:"+data.toString()+"}";
        return datas;
    }
}
