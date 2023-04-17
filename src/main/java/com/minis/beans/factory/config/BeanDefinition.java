package com.minis.beans.factory.config;

import com.minis.beans.PropertyValues;

/**
 * Bean 的定义
 *
 * @author <a href="mailto:ambiel127@163.com">Matianhao</a>
 * @since 1.0
 */
public class BeanDefinition {

    final String SCOPE_SINGLETON = "singleton";
    final String SCOPE_PROTOTYPE = "prototype";

    /**
     * Bean 唯一标识
     */
    private String id;
    /**
     * 全类名
     */
    private String className;

    /**
     * 是否延迟初始化，默认 true 延迟初始化
     */
    private boolean lazyInit = true;
    /**
     * 声明初始化方法
     */
    private String initMethodName;
    /**
     * Bean 之间的依赖关系
     */
    private String[] dependsOn;
    /**
     * 构造器参数
     */
    private ConstructorArgumentValues constructorArgumentValues;
    /**
     * 属性列表
     */
    private PropertyValues propertyValues;
    /**
     * Bean 的模式：单例还是原型
     */
    private String scope = SCOPE_SINGLETON;

    private volatile Object beanClass;


    // 简化操作

    public boolean hasBeanClass() {
        return (this.beanClass instanceof Class);
    }

    public boolean isSingleton() {
        return SCOPE_SINGLETON.equals(scope);
    }

    public boolean isPrototype() {
        return SCOPE_PROTOTYPE.equals(scope);
    }

    public boolean isLazyInit() {
        return this.lazyInit;
    }

    public boolean hasConstructorArgumentValues() {
        return !this.constructorArgumentValues.isEmpty();
    }


    // constructor

    public BeanDefinition(String id, String className) {
        this.id = id;
        this.className = className;
    }


    // getter and setter

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setBeanClass(Class<?> beanClass) {
        this.beanClass = beanClass;
    }

    public Class<?> getBeanClass() {
        return (Class<?>) this.beanClass;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getScope() {
        return this.scope;
    }

    public void setLazyInit(boolean lazyInit) {
        this.lazyInit = lazyInit;
    }

    public void setDependsOn(String... dependsOn) {
        this.dependsOn = dependsOn;
    }

    public String[] getDependsOn() {
        return this.dependsOn;
    }

    public void setConstructorArgumentValues(ConstructorArgumentValues constructorArgumentValues) {
        this.constructorArgumentValues =
                (constructorArgumentValues != null ? constructorArgumentValues : new ConstructorArgumentValues());
    }

    public ConstructorArgumentValues getConstructorArgumentValues() {
        return this.constructorArgumentValues;
    }

    public void setPropertyValues(PropertyValues propertyValues) {
        this.propertyValues = (propertyValues != null ? propertyValues : new PropertyValues());
    }

    public PropertyValues getPropertyValues() {
        return this.propertyValues;
    }

    public void setInitMethodName(String initMethodName) {
        this.initMethodName = initMethodName;
    }

    public String getInitMethodName() {
        return this.initMethodName;
    }
}
