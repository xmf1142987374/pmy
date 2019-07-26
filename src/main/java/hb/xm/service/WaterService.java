package hb.xm.service;

import hb.xm.entity.Water;

import java.util.List;

public interface WaterService {
    public List<Water> getWaters(String site_name);
}
