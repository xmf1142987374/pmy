package hb.xm.service.impl;

import hb.xm.dao.DeptDao;
import hb.xm.entity.Dept;
import hb.xm.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptDao deptDao;

    @Override
    public List<Dept> getDept() {
        return deptDao.findAll();
    }

    @Override
    public List<Dept> getDeptfy(Integer start, Integer limit) {
        return deptDao.getDeptfy(start,limit);
    }

    @Override
    public void addDept(Dept dept) {
        deptDao.save(dept);
    }

    @Override
    public void delectDept(Integer dep_id) {
        deptDao.deleteById(dep_id);
    }

    @Override
    public void updateDept(Dept dept) {
        deptDao.saveAndFlush(dept);
    }


}
