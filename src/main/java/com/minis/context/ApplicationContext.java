package com.minis.context;

import com.minis.beans.BeansException;
import com.minis.beans.factory.ListableBeanFactory;
import com.minis.beans.factory.config.BeanFactoryPostProcessor;
import com.minis.beans.factory.config.ConfigurableBeanFactory;
import com.minis.beans.factory.config.ConfigurableListableBeanFactory;
import com.minis.core.env.Environment;
import com.minis.core.env.EnvironmentCapable;

/**
 * 作为公共接口，所有的上下文都实现该接口，支持上下文环境和事件发布
 *
 * @author <a href="mailto:ambiel127@163.com">Matianhao</a>
 * @since 1.5
 */
public interface ApplicationContext extends EnvironmentCapable, ApplicationEventPublisher, ListableBeanFactory, ConfigurableBeanFactory {

    String getApplicationName();

    long getStartupDate();

    ConfigurableListableBeanFactory getBeanFactory() throws IllegalStateException;

    void setEnvironment(Environment environment);

    void addBeanFactoryPostProcessor(BeanFactoryPostProcessor postProcessor);

    void refresh() throws BeansException, IllegalStateException;

    void close();

    boolean isActive();

}
