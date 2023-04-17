package com.minis.beans.factory.support;

import com.minis.beans.factory.config.BeanDefinition;

/**
 * BeanDefinition 容器，提供 BeanDefinition 操作方法（作为仓库）
 *
 * @author <a href="mailto:ambiel127@163.com">Matianhao</a>
 * @since 1.1
 */
public interface BeanDefinitionRegistry {

    /**
     * 注册
     */
    void registerBeanDefinition(String name, BeanDefinition bd);

    /**
     * 移除
     */
    void removeBeanDefinition(String name);

    /**
     * 获取
     */
    BeanDefinition getBeanDefinition(String name);

    /**
     * 是否包含
     */
    boolean containsBeanDefinition(String name);
}
