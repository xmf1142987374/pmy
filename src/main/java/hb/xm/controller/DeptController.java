package hb.xm.controller;

import hb.xm.common.Ip;
import hb.xm.entity.Dept;
import hb.xm.entity.Role;
import hb.xm.entity.Role_User;
import hb.xm.entity.User;
import hb.xm.service.DeptService;
import hb.xm.service.LogService;
import hb.xm.service.RoleService;
import hb.xm.service.Role_UserService;
import net.sf.json.JSONArray;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class DeptController {

    @Autowired
    private DeptService deptService;
    @Autowired
    private LogService logService;
    @Autowired
    private Role_UserService role_userService;
    @Autowired
    private RoleService roleService;

    //ajax请求查询部门
    @ResponseBody
    @RequestMapping("seldept")
    public String findAll(@RequestParam("start")Integer start,@RequestParam("limit") Integer limit){
        List<Dept> depts=deptService.getDeptfy(start,limit);
        JSONArray datas=JSONArray.fromObject(depts);
        String totalCount="{totalCount:"+deptService.getDept().size()+"}";
        String data="{totalCount:"+deptService.getDept().size()+",data:"+datas.toString()+"}";
        return data;
    }

    //ajax请求添加部门
    @ResponseBody
    @RequestMapping("adddept")
    public void addDept(@RequestParam("dep_id") Integer dep_id, @RequestParam("dep_name") String dep_name, @RequestParam("dep_desc") String dep_desc, @RequestParam("dep_state") String dep_state, HttpSession session, HttpServletRequest request){
        Date date =new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String userDate=sdf.format(date);
        User user= (User) session.getAttribute("loginuser");
        String userName=user.getUname();
        Dept dept =new Dept(dep_id,dep_name,dep_desc,dep_state,userName,userDate);
        Integer userId=user.getUserid();
        String uName=user.getUname();
        String ip=request.getRemoteAddr();
        deptService.addDept(dept);
        logService.addLog2(uName,"1","添加部门",userId,userDate,ip,"1");
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

    //请求部门

    @RequestMapping("bm")
    public ModelAndView bm(ModelAndView mav,HttpSession session){
        User user=(User) session.getAttribute("loginuser");
        Integer ints=user.getUserid();
        List<Role_User> rus=role_userService.getRoleUser();
        List<Role> roles=roleService.getRole();
        for(Role_User ru:rus){
            if(ru.getUser_id().equals(ints)){
                for(Role role:roles){
                    if(ru.getRole_id().equals(role.getRole_id())){
                        if(role.getRole_name().equals("超级管理员")){
                            mav.setViewName("bm");
                            return mav;
                        }
                    }
                }
            }
        }
        mav.setViewName("403");
        return mav;
    }

    //输出excel
    @ResponseBody
    @RequestMapping("addExcelDept")
    public void poiDept(){
        HSSFWorkbook book = new HSSFWorkbook();
        HSSFSheet sheet = book.createSheet("部门表.xlsx");
        HSSFRow row = sheet.createRow(0);
        row.createCell(0).setCellValue("部门id");
        row.createCell(1).setCellValue("部门名称");
        row.createCell(2).setCellValue("部门描述");
        row.createCell(3).setCellValue("部门状态");
        row.createCell(4).setCellValue("创建者");
        row.createCell(5).setCellValue("创建时间");
        row.createCell(6).setCellValue("修改者");
        row.createCell(7).setCellValue("修改时间");
        List<Dept> list=deptService.getDept();
        for (int i = 0; i < list.size(); i++) {
            HSSFRow temp_row=sheet.createRow(i + 1);
            temp_row.createCell(0).setCellValue(list.get(i).getDep_id());
            temp_row.createCell(1).setCellValue(list.get(i).getDep_name());
            temp_row.createCell(2).setCellValue(list.get(i).getDep_desc());
            temp_row.createCell(3).setCellValue(list.get(i).getDep_state());
            temp_row.createCell(4).setCellValue(list.get(i).getCreate_user());
            temp_row.createCell(5).setCellValue(list.get(i).getCreate_time());
            temp_row.createCell(6).setCellValue(list.get(i).getModify_user());
            temp_row.createCell(7).setCellValue(list.get(i).getModify_time());
            try {
                book.write(new FileOutputStream("src\\main\\resources\\static\\excel\\部门表.xlsx"));
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
    @RequestMapping("updatedept")
    public void updateDept(@RequestParam("dep_id") Integer dep_id, @RequestParam("dep_name") String dep_name, @RequestParam("dep_desc") String dep_desc, @RequestParam("dep_state") String dep_state,@RequestParam("create_user") String create_user,@RequestParam("create_time") String create_time, HttpSession session, HttpServletRequest request){
        User user=(User) session.getAttribute("loginuser");
        String userName=user.getUname();
        Date date=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String userDate=sdf.format(date);
        Dept dept=new Dept(dep_id,dep_name,dep_desc,dep_state,create_user,create_time,userName,userDate);
        deptService.updateDept(dept);
    }
}
