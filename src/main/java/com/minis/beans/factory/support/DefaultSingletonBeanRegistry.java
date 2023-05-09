package com.minis.beans.factory.support;

import com.minis.beans.factory.config.SingletonBeanRegistry;

import java.util.*;
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

    protected Map<String, Set<String>> dependentBeanMap = new ConcurrentHashMap<>(64);
    protected Map<String, Set<String>> dependenciesForBeanMap = new ConcurrentHashMap<>(64);


    @Override
    public void registerSingleton(String beanName, Object singletonObject) {
        // 确保在多线程并发的情况下，仍然能安全地实现对单例 Bean 的管理
        synchronized (this.singletons) {
            Object oldObject = this.singletons.get(beanName);
            if (oldObject != null) {
                throw new IllegalStateException("Could not register object [" + singletonObject +
                                                        "] under bean name '" + beanName + "': there is already object [" + oldObject + "] bound");
            }
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

    public void registerDependentBean(String beanName, String dependentBeanName) {
        Set<String> dependentBeans = this.dependentBeanMap.get(beanName);
        if (dependentBeans != null && dependentBeans.contains(dependentBeanName)) {
            return;
        }

        // No entry yet -> fully synchronized manipulation of the dependentBeans Set
        synchronized (this.dependentBeanMap) {
            dependentBeans = this.dependentBeanMap.get(beanName);
            if (dependentBeans == null) {
                dependentBeans = new LinkedHashSet<String>(8);
                this.dependentBeanMap.put(beanName, dependentBeans);
            }
            dependentBeans.add(dependentBeanName);
        }
        synchronized (this.dependenciesForBeanMap) {
            Set<String> dependenciesForBean = this.dependenciesForBeanMap.get(dependentBeanName);
            if (dependenciesForBean == null) {
                dependenciesForBean = new LinkedHashSet<String>(8);
                this.dependenciesForBeanMap.put(dependentBeanName, dependenciesForBean);
            }
            dependenciesForBean.add(beanName);
        }

    }

    public boolean hasDependentBean(String beanName) {
        return this.dependentBeanMap.containsKey(beanName);
    }
    public String[] getDependentBeans(String beanName) {
        Set<String> dependentBeans = this.dependentBeanMap.get(beanName);
        if (dependentBeans == null) {
            return new String[0];
        }
        return (String[]) dependentBeans.toArray();
    }
    public String[] getDependenciesForBean(String beanName) {
        Set<String> dependenciesForBean = this.dependenciesForBeanMap.get(beanName);
        if (dependenciesForBean == null) {
            return new String[0];
        }
        return (String[]) dependenciesForBean.toArray();

    }
}
