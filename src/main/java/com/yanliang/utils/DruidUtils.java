package com.yanliang.utils;

import com.alibaba.druid.pool.DruidDataSource;

public class DruidUtils {

    private DruidUtils(){
    }

    private static DruidDataSource druidDataSource = new DruidDataSource();


    static {
        druidDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        druidDataSource.setUrl("jdbc:mysql://10.16.18.44:3306/yanliang");
        druidDataSource.setUsername("root");
        druidDataSource.setPassword("iquantex");

    }

    public static DruidDataSource getInstance() {
        return druidDataSource;
    }

}
