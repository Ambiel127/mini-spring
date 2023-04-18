package com.minis.test;

import com.minis.beans.factory.annotation.Autowired;

/**
 * 测试 ref 属性
 *
 * @author <a href="mailto:ambiel127@163.com">Matianhao</a>
 * @since 1.3
 */
public class BaseService {

    @Autowired
    private BaseBaseService baseBaseService;

    public BaseBaseService getBaseBaseService() {
        return baseBaseService;
    }

    public void setBaseBaseService(BaseBaseService baseBaseService) {
        this.baseBaseService = baseBaseService;
    }

    public BaseService() {
    }

    public void sayHello() {
        System.out.println("Base Service says hello");
        baseBaseService.sayHello();
    }

    public void init() {
        System.out.println("Base Service init method");
    }
}
