package com.minis.beans.factory.config;

import com.minis.beans.BeansException;
import com.minis.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import com.minis.beans.factory.support.AbstractBeanFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Autowired 能力的 beanFactory
 *
 * @author <a href="mailto:ambiel127@163.com">Matianhao</a>
 * @since 1.4
 */
public class AutowireCapableBeanFactory extends AbstractBeanFactory {

    /**
     * 可以按照需求注册若干个不同用途的处理器
     */
    private final List<AutowiredAnnotationBeanPostProcessor> beanPostProcessors = new ArrayList<>();

    public void addBeanPostProcessor(AutowiredAnnotationBeanPostProcessor beanPostProcessor) {
        this.beanPostProcessors.remove(beanPostProcessor);
        this.beanPostProcessors.add(beanPostProcessor);
    }

    public int getBeanPostProcessorCount() {
        return this.beanPostProcessors.size();
    }


    @Override
    public Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeansException {
        Object result = existingBean;
        for (AutowiredAnnotationBeanPostProcessor processor : beanPostProcessors) {
            processor.setBeanFactory(this);
            result = processor.postProcessBeforeInitialization(result, beanName);
            if (result != null) {
                return result;
            }
        }
        return result;
    }

    @Override
    public Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeansException {
        Object result = existingBean;
        for (AutowiredAnnotationBeanPostProcessor processor : beanPostProcessors) {
            result = processor.postProcessAfterInitialization(result, beanName);
            if (result != null) {
                return result;
            }
        }
        return result;
    }

}
