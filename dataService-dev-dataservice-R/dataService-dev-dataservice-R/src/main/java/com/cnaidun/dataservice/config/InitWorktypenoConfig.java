package com.cnaidun.dataservice.config;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;

@Component
@Order(value = 1)
public class InitWorktypenoConfig implements ApplicationRunner {

    public static HashMap<String,String> workTypeNoMap = new HashMap<String,String>();

    @Override
    public void run(ApplicationArguments args) throws Exception {

        ClassPathResource classPathResource = new ClassPathResource("config/cnaidun-worktypeno.xml");
        InputStream inputStream = classPathResource.getInputStream();
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);

        Element root = document.getRootElement();
        for (Iterator<Element> iterator = root.elementIterator(); iterator.hasNext();) {
            Element element = iterator.next();
            workTypeNoMap.put(element.attributeValue("worktypeno"),element.getStringValue());
        }
    }


}
