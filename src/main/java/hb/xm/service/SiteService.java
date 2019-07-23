package hb.xm.service;


import hb.xm.entity.Site;

import java.util.List;

public interface SiteService {
    public List<Site> getSiteNames();
    public List<Site> getSiteAreas();
    public List<Site> getSiteByName(String sitename);
}
