package com.minis.core;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.net.URL;
import java.util.Iterator;

/**
 * 解析 xml 类型 Resource
 *
 * @author <a href="mailto:ambiel127@163.com">Matianhao</a>
 * @see com.minis.beans.XmlBeanDefinitionReader 将解析好的 xml Resource 转换成 BeanDefinition
 * @since 1.0
 */
public class ClassPathXmlResource implements Resource {

    Iterator<Element> elementIterator;

    public ClassPathXmlResource(String fileName) {
        SAXReader reader = new SAXReader();

        try {
            URL xmlPath = this.getClass().getClassLoader().getResource(fileName);
            Document document = reader.read(xmlPath);
            Element rootElement = document.getRootElement();
            elementIterator = rootElement.elementIterator();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean hasNext() {
        return elementIterator.hasNext();
    }

    @Override
    public Object next() {
        return elementIterator.next();
    }
}
