package hb.xm.service.impl;

import hb.xm.dao.XwkqDao;
import hb.xm.service.XwkqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class XwkqServiceImpl implements XwkqService {
    @Autowired
    private XwkqDao xwkqDao;
}
