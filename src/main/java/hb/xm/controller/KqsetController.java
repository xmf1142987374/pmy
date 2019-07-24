package hb.xm.controller;

import hb.xm.entity.Kqset;
import hb.xm.service.KqsetService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


//处理巡维管理请求

@Controller
public class KqsetController {
    @Autowired
    private KqsetService kqsetService;

    //ajax请求查询考勤规则
    @ResponseBody
    @RequestMapping("selkqsets")
    public String findKqsets(@RequestParam("start")Integer start,@RequestParam("limit") Integer limit){
        List<Kqset> kqsets=kqsetService.getKqsetsfy(start,limit);
        JSONArray datas=JSONArray.fromObject(kqsets);
        String data="{totalCount:"+kqsetService.getKqsets().size()+",data:"+datas.toString()+"}";
        return data;
    }

    //ajax请求删除考勤规则
    @ResponseBody
    @RequestMapping("delkqsets")
    public void delKqsets(@RequestParam("data") Integer [] data){
        for (int i = 0; i <data.length ; i++) {
            kqsetService.delKest(data[i]);
        }

    }


    //ajax请求添加考勤规则
    @ResponseBody
    @RequestMapping("addkqset")
    public void addKqset(@RequestParam("site_type") String site_type,@RequestParam("valid_time") String valid_time,@RequestParam("log_cycle") String log_cycle,@RequestParam("log_count") String log_count,@RequestParam("valid_start_time") String valid_start_time,@RequestParam("valid_end_time") String valid_end_time){
        System.out.println(site_type);
        SimpleDateFormat st=new SimpleDateFormat("yyyy-MM-dd");
        Date end_time = null;
        Date start_time = null;
        try {
            start_time=st.parse(valid_end_time);
            end_time = st.parse(valid_end_time);
            valid_end_time=st.format(end_time);
            valid_start_time=st.format(start_time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Kqset kqset=new Kqset(site_type,valid_time,log_cycle,log_count,valid_start_time,valid_end_time);
        kqsetService.addKest(kqset);
    }

}
