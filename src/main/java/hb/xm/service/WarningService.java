package hb.xm.service;

import hb.xm.entity.Warning;

import java.util.List;

public interface WarningService {
    public List<Warning> getWarnings();

    public List<Warning> getWarningsfy(Integer start, Integer limit);
}
