package com.xu.code.practice.controller;

import com.xu.code.practice.properties.NacosDynamicProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/nacos")
public class NacosPropertiesBindController {

    @Resource
    private NacosDynamicProperties nacosDynamicProperties;

    /**
     * @Author liberty
     * @Date 2025/3/26 21:34
     */

    @GetMapping("/id")
    public ResponseEntity<String> getProperties(){

        return ResponseEntity.ok("Nacos: " + nacosDynamicProperties.getId());

    }


}
