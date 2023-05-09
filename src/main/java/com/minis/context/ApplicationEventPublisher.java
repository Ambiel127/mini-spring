package com.minis.context;

/**
 * 发布事件
 *
 * @author <a href="mailto:ambiel127@163.com">Matianhao</a>
 * @since 1.1
 */
public interface ApplicationEventPublisher {

    void publishEvent(ApplicationEvent event);

    void addApplicationListener(ApplicationListener listener);

}
