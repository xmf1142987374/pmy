package hb.xm.service.impl;

import hb.xm.dao.SiteDao;
import hb.xm.entity.Site;
import hb.xm.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SiteServiceImpl implements SiteService {
    @Autowired
    private SiteDao siteDao;

    @Override
    public List<Site> getSiteNames() {
        return siteDao.findAll();
    }

    @Override
    public List<Site> getSiteAreas() {
        return siteDao.getSiteAreas();
    }

    @Override
    public List<Site> getSiteByName(String sitename) {
        return siteDao.getSiteByName(sitename);
    }

    @Override
    public List<Site> getSitefy(Integer start, Integer limit) {
        return siteDao.getSitefy(start,limit);
    }

    @Override
    public List<Site> getSites() {
        return siteDao.findAll();
    }
}
