package hb.xm.service;


import hb.xm.entity.Site;
import hb.xm.entity.Xwkq;

import java.util.List;

public interface SiteService {
    public List<Site> getSiteNames();
    public List<Site> getSiteAreas();
    public List<Site> getSiteByName(String sitename);
    public List<Site> getSitefy(Integer start, Integer limit);
    public List<Site> getSites();
}
