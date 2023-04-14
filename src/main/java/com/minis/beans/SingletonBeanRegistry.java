package com.minis.beans;

/**
 * 管理单例 Bean（作为仓库）
 *
 * @author <a href="mailto:ambiel127@163.com">Matianhao</a>
 * @since 1.1
 */
public interface SingletonBeanRegistry {

    /**
     * 注册单例 bean
     */
    void registerSingleton(String beanName, Object singletonObject);

    /**
     * 获取单例 bean
     */
    Object getSingleton(String beanName);

    /**
     * 是否包含该单例 bean
     */
    boolean containsSingleton(String beanName);

    /**
     * 获取所有单例 bean 名称
     */
    String[] getSingletonNames();

}
