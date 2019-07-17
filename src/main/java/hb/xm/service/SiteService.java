package hb.xm.service;


import hb.xm.entity.Site;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SiteService {
    public List<Site> getSites();
    public List<String> getSiteNames();
}
