package hb.xm.service.impl;

import hb.xm.dao.TownDao;
import hb.xm.entity.Town;
import hb.xm.service.TownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TownServiceImpl implements TownService {
    @Autowired
    private TownDao townDao;

    @Override
    public List<Town> getTowns() {
        return townDao.getTowns();
    }

    /*@Override
    public List<Town> getTownsfy(Integer start, Integer limit) {
        return townDao.getTownsfy(start,limit);
    }*/


}
