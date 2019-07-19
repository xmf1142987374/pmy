package hb.xm.controller;

import hb.xm.entity.Dept;
import hb.xm.entity.User;
import hb.xm.service.DeptService;
import hb.xm.service.LogService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class DeptController {

    @Autowired
    private DeptService deptService;
    @Autowired
    private LogService logService;

    //ajax请求查询部门
    @ResponseBody
    @RequestMapping("seldept")
    public String findAll(){
        List<Dept> depts=deptService.getDept();
        JSONArray data=JSONArray.fromObject(depts);
        return data.toString();
    }

    //ajax请求添加部门
    @ResponseBody
    @RequestMapping("adddept")
    public void addDept(@RequestParam("dep_id") Integer dep_id, @RequestParam("dep_name") String dep_name, @RequestParam("dep_desc") String dep_desc, @RequestParam("dep_state") String dep_state, HttpSession session, HttpServletRequest request){
        Date date =new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String userDate=sdf.format(date);
        User user= (User) session.getAttribute("loginuser");
        String userName=user.getUsername();
        Dept dept =new Dept(dep_id,dep_name,dep_desc,dep_state,userName,userDate);
        Integer userId=user.getUserid();
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if (ip.contains(",")) {
            ip=ip.split(",")[0];
        } else {
            ip=ip;
        }
        deptService.addDept(dept);
        logService.addLog2("1","添加部门",userId,userDate,ip,"1");
    }

    //ajax请求删除部门
    @ResponseBody
    @RequestMapping("deldept")
    public void deleteDept(@RequestParam("data") Integer [] data){
        System.out.println(data.toString());
        for (int i = 0; i <data.length ; i++) {
            deptService.delectDept(data[i]);
        }
    }
}
