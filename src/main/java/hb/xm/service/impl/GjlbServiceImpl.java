package hb.xm.service.impl;

import hb.xm.dao.GjlbDao;
import hb.xm.entity.Gjlb;
import hb.xm.service.GjlbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class GjlbServiceImpl implements GjlbService{
    @Autowired
    private GjlbDao gjlbDao;

    @Override
    public List<Gjlb> getGjlb() {
        return gjlbDao.findAll();
    }
}


