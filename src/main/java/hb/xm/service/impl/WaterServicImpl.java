package hb.xm.service.impl;

import hb.xm.dao.WaterDao;
import hb.xm.entity.Water;
import hb.xm.service.WaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WaterServicImpl implements WaterService {
    @Autowired
    private WaterDao waterDao;
    @Override
    public List<Water> getWaters(String site_name) {
        return waterDao.getWaters(site_name);
    }
}
