package hb.xm.service;


import hb.xm.entity.Xwkq;

import java.util.List;

public interface XwkqService {
    public List<Xwkq> getXwkqfy(Integer start,Integer limit);
    public List<Xwkq> getXwkq();
    public List<Xwkq> getSiteXwkqfy(String sitelocation,Integer start,Integer limit);
    public List<Xwkq> getSiteXwkqfy(String sitelocation);

}
