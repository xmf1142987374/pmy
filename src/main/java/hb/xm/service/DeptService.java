package hb.xm.service;

import hb.xm.entity.Dept;

import java.util.List;

public interface DeptService {
    public List<Dept> getDept();

    public void addDept(Dept dept);

    public void delectDept(Integer dep_id);
}
