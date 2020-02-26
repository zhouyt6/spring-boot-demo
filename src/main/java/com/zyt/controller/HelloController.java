package com.zyt.controller;

import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import cn.hutool.poi.excel.StyleSet;
import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qf58.crm.dto.CustomerDto;
import com.qf58.crm.extend.module.service.CustomerService;
import com.zyt.entity.AddAfterSaleParam;
import com.zyt.utils.IdEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

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
    public String hello(@Validated @RequestBody AddAfterSaleParam commonSearchDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
        }
        System.out.println(commonSearchDTO);
        return "测试POST参数校验"+commonSearchDTO;
    }

    @GetMapping(value = "/helloGet")
    public String hello(@RequestParam Long id) {
//        if (bindingResult.hasErrors()) {
//            System.out.println(bindingResult.getAllErrors());
//        }
        System.out.println("ID  = "+id);
        return "测试GET单参"+id;
    }

    @RequestMapping(value = {"/one","/two"},method = RequestMethod.GET)
    public JSONObject hello2(@RequestParam(required = false, value = "id") String id) {
        CustomerDto customerById = customerService.getCustomerById(10002L);
        log.info(JSON.toJSONString(customerById));
        System.out.println(id);
        JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(customerById));
        return jsonObject;
    }

    @PostMapping(value = "/aa")
    public String dffdf(@RequestBody IdEntity entity) {
        System.out.println("ID  = "+entity.getId());
        return "测试Post请求,单参"+entity.getId();
    }

    /**
     * 导出账号手机号
     *
     * @param response
     * @return
     */
    @RequestMapping(value = "/api", method = RequestMethod.GET)
    public JSONObject exportAccount(HttpServletResponse response) throws Exception{

        List<String> exchangeCodes = Arrays.asList("1111111111", "22222222", "6666666666", "888888888"
                , "454545454", "34545435", "6767676", "88565654");

            ExcelWriter writer = ExcelUtil.getWriter();
        writer.merge(1,"dsfdf");

//        writer.setStyleSet(headCellStyle);
//        writer.write(Arrays.asList("兑换码"));

       StyleSet style = writer.getStyleSet();
        // 第二个参数表示是否也设置头部单元格背景
        StyleSet styleSet = style.setBackgroundColor(IndexedColors.BLUE_GREY, false);
        writer.setStyleSet(styleSet);

            String fileName = "兑换码-礼品册名.xls";
            fileName = new String(fileName.getBytes("GBK"), "ISO8859_1");

            // 一次性写出内容，使用默认样式，强制输出标题
            writer.write(exchangeCodes, true);
            // response为HttpServletResponse对象
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            // test.xls是弹出下载对话框的文件名，不能为中文，中文请自行编码
            response.setHeader("Content-Disposition", "attachment;filename="+fileName);
            // out为OutputStream，需要写出到的目标流
            ServletOutputStream out = response.getOutputStream();
            writer.flush(out, true);
            // 关闭writer，释放内存
            writer.close();
            // 关闭输出Servlet流
            IoUtil.close(out);
            return new JSONObject(true);
    }

}
