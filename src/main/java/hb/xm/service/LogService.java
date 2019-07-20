package hb.xm.service;

import hb.xm.entity.Log;

import java.util.List;

public interface LogService {

    public List<Log> getLog();

    public List<Log> getLogfy(Integer start, Integer limit);

    public void addLog(Log log);

    public void addLog2(String user_name,String log_type,String log_desc,Integer user_id,String log_time,String user_ip,String is_success);
}
