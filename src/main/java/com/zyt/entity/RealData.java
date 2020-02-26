package com.zyt.entity;

import java.util.concurrent.Callable;

/**
 * @Author: zhouyt
 * @Date: 2019/12/26
 * @Description:
 */

public class RealData implements Callable<Integer> {

    private String para;

    public RealData(String para){
        this.para = para;
    }

    @Override
    public Integer call() throws Exception {
        //真实的业务逻辑
        StringBuffer sb = new StringBuffer();
        for (int i= 0 ;i < 10 ; i++){
            sb.append(para);
            try {
                Thread.sleep(100);
            }catch (InterruptedException e){

            }
        }
        return Integer.parseInt(sb.toString());
    }
}
