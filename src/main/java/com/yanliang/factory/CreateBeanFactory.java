package com.yanliang.factory;

import com.yanliang.utils.ConnectionUtils;

/**
 * Spring ioc 实例化Bean的三种方式
 * 方式一：使用无参构造器（推荐）
 * 方式二：静态方法
 * 方式三：实例化方法
 */
public class CreateBeanFactory {

    /**
     * 方式二：静态方法
     * @return
     */
    public static ConnectionUtils getInstanceStatic() {
        return new ConnectionUtils();
    }

    /**
     * 方式三：实例化方法
     * @return
     */
    public ConnectionUtils getInstance() {
        return new ConnectionUtils();
    }
}
