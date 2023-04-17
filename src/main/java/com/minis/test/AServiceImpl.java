package com.minis.test;

import java.util.StringJoiner;

/**
 * 测试
 *
 * @author <a href="mailto:ambiel127@163.com">Matianhao</a>
 * @since 1.0
 */
public class AServiceImpl implements AService {

    private String name;
    private int level;
    private String property1;
    private String property2;

    /**
     * @since 1.3 测试 ref 属性
     */
    private BaseService ref1;

    /**
     * setter 注入
     */
    public String getProperty1() {
        return property1;
    }

    public void setProperty1(String property1) {
        this.property1 = property1;
    }

    public String getProperty2() {
        return property2;
    }

    public void setProperty2(String property2) {
        this.property2 = property2;
    }

    public BaseService getRef1() {
        return ref1;
    }

    public void setRef1(BaseService ref1) {
        this.ref1 = ref1;
    }

    /**
     * 构造器注入
     */
    public AServiceImpl() {
    }

    public AServiceImpl(String name, int level) {
        this.name = name;
        this.level = level;
        System.out.println("invoke constructor... name: " + this.name + ",level: " + this.level);
    }

    @Override
    public void sayHello() {
        System.out.println("A service say hello");
        ref1.sayHello();
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", AServiceImpl.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("level=" + level)
                .add("property1='" + property1 + "'")
                .add("property2='" + property2 + "'")
                .add("ref1=" + ref1)
                .toString();
    }
}
