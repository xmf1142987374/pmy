package hb.xm.controller;

import hb.xm.entity.Role;
import hb.xm.entity.User;
import hb.xm.service.LogService;
import hb.xm.service.RoleService;
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
public class RoleController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private LogService logService;

    //ajax请求查询角色
    @ResponseBody
    @RequestMapping("selrole")
    public String findAll(){
        List<Role> roles=roleService.getRole();
        JSONArray data=JSONArray.fromObject(roles);
        return data.toString();
    }

    //ajax请求添加角色
    @ResponseBody
    @RequestMapping("addrole")
    public void addRole(@RequestParam("role_id") Integer role_id, @RequestParam("role_name") String role_name, @RequestParam("role_state") String role_state, HttpSession session, HttpServletRequest request){
        Date date =new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String roleDate=sdf.format(date);
        User user= (User) session.getAttribute("loginuser");
        String userName=user.getUsername();
        Role role =new Role(role_id,role_name,role_state,roleDate,userName);
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
        roleService.addRole(role);
        logService.addLog2("1","添加角色",userId,roleDate,ip,"1");

    }

    //ajax请求删除角色
    @ResponseBody
    @RequestMapping("delrole")
    public void deleteRole(@RequestParam("data") Integer [] data){
        System.out.println(data.toString());
        for (int i = 0; i <data.length ; i++) {
            roleService.deleteRole(data[i]);
        }
    }
}
