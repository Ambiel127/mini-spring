package com.minis.beans;

/**
 * Bean 容器
 *
 * @author <a href="mailto:ambiel127@163.com">Matianhao</a>
 * @since 1.0
 */
public interface BeanFactory {

    /**
     * 获取 Bean
     */
    Object getBean(String name) throws BeansException;

    /**
     * 注册 BeanDefinition
     */
    void registerBeanDefinition(BeanDefinition beanDefinition);

}
