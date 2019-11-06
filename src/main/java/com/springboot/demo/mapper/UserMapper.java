package com.springboot.demo.mapper;

import com.springboot.demo.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper {

     User getUserByOpenId(String username);

     User getUserByName(String username);
}
