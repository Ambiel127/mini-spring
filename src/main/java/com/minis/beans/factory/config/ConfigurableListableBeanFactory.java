package com.minis.beans.factory.config;

import com.minis.beans.factory.ListableBeanFactory;

/**
 * 组合各接口特性
 * <p>
 * interface segregation（接口隔离原则）
 *
 * @author <a href="mailto:ambiel127@163.com">Matianhao</a>
 * @since 1.5
 */
public interface ConfigurableListableBeanFactory extends ListableBeanFactory, ConfigurableBeanFactory, AutowireCapableBeanFactory {
}
