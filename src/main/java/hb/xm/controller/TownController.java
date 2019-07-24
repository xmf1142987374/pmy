package hb.xm.controller;

import hb.xm.entity.Site;
import hb.xm.entity.Town;
import hb.xm.service.SiteService;
import hb.xm.service.TownService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

//处理村镇请求
@Controller
public class TownController {
    @Autowired
    private TownService townService;
    @Autowired
    private SiteService siteService;

    @ResponseBody
    @RequestMapping("selTowns")
    public String selTowns(){
        List<Town> towns=townService.getTowns();
        JSONArray data=JSONArray.fromObject(towns);
        return  data.toString();
    }

    /*//ajax请求查询巡维考勤数据
    @ResponseBody
    @RequestMapping("selTown")
    public String selSite(@RequestParam("start")Integer start, @RequestParam("limit") Integer limit){
        List<Site> sites=siteService.getSites();
        List<Town> towns=townService.getTownsfy(start,limit);
        JSONArray data= new JSONArray();
        for (int i = 0; i <towns.size() ; i++) {
            JSONObject jsonObject=new JSONObject();
            jsonObject=JSONObject.fromObject(towns.get(i));
            for(Site site:sites){
                if(site.getSite_location().toString().equals(towns.get(i).getTown_name().toString())){
                    jsonObject.put("town_x_num",site.getSite_type());
                }
            }

            *//*for (User user:users){
                if (user.getUserid().toString().equals(xwkqs.get(i).getUser_id().toString())){
                    jsonObject.put("uname",user.getUname());
                }

            }*//*
            data.add(jsonObject);
        }
        String datas="{totalCount:"+townService.getTowns().size()+",data:"+data.toString()+"}";
        return datas;
    }*/
}
