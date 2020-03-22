package dev.j.api.restful.common.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@Api(description = "Controller Dummy")
@RestController
@RequestMapping(value = "/dummy")
public class ControllerDummy {

    @GetMapping(value="/dummy")
    public String dummy(){
        return "";
    }

}