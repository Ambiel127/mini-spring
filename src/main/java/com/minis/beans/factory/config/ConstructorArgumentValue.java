package com.minis.beans.factory.config;

/**
 * 构造器注入属性类
 *
 * @author <a href="mailto:ambiel127@163.com">Matianhao</a>
 * @since 1.1
 */
public class ConstructorArgumentValue {

    private String type;

    private String name;

    private Object value;

    public ConstructorArgumentValue() {
    }

    public ConstructorArgumentValue(String type, Object value) {
        this.type = type;
        this.value = value;
    }

    public ConstructorArgumentValue(String type, String name, Object value) {
        this.type = type;
        this.name = name;
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
