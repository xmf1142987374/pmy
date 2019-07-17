package hb.xm.controller;

import hb.xm.entity.Kqset;
import hb.xm.service.KqsetService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class T {
    @Autowired
    private KqsetService kqsetService;
    @ResponseBody
    @RequestMapping("selkqsetsss")
    public String findKqsets(@RequestParam("start")Integer start,@RequestParam("limit") Integer limit){
        List<Kqset> kqsets=kqsetService.getKqsetsfy(start,limit);
        JSONArray datas=JSONArray.fromObject(kqsets);
        String totalCount="{totalCount:"+kqsetService.getKqsets().size()+"}";
        String data="{totalCount:"+kqsetService.getKqsets().size()+",data:"+datas.toString()+"}";
        System.out.println(data);
        return data;
    }
    @RequestMapping("bg")
    public String bg(){
        return "bg";
    }
}
