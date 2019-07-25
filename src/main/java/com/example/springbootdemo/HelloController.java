package com.example.springbootdemo;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qf58.crm.dto.CustomerDto;
import com.qf58.crm.extend.module.service.CustomerService;
import com.qf58.crm.utils.ParamLess;
import com.qf58.crm.utils.ParamOptional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: Zhou YingTao
 * @Date: 2019/3/27
 * @Description:
 */
@Slf4j
@RestController
public class HelloController {

    @Reference
    CustomerService customerService;


    @RequestMapping(value = "/hello",method = RequestMethod.POST)
    public String hello(@RequestBody @Validated({ParamLess.class, ParamOptional.class}) CommonSearchDTO commonSearchDTO) {
//        if (bindingResult.hasErrors()) {
//            System.out.println(bindingResult.getAllErrors());
//        }
        System.out.println(commonSearchDTO);
        return "测试无参"+commonSearchDTO;
    }

    @GetMapping(value = "/hello")
    public String hello(@RequestParam(required = false,defaultValue = "2") Long id) {
//        if (bindingResult.hasErrors()) {
//            System.out.println(bindingResult.getAllErrors());
//        }
        System.out.println("ID  = "+id);
        return "测试无参"+id;
    }

    @RequestMapping(value = {"/one","/two"},method = RequestMethod.GET)
    public JSONObject hello2(@RequestParam(required = false, value = "id") String id) {
        CustomerDto customerById = customerService.getCustomerById(10002L);
        log.info(JSON.toJSONString(customerById));
        System.out.println(id);
        JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(customerById));
        return jsonObject;
    }
}
