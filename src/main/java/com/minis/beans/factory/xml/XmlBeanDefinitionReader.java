package com.minis.beans.factory.xml;

import com.minis.beans.*;
import com.minis.beans.factory.config.BeanDefinition;
import com.minis.beans.factory.config.ConstructorArgumentValue;
import com.minis.beans.factory.config.ConstructorArgumentValues;
import com.minis.beans.factory.support.SimpleBeanFactory;
import com.minis.core.Resource;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.List;

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
            Element element = (Element) resource.next();
            String beanId = element.attributeValue("id");
            String beanName = element.attributeValue("class");
            BeanDefinition beanDefinition = new BeanDefinition(beanId, beanName);

            // 处理构造器参数
            List<Element> constructorElements = element.elements("constructor-arg");
            ConstructorArgumentValues avs = new ConstructorArgumentValues();
            for (Element e : constructorElements) {
                String type = e.attributeValue("type");
                String name = e.attributeValue("name");
                String value = e.attributeValue("value");
                ConstructorArgumentValue av = new ConstructorArgumentValue(type, name, value);
                avs.addArgumentValue(av);
            }
            beanDefinition.setConstructorArgumentValues(avs);

            // 处理属性
            List<Element> propertyElements = element.elements("property");
            PropertyValues pvs = new PropertyValues();
            List<String> refs = new ArrayList<>();
            for (Element e : propertyElements) {
                String type = e.attributeValue("type");
                String name = e.attributeValue("name");
                String value = e.attributeValue("value");
                String ref = e.attributeValue("ref");

                boolean isRef = false;
                if (ref != null && !"".equals(ref)) {
                    isRef = true;
                    value = ref;
                    refs.add(ref);
                }
                PropertyValue pv = new PropertyValue(type, name, value, isRef);
                pvs.addPropertyValue(pv);
            }
            beanDefinition.setPropertyValues(pvs);

            // 设置 bean 之间依赖关系
            String[] refArray = refs.toArray(new String[0]);
            beanDefinition.setDependsOn(refArray);

            // 注入到 BeanFactory 容器
            beanFactory.registerBeanDefinition(beanId, beanDefinition);
        }
    }

}
