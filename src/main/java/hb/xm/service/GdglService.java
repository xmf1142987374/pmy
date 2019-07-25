package hb.xm.service;


import hb.xm.entity.Gdgl;
import hb.xm.entity.Gjlb;

import java.util.List;


import java.util.List;

public interface GdglService {
    public List<Gdgl> getGdgl();//读取数据

     public void addGdgl(Gdgl gdgl);//添加工单

    public List<Gdgl> getgjlbfy(Integer start, Integer limit);

    public void deleteGdgl(Integer order_id);//删除
}
