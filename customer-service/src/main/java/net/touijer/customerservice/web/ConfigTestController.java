package net.touijer.customerservice.web;

import net.touijer.customerservice.config.GlobalConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RefreshScope
public class ConfigTestController {
//1--vous allez beaucoup parametre du configuration donc achque parametre il faut injecte dans une variable
    @Value("${global.params.p1}")
    private int p1;
    @Value("${global.params.p2}")
    private int p2;
    @Value("${customer.params.x}")
    private int x;
    @Value("${customer.params.y}")
    private int y;

    @GetMapping("testConfig")
    public Map<String,Integer> configTest(){
        return Map.of("p1",p1,"p2",p2,"x",x,"y",y);
    }

//2--est un facon plus pratique creere une class dans le package config pour regroupe tous variable est apre injecte la class
    @Autowired
    private GlobalConfig globalConfig;

    @GetMapping("GlobalConfig")
    public GlobalConfig globalConfig(){
        return globalConfig;
    }

}
