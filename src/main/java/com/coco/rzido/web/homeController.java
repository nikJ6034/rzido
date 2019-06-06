package com.coco.rzido.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * homeController
 */
@RestController
public class homeController {

    @GetMapping("/")
    public String home(){
        return "집이다 2222";
    }
    
}