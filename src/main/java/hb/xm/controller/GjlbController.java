package hb.xm.controller;

import hb.xm.dao.GjlbDao;
import hb.xm.entity.Dept;
import hb.xm.entity.Gjlb;
import hb.xm.service.GjlbService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class GjlbController {
    @Autowired
    private GjlbService gjlbService;

    @ResponseBody
    @RequestMapping("findAll")
    public String findAll() {
        List<Gjlb> gjlbs=gjlbService.getGjlb();
        JSONArray data=JSONArray.fromObject(gjlbs);
        return data.toString();
    }
}
