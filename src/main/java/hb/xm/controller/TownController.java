package hb.xm.controller;

import hb.xm.entity.Town;
import hb.xm.service.TownService;
import net.sf.json.JSONArray;
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

    @ResponseBody
    @RequestMapping("selTowns")
    public String selTowns(){
        List<Town> towns=townService.getTowns();
        JSONArray data=JSONArray.fromObject(towns);
        return  data.toString();
    }
}
