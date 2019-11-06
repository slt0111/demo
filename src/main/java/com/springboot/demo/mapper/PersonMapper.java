package com.springboot.demo.mapper;


import com.springboot.demo.model.Person;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
@Repository
@Mapper
public interface PersonMapper {

    Person getPersonInfoById(String id);

}
