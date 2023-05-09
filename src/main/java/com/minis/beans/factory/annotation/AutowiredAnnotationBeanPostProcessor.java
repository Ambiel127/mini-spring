package com.minis.beans.factory.annotation;

import com.minis.beans.BeansException;
import com.minis.beans.factory.BeanFactory;
import com.minis.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.Field;

/**
 * Autowired Bean 处理器
 *
 * @author <a href="mailto:ambiel127@163.com">Matianhao</a>
 * @since 1.4
 */
public class AutowiredAnnotationBeanPostProcessor implements BeanPostProcessor {

    private BeanFactory beanFactory;

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class<?> clz = bean.getClass();
        Field[] fields = clz.getDeclaredFields();
        for (Field field : fields) {
            boolean isAutowired = field.isAnnotationPresent(Autowired.class);
            if (!isAutowired) {
                continue;
            }
            // 根据属性名获取需要注入的 bean
            String fieldName = field.getName();
            Object autowiredObj = this.beanFactory.getBean(fieldName);

            try {
                field.setAccessible(true);
                field.set(bean, autowiredObj);
                System.out.println("autowire " + fieldName + " for bean " + beanName);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessAfterInitialization: " + beanName);
        return bean;
    }

}
