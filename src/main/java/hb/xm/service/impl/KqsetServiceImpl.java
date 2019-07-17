package hb.xm.service.impl;

import hb.xm.dao.KqsetDao;
import hb.xm.entity.Kqset;
import hb.xm.service.KqsetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KqsetServiceImpl implements KqsetService {
    @Autowired
    private  KqsetDao kqsetDao;
    @Override
    public List<Kqset> getKqsetsfy(Integer start,Integer limit) {
        return kqsetDao.getKqsetsfy(start,limit);
    }

    @Override
    public List<Kqset> getKqsets() {
        return kqsetDao.findAll();
    }

    @Override
    public void addKest(Kqset kqset) {
        kqsetDao.save(kqset);
    }

    @Override
    public void delKest(Integer sets_id) {
        kqsetDao.deleteById(sets_id);
    }
}
