package com.springboot.demo.serviceimpl;

import com.springboot.demo.mapper.PersonMapper;
import com.springboot.demo.model.Person;
import com.springboot.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class PersonServiceImpl implements PersonService {

    @Override
    public Person getPersonInfoById(String id){
        //return personMapper.getPersonInfoById(id);
        return new Person();
    }

}
