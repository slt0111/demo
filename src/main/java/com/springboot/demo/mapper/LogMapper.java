package com.springboot.demo.mapper;


import com.springboot.demo.model.LoginLog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface LogMapper {

    void saveloginlog(LoginLog loginLog);
}
