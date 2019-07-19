package hb.xm.controller;

import hb.xm.entity.Log;
import hb.xm.entity.Role;
import hb.xm.service.LogService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class LogController {

    @Autowired
    private LogService logService;
    //ajax请求查询角色
    @ResponseBody
    @RequestMapping("sellog")
    public String findAll(){
        List<Log> logs=logService.getLog();
        JSONArray data=JSONArray.fromObject(logs);
        return data.toString();
    }
}
