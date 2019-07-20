package hb.xm.service.impl;

import hb.xm.dao.LogDao;
import hb.xm.entity.Log;
import hb.xm.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogDao logDao;

    @Override
    public List<Log> getLog() {
        return logDao.findAll();
    }

    @Override
    public List<Log> getLogfy(Integer start, Integer limit) {
        return logDao.getLogfy(start,limit);
    }

    @Override
    public void addLog(Log log) {
        logDao.save(log);
    }

    @Override
    public void addLog2(String user_name, String log_type,String log_desc,Integer user_id,String log_time,String user_ip,String is_success) {
        logDao.addLog(user_name,log_type,log_desc,user_id,log_time,user_ip,is_success);
    }
}
