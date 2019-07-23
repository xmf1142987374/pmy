//工单管理
package hb.xm.controller;

import hb.xm.entity.Gdgl;
import hb.xm.service.GdglService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class GdglController {
    @Autowired
    private GdglService gdglService;

    @ResponseBody//查询
    @RequestMapping("all")
    public String findAll() {
        List<Gdgl> gdgls = gdglService.getGdgl();
        JSONArray data = new JSONArray();
        data = JSONArray.fromObject(gdgls);
        return data.toString();
    }

    //添加工单
    @ResponseBody
    @RequestMapping("addgdgl")
    public void addGdgl(@RequestParam("order_name") String order_name, @RequestParam("site_id") String site_id, @RequestParam("order_machine") String order_machine,
                        @RequestParam("order_problem_from") String order_problem_from, @RequestParam("order_desc") String order_desc) {
        Gdgl gdgl = new Gdgl();
        gdgl.setOrder_name(order_name);
        gdgl.setSite_id(site_id);
        gdgl.setOrder_machine(order_machine);
        gdgl.setOrder_problem_from(order_problem_from);
        gdgl.setOrder_desc(order_desc);
        gdglService.addGdgl(gdgl);
    }

    //ajax请求删除角色
    @ResponseBody
    @RequestMapping("delegdgl")
    public void deleteGdgl(@RequestParam("data") Integer[] data) {
        for (int i = 0; i < data.length; i++) {
            gdglService.deleteGdgl(data[i]);
        }
    }
}
