package com.george.web.controller;

import com.george.multidb.SqlSessionHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by George on 2017/12/11.
 */

@Controller
@RequestMapping(value = "/hello")
public class HelloController {

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String testPage() {
        Object o = SqlSessionHelper.getPools();
        System.out.println(" Ôπ‚¥Ûœ√!");
        return "test";
    }

    @RequestMapping(value = "/state", method = RequestMethod.POST)
    @ResponseBody
    public Object testSrcState() {
        Object o = SqlSessionHelper.getPools();
        return o;
    }
}
