package com.minis.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * BeanFactory 的简单实现
 *
 * @author <a href="mailto:ambiel127@163.com">Matianhao</a>
 * @version 1.1
 * a) 继承管理单例 Bean 的默认实现类 DefaultSingletonBeanRegistry 用来实现 BeanFactory 的接口方法，确保通过 SimpleBeanFactory 创建的 Bean 默认就是单例的；
 * b) 实现 BeanDefinitionRegistry 接口，这样 SimpleBeanFactory 既是工厂同时也是仓库；
 * @since 1.0
 */
public class SimpleBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory, BeanDefinitionRegistry {

    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>(256);
    private List<String> beanDefinitionNames = new ArrayList<>();


    // BeanFactory 接口实现

    @Override
    public Object getBean(String beanName) throws BeansException {
        // 先尝试获取 Bean 实例
        Object singleton = this.getSingleton(beanName);
        if (singleton != null) {
            return singleton;
        }

        // 此时还没有这个 bean 的实例，则获取 BeanDefinition 创建实例（延迟创建）
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if (beanDefinition == null) {
            throw new BeansException("No Bean...");
        }
        try {
            singleton = Class.forName(beanDefinition.getClassName()).newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        // 注册单例 bean
        registerSingleton(beanName, singleton);

        return singleton;
    }

    @Override
    public void registerBean(String beanName, Object obj) {
        this.registerSingleton(beanName, obj);
    }

    @Override
    public boolean containsBean(String beanName) {
        return this.containsSingleton(beanName);
    }

    @Override
    public boolean isSingleton(String beanName) {
        return this.beanDefinitionMap.get(beanName).isSingleton();
    }

    @Override
    public boolean isPrototype(String beanName) {
        return this.beanDefinitionMap.get(beanName).isPrototype();
    }

    @Override
    public Class<?> getType(String beanName) {
        return this.beanDefinitionMap.get(beanName).getBeanClass();
    }


    // BeanDefinitionRegistry 接口实现

    @Override
    public void registerBeanDefinition(String name, BeanDefinition bd) {
        this.beanDefinitionMap.put(name, bd);
        this.beanDefinitionNames.add(name);

        // 立即初始化
        if (!bd.isLazyInit()) {
            try {
                getBean(name);
            } catch (BeansException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void removeBeanDefinition(String name) {
        this.beanDefinitionMap.remove(name);
        this.beanDefinitionNames.remove(name);
        this.removeSingleton(name);
    }

    @Override
    public BeanDefinition getBeanDefinition(String name) {
        return this.beanDefinitionMap.get(name);
    }

    @Override
    public boolean containsBeanDefinition(String name) {
        return this.beanDefinitionMap.containsKey(name);
    }
}
