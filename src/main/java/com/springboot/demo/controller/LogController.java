package com.springboot.demo.controller;

import com.springboot.demo.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

public class LogController {
    @Autowired
    private LogService logService;

    @Resource
    public void saveLoginLog(String username){

        //logService.savloginlog(username);
        return ;
    }
}
