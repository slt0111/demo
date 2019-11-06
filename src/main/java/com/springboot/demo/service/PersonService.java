package com.springboot.demo.service;

import com.springboot.demo.mapper.PersonMapper;
import com.springboot.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;

public interface PersonService {

    Person getPersonInfoById(String id);


}
