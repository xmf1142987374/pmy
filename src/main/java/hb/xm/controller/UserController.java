package hb.xm.controller;

import hb.xm.entity.User;
import hb.xm.service.LogService;
import hb.xm.service.UserService;
import net.sf.json.JSONArray;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


//处理用户请求

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private LogService logService;

    //登录界面
    @RequestMapping("/")
    public ModelAndView index(ModelAndView mav){

        mav.setViewName("login");
        return mav;
    }

    //ajax请求查询用户
    @ResponseBody
    @RequestMapping("seluser")
    public String findAll(@RequestParam("start")Integer start,@RequestParam("limit") Integer limit){
        List<User> users=userService.getUserfy(start,limit);
        JSONArray datas=JSONArray.fromObject(users);
        String totalCount="{totalCount:"+userService.getUsers().size()+"}";
        String data="{totalCount:"+userService.getUsers().size()+",data:"+datas.toString()+"}";
        return data;
    }

    //用户登录
    @RequestMapping("userlogin")
    public ModelAndView userlogin(ModelAndView mav, HttpSession session, @ModelAttribute User user){


        String str=user.getPassword();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            str=new BigInteger(1, md.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }



        user.setPassword(str);
        User u=userService.getUser(user);

        if (null!=u){
            session.setAttribute("loginuser",u);
            mav.setViewName("index");
        }else{
            mav.setViewName("redirect:/");
        }
        return mav;
    }


    //ajax请求添加用户
    @ResponseBody
    @RequestMapping("adduser")
    public void addUser(@RequestParam("user_id") Integer user_id, @RequestParam("user_name") String user_name, @RequestParam("user_password") String user_password, @RequestParam("user_login_name") String user_login_name, @RequestParam("user_department") String user_department, @RequestParam("user_gender") String user_gender, @RequestParam("user_tel") String user_tel, @RequestParam("user_phone") String user_phone,HttpSession session, HttpServletRequest request){
        String str=user_password;   //加密操作
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            str=new BigInteger(1, md.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }


        Date date =new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String userDate=sdf.format(date);
        User user=new User(user_id,user_name,user_login_name,str,user_department,user_gender,user_tel,user_phone,"正常",userDate);
        User u= (User) session.getAttribute("loginuser");
        Integer userId=u.getUserid();
        String userName=u.getUname();
        String ip=request.getRemoteAddr();
        userService.addUser(user);
        logService.addLog2(userName,"1","添加用户",userId,userDate,ip,"1");

    }

    //ajax请求删除用户
    @ResponseBody
    @RequestMapping("delUser")
    public void deleteUser(@RequestParam("data") Integer [] data){
        for (int i = 0; i <data.length ; i++) {
            userService.deleteUser(data[i]);
        }
    }

    /*//修改密码
    @RequestMapping("updateUserPas")
    public String update(@RequestParam("password1") String password1,HttpSession session){
        User user=(User)session.getAttribute("loginuser");
        user.setPassword(password1);
        userService.updateUser(user);
        return "login";
    }*/

    //输出excel
    @ResponseBody
    @RequestMapping("addExcelUser")
    public void poiDept(){
        HSSFWorkbook book = new HSSFWorkbook();
        HSSFSheet sheet = book.createSheet("用户表.xlsx");
        HSSFRow row = sheet.createRow(0);
        row.createCell(0).setCellValue("用户id");
        row.createCell(1).setCellValue("用户姓名");
        row.createCell(2).setCellValue("用户登录账号");
        row.createCell(3).setCellValue("用户所在部门");
        row.createCell(4).setCellValue("用户性别");
        row.createCell(5).setCellValue("用户电话");
        row.createCell(6).setCellValue("用户手机号");
        row.createCell(7).setCellValue("用户状态");
        row.createCell(8).setCellValue("创建角色时间");

        List<User> list=userService.getUsers();
        for (int i = 0; i < list.size(); i++) {
            HSSFRow temp_row=sheet.createRow(i + 1);
            temp_row.createCell(0).setCellValue(list.get(i).getUserid());
            temp_row.createCell(1).setCellValue(list.get(i).getUname());
            temp_row.createCell(2).setCellValue(list.get(i).getUsername());
            temp_row.createCell(3).setCellValue(list.get(i).getUser_dept());
            temp_row.createCell(4).setCellValue(list.get(i).getUser_sex());
            temp_row.createCell(5).setCellValue(list.get(i).getUser_tel());
            temp_row.createCell(6).setCellValue(list.get(i).getUser_phone());
            temp_row.createCell(7).setCellValue(list.get(i).getUser_state());
            temp_row.createCell(8).setCellValue(list.get(i).getCreate_time());
            try {
                book.write(new FileOutputStream("src\\main\\resources\\static\\excel\\用户表.xlsx"));
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

    //修改用户
    @ResponseBody
    @RequestMapping("updateuser")
    public void updateUser(@RequestParam("userid") Integer userid, @RequestParam("uname") String uname, @RequestParam("password") String password, @RequestParam("username") String username, @RequestParam("user_dept") String user_dept, @RequestParam("user_sex") String user_sex, @RequestParam("user_tel") String user_tel, @RequestParam("user_phone") String user_phone,@RequestParam("user_state") String user_state,@RequestParam("create_time") String create_time){
        User user=new User(userid,uname,username,password,user_dept,user_sex,user_tel,user_phone,user_state,create_time);
        userService.updateUser(user);
    }

    //ajax高级查询用户
    @ResponseBody
    @RequestMapping("GJseluser")
    public String findGJAll(@RequestParam("uname")String uname,@RequestParam("username")String username,@RequestParam("user_sex")String user_sex){
        User user=new User(uname,username,user_sex);
        List<User> users=userService.gjSeleteUser(user);
        JSONArray datas=JSONArray.fromObject(users);
        String data="{totalCount:"+users.size()+",data:"+datas.toString()+"}";
        System.out.println(data);
        return data;
    }
}
