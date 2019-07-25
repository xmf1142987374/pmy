package hb.xm.service.impl;

import hb.xm.dao.SbbDao;
import hb.xm.entity.Sbb;
import hb.xm.service.SbbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SbbServiceImpl implements SbbService {
    @Autowired
    private SbbDao sbbDao;

    @Override
    public List<Sbb> getSbb() {
        return sbbDao.findAll();
    }
}
