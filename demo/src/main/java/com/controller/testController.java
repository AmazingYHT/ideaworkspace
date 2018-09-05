package com.controller;

import com.bean.bean;
import com.mapper.testMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class testController {

    @Autowired
    private testMapper mapper;

    @RequestMapping("/index")
    public void testSelect() {
        System.out.println("figau");

        List<bean> selectList = mapper.selectMap(new HashMap<>());

        System.out.println("666");
    }

}
