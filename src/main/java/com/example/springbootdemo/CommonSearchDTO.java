package com.example.springbootdemo;

import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;
import com.qf58.crm.utils.ParamLess;
import com.qf58.crm.utils.ParamOptional;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Author: Zhou YingTao
 * @Date: 2019/4/9
 * @Description: 公共组件查询共有字段
 */
@Data
public class CommonSearchDTO implements Serializable{

    private static final long serialVersionUID = -3784104666908739872L;

    /**
     * 1不分页，2分页
     */
    @NotNull(message = "地址id不能为空", groups = {ParamOptional.class, ParamLess.class})
    private Integer searchType;

    /**
     * 搜索关键字
     */
    @NotNull
    private Byte searchKey;

    private Integer pageNo = 1;
    private Integer pageSize = 10;



    @Override
    public String toString() {
        return "CommonSearchDTO{" +
                "searchType=" + searchType +
                ", searchKey='" + searchKey + '\'' +
                ", pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                '}';
    }

    public static void main(String[] args) {
        QrCodeUtil.generate("http://www.58qf.com", QrConfig.create().setWidth(300).setHeight(300).setImg("e:/文字.png"), FileUtil.file("e:/qrcode.jpg"));
    }



}
