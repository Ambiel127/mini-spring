package com.minis.beans;

/**
 * Bean 容器（作为工厂）
 *
 * @author <a href="mailto:ambiel127@163.com">Matianhao</a>
 * @since 1.0
 */
public interface BeanFactory {

    /**
     * 获取 Bean
     */
    Object getBean(String beanName) throws BeansException;

    /**
     * 注册 Bean
     */
    void registerBean(String beanName, Object obj);

    /**
     * 是否包含该 Bean
     */
    boolean containsBean(String beanName);

    /**
     * 是否为单例 Bean
     */
    boolean isSingleton(String beanName);

    /**
     * 是否为原型 Bean
     */
    boolean isPrototype(String beanName);

    /**
     * 获取 Bean Class 类型
     */
    Class<?> getType(String beanName);

}
