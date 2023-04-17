package com.minis.test;

import com.minis.context.ClassPathXmlApplicationContext;
import com.minis.beans.BeansException;

/**
 * 测试入口
 *
 * @author <a href="mailto:ambiel127@163.com">Matianhao</a>
 * @since 1.0
 */
public class Test {

    public static void main(String[] args) throws BeansException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml", true);
        AService aService = (AService) context.getBean("aService");
        aService.sayHello();
        System.out.println(aService.toString());
    }

}
