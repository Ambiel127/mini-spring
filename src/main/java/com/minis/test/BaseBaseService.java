package com.minis.test;

/**
 * 测试 ref 属性
 *
 * @author <a href="mailto:ambiel127@163.com">Matianhao</a>
 * @since 1.3
 */
public class BaseBaseService {
    private AServiceImpl as;

    public AServiceImpl getAs() {
        return as;
    }

    public void setAs(AServiceImpl as) {
        this.as = as;
    }

    public BaseBaseService() {
    }

    public void sayHello() {
        System.out.println("Base Base Service says hello");

    }
}
