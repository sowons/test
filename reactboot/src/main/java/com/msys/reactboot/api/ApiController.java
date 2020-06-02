package com.msys.reactboot.api;

import java.util.HashMap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ApiController
 */
@RestController
@RequestMapping(value = "/api")
public class ApiController {

    @GetMapping(value = "/hello")
    public HashMap<String, Object> getHello() {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("message", "Hi Hi roo");
        return map;
    }
    
}