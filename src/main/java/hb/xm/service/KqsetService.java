package hb.xm.service;

import hb.xm.entity.Kqset;
import hb.xm.entity.User;

import java.util.List;

public interface KqsetService {
    public List<Kqset> getKqsetsfy(Integer start,Integer limit);
    public List<Kqset> getKqsets();
    public void addKest(Kqset kqset);
    public void delKest(Integer sets_id);
}
