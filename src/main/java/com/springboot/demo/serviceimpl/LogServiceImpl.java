package com.springboot.demo.serviceimpl;

import com.springboot.demo.mapper.LogMapper;
import com.springboot.demo.model.LoginLog;
import com.springboot.demo.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LogServiceImpl implements LogService{
    @Autowired
    private LogMapper logMapper;

    @Override
    public void savloginlog(LoginLog loginLog) {

        logMapper.saveloginlog(loginLog);
        return;
    }
}
