package hb.xm.service.impl;

import hb.xm.dao.SiteDao;
import hb.xm.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SiteServiceImpl implements SiteService {
    @Autowired
    private SiteDao siteDao;
}
