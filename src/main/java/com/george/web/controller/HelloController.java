package com.george.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by George on 2017/12/11.
 */

@Controller
@RequestMapping(value = "/hello")
public class HelloController {
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String testPage() {
        System.out.println("hello!");
        return "test";
    }
}
