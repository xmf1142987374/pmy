package hb.xm.controller;


import hb.xm.entity.Gjlb;

import hb.xm.entity.Site;

import hb.xm.service.GjlbService;
import hb.xm.service.SiteService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

//告警列表数据处理
@Controller
public class GjlbController {
    @Autowired
    private GjlbService gjlbService;
    @Autowired
    private SiteService siteService;

    //ajax请求查询告警列表所有数据
    @ResponseBody
    @RequestMapping("findAll")
    public String findAll(@RequestParam("start") Integer start, @RequestParam("limit") Integer limit) {
        List<Gjlb> gjlbs = gjlbService.getgjlbfy(start, limit);
        List<Site> sites = siteService.getSiteAreas();
        JSONArray datas = new JSONArray();
        for (int i = 0; i < gjlbs.size(); i++) {
            JSONObject jsonObject = new JSONObject();
            jsonObject = JSONObject.fromObject(gjlbs.get(i));
            for (Site site : sites) {
                if (site.getSite_id().toString().equals(gjlbs.get(i).getSite_id().toString())) {
                    jsonObject.put("site_location", site.getSite_location());
                    jsonObject.put("site_name", site.getSite_name());
                }
            }
            datas.add(jsonObject);
        }
        String data = "{totalCount:" + gjlbService.getGjlb().size() + ",data:" + datas.toString() + "}";//分页
        return data;
    }

    //ajax请求删除告警列表选择数据
    @ResponseBody
    @RequestMapping("delegjlb")
    public void deleteGjlb(@RequestParam("data") Integer[] data) {
        for (int i = 0; i < data.length; i++) {
            gjlbService.deleteGjlb(data[i]);
        }
    }


    //输出excel
    @ResponseBody
    @RequestMapping("addExcel")
    public void poiDept() {
        HSSFWorkbook book = new HSSFWorkbook();
        HSSFSheet sheet = book.createSheet("数据汇总表.xlsx");
        HSSFRow row = sheet.createRow(0);
        row.createCell(0).setCellValue("乡镇名");
        row.createCell(1).setCellValue("站点名称");
        row.createCell(2).setCellValue("告警级别");
        row.createCell(3).setCellValue("告警内容");
        row.createCell(4).setCellValue("是否处理");
        row.createCell(5).setCellValue("告警时间");


        List<Gjlb> list = gjlbService.getGjlb();
        List<Site> sites = siteService.getSiteAreas();
        for (int i = 0; i < sites.size(); i++) {
            HSSFRow temp_row = sheet.createRow(i + 1);
            temp_row.createCell(0).setCellValue(sites.get(i).getSite_location());
            temp_row.createCell(1).setCellValue(list.get(i).getSite_id());
            temp_row.createCell(2).setCellValue(list.get(i).getWarning_level());
            temp_row.createCell(3).setCellValue(list.get(i).getWarning_desc());
            temp_row.createCell(4).setCellValue(list.get(i).getIs_valid());
            temp_row.createCell(5).setCellValue(list.get(i).getOperate_time());

            try {
                book.write(new FileOutputStream("src\\main\\resources\\static\\excel\\数据汇总表.xlsx"));
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
}
