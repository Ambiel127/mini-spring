package com.minis.beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * BeanFactory 的简单实现
 *
 * @author <a href="mailto:ambiel127@163.com">Matianhao</a>
 * @since 1.0
 */
public class SimpleBeanFactory implements BeanFactory {

    private List<BeanDefinition> beanDefinitions = new ArrayList<>();

    private List<String> beanNames = new ArrayList<>();

    private Map<String, Object> singletons = new HashMap<>();


    @Override
    public Object getBean(String name) throws BeansException {
        // 先尝试获取 Bean 实例
        Object targetBean = singletons.get(name);
        if (targetBean != null) {
            return targetBean;
        }

        // 此时还没有这个 bean 的实例，则获取 BeanDefinition 创建实例（延迟创建）
        int i = beanNames.indexOf(name);
        if (i == -1) {
            throw new BeansException();
        }
        BeanDefinition beanDefinition = beanDefinitions.get(i);
        try {
            targetBean = Class.forName(beanDefinition.getClassName()).newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        singletons.put(beanDefinition.getId(), targetBean);

        return targetBean;
    }

    @Override
    public void registerBeanDefinition(BeanDefinition beanDefinition) {
        beanDefinitions.add(beanDefinition);
        beanNames.add(beanDefinition.getId());
    }
}
