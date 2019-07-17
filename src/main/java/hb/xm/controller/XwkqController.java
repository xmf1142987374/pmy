package hb.xm.controller;

import hb.xm.entity.Kqset;
import hb.xm.service.XwkqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class XwkqController {
    @Autowired
    private XwkqService xwkqService;

    //ajax请求查询考勤规则
    @ResponseBody
    @RequestMapping("selsitenames")
    public String selsitenames(){

        //JSONArray data=JSONArray.fromObject();
        return "";
    }
}
