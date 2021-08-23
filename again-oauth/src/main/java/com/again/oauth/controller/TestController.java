package com.again.oauth.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author create by 罗英杰 on 2021/8/18
 * @description:
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/init")
public class TestController {
    @RequestMapping(value = "actionInfo", method = RequestMethod.GET)
    @ResponseBody
    public String test(){
        return "ok";
    }
}
