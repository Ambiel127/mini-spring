package com.minis.context;

import com.minis.beans.BeansException;
import com.minis.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import com.minis.beans.factory.config.ConfigurableListableBeanFactory;
import com.minis.beans.factory.support.DefaultListableBeanFactory;
import com.minis.beans.factory.xml.XmlBeanDefinitionReader;
import com.minis.core.ClassPathXmlResource;
import com.minis.core.Resource;

/**
 * 解析 XML 文件内容，获取 Bean 配置信息
 *
 * @author <a href="mailto:ambiel127@163.com">Matianhao</a>
 * @since 1.0
 */
public class ClassPathXmlApplicationContext extends AbstractApplicationContext {

    private DefaultListableBeanFactory beanFactory;

    public ClassPathXmlApplicationContext(String fileName) {
        this(fileName, true);
    }

    /**
     * 获取、注册 bean 的能力交给 BeanFactory
     * 读取、解析配置的能力交给 Resource 和 Reader
     */
    public ClassPathXmlApplicationContext(String fileName, boolean isRefresh) {
        // 解析 XML 文件中的内容
        Resource resource = new ClassPathXmlResource(fileName);
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);

        // 读取 BeanDefinition 的配置信息，实例化 Bean，然后把它注入到 BeanFactory 容器中。
        reader.loadBeanDefinitions(resource);
        this.beanFactory = beanFactory;

        if (isRefresh) {
            try {
                refresh();
            } catch (BeansException e) {
                e.printStackTrace();
            }
        }
    }

    // --------- refresh step start ---------

    @Override
    void postProcessBeanFactory(ConfigurableListableBeanFactory bf) {
    }

    @Override
    void registerBeanPostProcessors(ConfigurableListableBeanFactory bf) {
        this.beanFactory.addBeanPostProcessor(new AutowiredAnnotationBeanPostProcessor());
    }

    @Override
    void initApplicationEventPublisher() {
        ApplicationEventPublisher aep = new SimpleApplicationEventPublisher();
        this.setApplicationEventPublisher(aep);
    }

    @Override
    void onRefresh() {
        this.beanFactory.refresh();
    }

    @Override
    void registerListeners() {
        ApplicationListener listener = new ApplicationListener();
        addApplicationListener(listener);
    }

    @Override
    void finishRefresh() {
        publishEvent(new ContextRefreshEvent("--- Context Refreshed ---"));
        publishEvent(new ContextRefreshEvent(
                "--- " + getApplicationName() + " start completed at " + getStartupDate() + " ---"));

    }

    // --------- refresh step end ---------


    @Override
    public ConfigurableListableBeanFactory getBeanFactory() throws IllegalStateException {
        return this.beanFactory;
    }


    // publish event

    @Override
    public void addApplicationListener(ApplicationListener listener) {
        this.getApplicationEventPublisher().addApplicationListener(listener);

    }

    @Override
    public void publishEvent(ApplicationEvent event) {
        this.getApplicationEventPublisher().publishEvent(event);

    }
}
