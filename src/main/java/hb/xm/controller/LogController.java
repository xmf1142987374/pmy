package hb.xm.controller;

import hb.xm.entity.Log;
import hb.xm.service.LogService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class LogController {

    @Autowired
    private LogService logService;
    //ajax请求查询角色
    @ResponseBody
    @RequestMapping("sellog")
    public String findAll(@RequestParam("start")Integer start, @RequestParam("limit") Integer limit){
        List<Log> logs=logService.getLogfy(start,limit);
        JSONArray datas=JSONArray.fromObject(logs);
        String totalCount="{totalCount:"+logService.getLog().size()+"}";
        String data="{totalCount:"+logService.getLog().size()+",data:"+datas.toString()+"}";
        return data;
    }

}
