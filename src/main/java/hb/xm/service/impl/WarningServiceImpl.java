package hb.xm.service.impl;

import hb.xm.dao.WarningDao;
import hb.xm.entity.Warning;
import hb.xm.service.WarningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarningServiceImpl implements WarningService {

    @Autowired
    private WarningDao warningDao;

    @Override
    public List<Warning> getWarnings() {
        return warningDao.findAll();
    }

    @Override
    public List<Warning> getWarningsfy(Integer start, Integer limit) {
        return warningDao.getWarningfy(start,limit);
    }
}
