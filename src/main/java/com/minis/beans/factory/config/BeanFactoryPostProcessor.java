package com.minis.beans.factory.config;

import com.minis.beans.BeansException;
import com.minis.beans.factory.BeanFactory;

/**
 * Bean Factory 处理器
 *
 * @author <a href="mailto:ambiel127@163.com">Matianhao</a>
 * @since 1.5
 */
public interface BeanFactoryPostProcessor {

    void postProcessBeanFactory(BeanFactory beanFactory) throws BeansException;

}
