package com.minis.context;

import com.minis.beans.factory.BeanFactory;
import com.minis.beans.BeansException;
import com.minis.beans.factory.support.SimpleBeanFactory;
import com.minis.beans.factory.xml.XmlBeanDefinitionReader;
import com.minis.core.ClassPathXmlResource;
import com.minis.core.Resource;

/**
 * 解析 XML 文件内容，获取 Bean 配置信息
 *
 * @author <a href="mailto:ambiel127@163.com">Matianhao</a>
 * @since 1.0
 */
public class ClassPathXmlApplicationContext implements BeanFactory {

    private SimpleBeanFactory beanFactory;

    /**
     * 获取、注册 bean 的能力交给 BeanFactory
     * 读取、解析配置的能力交给 Resource 和 Reader
     */
    public ClassPathXmlApplicationContext(String fileName, boolean isRefresh) {
        // 解析 XML 文件中的内容
        Resource resource = new ClassPathXmlResource(fileName);
        SimpleBeanFactory beanFactory = new SimpleBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);

        // 读取 BeanDefinition 的配置信息，实例化 Bean，然后把它注入到 BeanFactory 容器中。
        reader.loadBeanDefinitions(resource);
        this.beanFactory = beanFactory;

        if (isRefresh) {
            this.beanFactory.refresh();
        }
    }

    @Override
    public Object getBean(String beanName) throws BeansException {
        return this.beanFactory.getBean(beanName);
    }

    @Override
    public void registerBean(String beanName, Object obj) {
        this.beanFactory.registerBean(beanName, obj);
    }

    @Override
    public boolean containsBean(String beanName) {
        return this.beanFactory.containsBean(beanName);
    }

    @Override
    public boolean isSingleton(String beanName) {
        return false;
    }

    @Override
    public boolean isPrototype(String beanName) {
        return false;
    }

    @Override
    public Class<?> getType(String beanName) {
        return null;
    }
}
