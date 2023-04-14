package com.minis.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 管理单例 Bean 的默认实现
 *
 * @author <a href="mailto:ambiel127@163.com">Matianhao</a>
 * @since 1.1
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    /**
     * 容器中存放所有 bean 的名称的列表
     */
    protected List<String> beanNames = new ArrayList<>();

    /**
     * 容器中存放所有 bean 实例的 map
     */
    protected Map<String, Object> singletons = new ConcurrentHashMap<>(256);


    @Override
    public void registerSingleton(String beanName, Object singletonObject) {
        // 确保在多线程并发的情况下，仍然能安全地实现对单例 Bean 的管理
        synchronized (this.singletons) {
            this.singletons.put(beanName, singletonObject);
            this.beanNames.add(beanName);
        }
    }

    @Override
    public Object getSingleton(String beanName) {
        return this.singletons.get(beanName);
    }

    @Override
    public boolean containsSingleton(String beanName) {
        return this.singletons.containsKey(beanName);
    }

    @Override
    public String[] getSingletonNames() {
        return this.beanNames.toArray(new String[0]);
    }

    protected void removeSingleton(String beanName) {
        synchronized (this.singletons) {
            this.beanNames.remove(beanName);
            this.singletons.remove(beanName);
        }
    }
}
