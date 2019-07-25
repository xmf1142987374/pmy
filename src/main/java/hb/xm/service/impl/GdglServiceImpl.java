package hb.xm.service.impl;
//工单管理

import hb.xm.dao.GdglDao;
import hb.xm.entity.Gdgl;
import hb.xm.service.GdglService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GdglServiceImpl implements GdglService {

    @Autowired
    private GdglDao gdglDao;

    @Override//查询
    public List<Gdgl> getGdgl() {
        return gdglDao.findAll();
    }

    @Override//添加
    public void addGdgl(Gdgl gdgl) {
        gdglDao.save(gdgl);
    }

    @Override
    public List<Gdgl> getgjlbfy(Integer start, Integer limit) {
        return gdglDao.getgdglfy(start, limit);
    }

    @Override//删除
    public void deleteGdgl(Integer order_id) {
        gdglDao.deleteById(order_id);
    }


}
