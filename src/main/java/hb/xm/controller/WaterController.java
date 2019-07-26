package hb.xm.controller;

import hb.xm.entity.Water;
import hb.xm.service.WaterService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

//报表单站点水流量
@Controller
public class WaterController {
    @Autowired
    private WaterService waterService;
    @ResponseBody
    @RequestMapping("selWaters")
    public String selWaters(@RequestParam("site_name")String site_name){
         List<Water> waters=waterService.getWaters(site_name);
        JSONArray data=JSONArray.fromObject(waters);
        return data.toString();
    }

}
