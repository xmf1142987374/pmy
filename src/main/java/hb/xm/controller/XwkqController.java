package hb.xm.controller;
//请求考勤规则
import hb.xm.entity.Site;
import hb.xm.entity.User;
import hb.xm.entity.Xwkq;
import hb.xm.service.SiteService;
import hb.xm.service.UserService;
import hb.xm.service.XwkqService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
public class XwkqController {
    @Autowired
    private XwkqService xwkqService;
    @Autowired
    private SiteService siteService;
    @Autowired
    private UserService userService;

    //ajax请求查询站点名
    @ResponseBody
    @RequestMapping("selSiteNames")
    public String selSiteNames(){
        List<Site> sites=siteService.getSiteNames();
        JSONArray data= JSONArray.fromObject(sites);
        return data.toString();
    }

    //ajax请求查询站点区域
    @ResponseBody
    @RequestMapping("selSiteAreas")
    public String selSiteAreas(){
        List<Site> sites=siteService.getSiteAreas();
        JSONArray data= JSONArray.fromObject(sites);
        return data.toString();
    }

    //ajax请求查询巡维考勤数据
    @ResponseBody
    @RequestMapping("selXwkq")
    public String selXwkq(@RequestParam("start")Integer start, @RequestParam("limit") Integer limit){
        List<Site> sites=siteService.getSiteAreas();
        List<User> users=userService.getUsers();
        List<Xwkq> xwkqs=xwkqService.getXwkqfy(start,limit);
        JSONArray data= new JSONArray();
        for (int i = 0; i <xwkqs.size() ; i++) {
            JSONObject jsonObject=new JSONObject();
            jsonObject=JSONObject.fromObject(xwkqs.get(i));
            for(Site site:sites){
                if(site.getSite_id().toString().equals(xwkqs.get(i).getSite_id().toString())){
                    jsonObject.put("site_location",site.getSite_location());
                    jsonObject.put("site_name",site.getSite_name());
                }
            }

            for (User user:users){
                if (user.getUserid().toString().equals(xwkqs.get(i).getUser_id().toString())){
                    jsonObject.put("uname",user.getUname());
                }

            }
            data.add(jsonObject);
        }
        String datas="{totalCount:"+xwkqService.getXwkq().size()+",data:"+data.toString()+"}";
        System.out.println(data);
        return datas;
    }
}
