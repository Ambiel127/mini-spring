package com.minis.beans.factory;

import com.minis.beans.BeansException;

import java.util.Map;

/**
 * 特性：将 Factory 内部管理的 Bean 作为一个集合来对待
 *
 * @author <a href="mailto:ambiel127@163.com">Matianhao</a>
 * @since
 */
public interface ListableBeanFactory extends BeanFactory {

    boolean containsBeanDefinition(String beanName);

    int getBeanDefinitionCount();

    String[] getBeanDefinitionNames();

    String[] getBeanNamesForType(Class<?> type);

    <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException;

}
