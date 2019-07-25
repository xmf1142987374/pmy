package hb.xm.controller;

import hb.xm.entity.Site;
import hb.xm.entity.Town;
import hb.xm.service.SiteService;
import hb.xm.service.TownService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.*;

//处理站点关于站点信息的请求
@Controller
public class SiteController {
    @Autowired
    private TownService townService;
    @Autowired
    private SiteService siteService;


    @RequestMapping("zds")
    public ModelAndView zds(ModelAndView mav){
        mav.setViewName("Site");
        return mav;
    }

    @ResponseBody
    @RequestMapping("selSiteByName")
    public String selSiteByName(@RequestParam("sitename") String sitename) {
        List<Town> towns = townService.getTowns();
        List<Site> sites = siteService.getSiteByName(sitename);
        JSONArray data = new JSONArray();
        for (int i = 0; i < sites.size(); i++) {
            JSONObject jsonObject = new JSONObject();
            jsonObject = JSONObject.fromObject(sites.get(i));
            for (Town town : towns) {
                if (sites.get(i).getSite_location().equals(town.getTown_name())) {
                    jsonObject.put("town_x_num", town.getTown_x_num());
                    jsonObject.put("town_y_num", town.getTown_y_num());
                }
            }
            data.add(jsonObject);
        }
        return data.toString();
    }

    //ajax请求查询站点数据
    @ResponseBody
    @RequestMapping("selSite")
    public String selSite(@RequestParam("start") Integer start, @RequestParam("limit") Integer limit) {
        List<Town> towns = townService.getTowns();
        List<Site> sites = siteService.getSitefy(start, limit);
        JSONArray data = new JSONArray();
        for (int i = 0; i < sites.size(); i++) {
            JSONObject jsonObject = new JSONObject();
            jsonObject = JSONObject.fromObject(sites.get(i));
            for (Town town : towns) {
                if (town.getTown_name().toString().equals(sites.get(i).getSite_location().toString())) {
                    jsonObject.put("town_x_num", town.getTown_x_num());
                    jsonObject.put("town_y_num", town.getTown_y_num());
                }
            }

            /*for (User user:users){
                if (user.getUserid().toString().equals(xwkqs.get(i).getUser_id().toString())){
                    jsonObject.put("uname",user.getUname());
                }

            }*/
            data.add(jsonObject);
        }
        String datas = "{totalCount:" + siteService.getSites().size() + ",data:" + data.toString() + "}";
        return datas;
    }

    //查询站点类型下拉框数据
    @ResponseBody
    @RequestMapping("selSiteType")
    public String selSiteTypes() {
        List<Site> sites = siteService.getSites();
        Set<String> s_type = new TreeSet<String>();
        for (Site s : sites) {
            s_type.add(s.getSite_type());
        }
        JSONArray data = new JSONArray();
        for (String string : s_type) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("site_type", string);
            data.add(jsonObject);
        }
        //System.out.println(data.toString());
        return data.toString();
    }

    //ajax请求添加站点
    @ResponseBody
    @RequestMapping("addsite")
    public void addSite(@RequestParam("site_id") Integer site_id, @RequestParam("site_name") String site_name, @RequestParam("site_location") String site_location, @RequestParam("site_type") String site_type, @RequestParam("site_desc") String site_desc, @RequestParam("uname") String uname, @RequestParam("user_tel") String user_tel,@RequestParam("fname") MultipartFile file) {
        //System.out.println(file.getOriginalFilename());
        //@RequestParam("site_id") Integer site_id, @RequestParam("site_name") String site_name, @RequestParam("site_location") String site_location, @RequestParam("site_type") String site_type, @RequestParam("site_desc") String site_desc, @RequestParam("uname") String uname, @RequestParam("user_tel") String user_tel,
        //获取文件名
        String filename = file.getOriginalFilename();
        String ext= null;
        //获取文件后缀
        if(filename.contains(".")){
            ext = filename.substring(filename.lastIndexOf("."));
        }else{
            ext = "";
        }
        //使用uuid防止重复
        String uuid =  UUID.randomUUID().toString().replaceAll("-", "");
        String nfileName = uuid + ext;

        String path = "E:\\GitOne\\src\\main\\resources\\static\\siteimg";
        File f1 = new File(path);
        if (!f1.exists()) {
            f1.mkdirs();
        }
        String filepath = path + "/" + nfileName;
        File f2 = new File(filepath);
        try {
            file.transferTo(f2);

        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Site site=new Site(site_id,site_name,site_location,site_type,site_desc,nfileName);
        siteService.addSite(site);
        System.out.println("上传成功");
    }
}
