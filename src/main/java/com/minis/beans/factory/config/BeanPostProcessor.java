package com.minis.beans.factory.config;

import com.minis.beans.BeansException;
import com.minis.beans.factory.BeanFactory;

/**
 * Bean 处理器，用于解释注解
 *
 * @author <a href="mailto:ambiel127@163.com">Matianhao</a>
 * @since 1.4
 */
public interface BeanPostProcessor {

    /**
     * Bean 初始化之前
     */
    Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException;

    /**
     * Bean初始化之后
     */
    Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException;

    void setBeanFactory(BeanFactory beanFactory);

}
