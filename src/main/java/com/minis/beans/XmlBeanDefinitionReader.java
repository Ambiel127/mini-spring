package com.minis.beans;

import com.minis.core.Resource;
import org.dom4j.Element;

/**
 * 将解析好的 xml Resource 转换成 BeanDefinition
 *
 * @author <a href="mailto:ambiel127@163.com">Matianhao</a>
 * @since 1.0
 */
public class XmlBeanDefinitionReader {

    private BeanFactory beanFactory;

    public XmlBeanDefinitionReader(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    /**
     * 加载 Resource 转换成 BeanDefinition，注册到 BeanFactory
     */
    public void loadBeanDefinitions(Resource resource) {
        while (resource.hasNext()) {
            // 加载解析的内容，构建 BeanDefinition
            Element next = (Element) resource.next();
            String beanId = next.attributeValue("id");
            String beanName = next.attributeValue("class");
            BeanDefinition beanDefinition = new BeanDefinition(beanId, beanName);

            // 注入到 BeanFactory 容器
            beanFactory.registerBeanDefinition(beanDefinition);
        }
    }

}
