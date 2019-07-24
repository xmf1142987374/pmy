package hb.xm.service;

import hb.xm.entity.Gjlb;
import hb.xm.entity.User;
import hb.xm.entity.Xwkq;

import java.util.List;


public interface GjlbService {
    public List<Gjlb> getGjlb();//查询

    //public List<Gjlb> getgjlb(Integer start, Integer limit);



    public void deleteGjlb(Integer warning_id);//删除
}
