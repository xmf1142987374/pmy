package hb.xm.controller;

import hb.xm.entity.User;
import hb.xm.service.UserService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;


//处理用户请求

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    //登录界面
    @RequestMapping("/")
    public ModelAndView index(ModelAndView mav){

        mav.setViewName("login");
        return mav;
    }

    //ajax请求查询用户
    @ResponseBody
    @RequestMapping("seluser")
    public String findAll(){
        List<User> users=userService.getUsers();
        JSONArray data=JSONArray.fromObject(users);
        return data.toString();
    }

    //用户登录
    @RequestMapping("userlogin")
    public ModelAndView userlogin(ModelAndView mav, HttpSession session, @ModelAttribute User user){
        User u=userService.getUser(user);
        if (null!=u){
            session.setAttribute("loginuser",u);
            mav.setViewName("index");
        }else{
            mav.setViewName("redirect:/");
        }
        return mav;
    }
}
