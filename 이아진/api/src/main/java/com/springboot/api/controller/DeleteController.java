package com.springboot.api.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/delete-api")
@RestController
public class DeleteController {

    @DeleteMapping("/{variable}")
    public String deleteVariable(@PathVariable String variable){
        return variable;
    }

    @DeleteMapping(value = "/request1")
    public String getRequestParam1(@PathVariable String email){
        return "email : " + email;
    }
}

