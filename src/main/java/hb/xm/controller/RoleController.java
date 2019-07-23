package hb.xm.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import hb.xm.common.Ip;
import hb.xm.entity.Dept;
import hb.xm.entity.Role;
import hb.xm.entity.User;
import hb.xm.service.LogService;
import hb.xm.service.RoleService;
import net.sf.json.JSONArray;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.FileOutputStream;
import java.io.IOException;
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
    public String findAll(@RequestParam("start")Integer start,@RequestParam("limit") Integer limit){
        List<Role> roles=roleService.getRolefy(start,limit);
        JSONArray datas=JSONArray.fromObject(roles);
        String totalCount="{totalCount:"+roleService.getRole().size()+"}";
        String data="{totalCount:"+roleService.getRole().size()+",data:"+datas.toString()+"}";
        return data;
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
        String uName=user.getUname();
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip  = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip  = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip  = request.getRemoteAddr();
        }
        if (ip.contains(",")) {
            ip =ip.split(",")[0];
        } else {
            ip =ip;
        }
        roleService.addRole(role);
        logService.addLog2(uName,"1","添加角色",userId,roleDate,ip,"1");

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

    //输出excel
    @ResponseBody
    @RequestMapping("addExcelRole")
    public void poiDept(){
        HSSFWorkbook book = new HSSFWorkbook();
        HSSFSheet sheet = book.createSheet("角色表.xlsx");
        HSSFRow row = sheet.createRow(0);
        row.createCell(0).setCellValue("角色id");
        row.createCell(1).setCellValue("角色名称");
        row.createCell(2).setCellValue("角色描述");
        List<Role> list=roleService.getRole();
        for (int i = 0; i < list.size(); i++) {
            HSSFRow temp_row=sheet.createRow(i + 1);
            temp_row.createCell(0).setCellValue(list.get(i).getRole_id());
            temp_row.createCell(1).setCellValue(list.get(i).getRole_name());
            temp_row.createCell(2).setCellValue(list.get(i).getRole_state());
            try {
                book.write(new FileOutputStream("src\\main\\resources\\static\\excel\\角色表.xlsx"));
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                try {
                    book.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    //修改部门
    @ResponseBody
    @RequestMapping("updaterole")
    public void updateDept(@RequestParam("role_id") Integer role_id, @RequestParam("role_name") String role_name, @RequestParam("role_state") String role_state, @RequestParam("create_time") String create_time,@RequestParam("create_user") String create_user,HttpSession session, HttpServletRequest request){
        System.out.println(role_id);
        System.out.println(role_name);
        System.out.println(role_state);
        System.out.println(create_time);
        System.out.println(create_user);
        User user=(User) session.getAttribute("loginuser");
        String userName=user.getUname();
        Date date=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String userDate=sdf.format(date);
        Role role=new Role(role_id,role_name,role_state,create_time,create_user,userName,userDate);

        roleService.updateRole(role);
    }
}
