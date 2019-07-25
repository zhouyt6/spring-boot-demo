package com.example.springbootdemo;

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
    private String searchKey;

    private Integer pageNo = 1;
    private Integer pageSize = 10;

    public Integer getSearchType() {
        return searchType;
    }

    public void setSearchType(Integer searchType) {
        this.searchType = searchType;
    }

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "CommonSearchDTO{" +
                "searchType=" + searchType +
                ", searchKey='" + searchKey + '\'' +
                ", pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                '}';
    }
}
