package com.minis.beans;

import com.minis.core.Resource;
import org.dom4j.Element;

/**
 * 将解析好的 xml Resource 转换成 BeanDefinition
 *
 * @author <a href="mailto:ambiel127@163.com">Matianhao</a>
 * @version 1.1 构造注入的 beanFactory 暂时改为其实现类 SimpleBeanFactory，降低了扩展性。因为注册 beanDefinition 的方法被抽到了 BeanDefinitionRegistry 接口，后面会一步步演变。
 * @since 1.0
 */
public class XmlBeanDefinitionReader {

    private SimpleBeanFactory beanFactory;

    public XmlBeanDefinitionReader(SimpleBeanFactory beanFactory) {
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
            beanFactory.registerBeanDefinition(beanId, beanDefinition);
        }
    }

}
