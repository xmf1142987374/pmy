package hb.xm.service.impl;

import hb.xm.dao.XwkqDao;
import hb.xm.entity.Xwkq;
import hb.xm.service.XwkqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class XwkqServiceImpl implements XwkqService {
    @Autowired
    private XwkqDao xwkqDao;

    @Override
    public List<Xwkq> getXwkqfy(Integer start,Integer limit) {
        return xwkqDao.getXwkqfy( start, limit);
    }

    @Override
    public List<Xwkq> getXwkq() {
        return xwkqDao.findAll();
    }
}
