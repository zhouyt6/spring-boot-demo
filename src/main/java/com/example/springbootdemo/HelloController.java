package com.example.springbootdemo;

import org.springframework.web.bind.annotation.*;

/**
 * @Author: Zhou YingTao
 * @Date: 2019/3/27
 * @Description:
 */
@RestController
public class HelloController {

    @RequestMapping(value = "/helloWord",method = RequestMethod.POST)
    public String hello(@RequestBody CommonSearchDTO commonSearchDTO) {
        System.out.println(commonSearchDTO);
        return "测试无参"+commonSearchDTO;
    }

    @RequestMapping(value = {"/one","/two"},method = RequestMethod.GET)
    public String hello2(@RequestParam(required = false, value = "id", defaultValue = "34343434343343433") Long id) {
        System.out.println(id);
        return "测试Get无参"+id;
    }
}
