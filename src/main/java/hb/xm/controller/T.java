package hb.xm.controller;

import hb.xm.entity.User;
import hb.xm.service.UserService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
public class T {
    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public ModelAndView index(ModelAndView mav){

        mav.setViewName("login");
        return mav;
    }
    @RequestMapping("bg")
    public ModelAndView bg(ModelAndView mav){

        mav.setViewName("bg");
        return mav;
    }
    @ResponseBody
    @RequestMapping("hq")
    public String findAll(){
        List<User> users=userService.getUsers();
        JSONArray data=JSONArray.fromObject(users);
        return data.toString();
    }
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
