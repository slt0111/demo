package com.springboot.demo.controller;


import com.springboot.demo.model.Person;
import com.springboot.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

    @Autowired
    private PersonService personService;

    @RequestMapping("/index")
    public ModelAndView index(){
        ModelAndView mav = new ModelAndView("index");
        return mav;
    }

    @RequestMapping("index/logout")
    public String logout(){
        return "redirect:/welcome";
    }

    @ResponseBody
    @RequestMapping("/getPersonInfoById/{id}")
    public Person getPersonInfoById(String id){
        Person person = new Person();
        person = personService.getPersonInfoById(id);
        return person;
    }


}
