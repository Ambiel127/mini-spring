package com.minis.context;

import java.util.EventListener;

/**
 * 事件监听器
 *
 * @author <a href="mailto:ambiel127@163.com">Matianhao</a>
 * @since 1.5
 */
public class ApplicationListener implements EventListener {

    void onApplicationEvent(ApplicationEvent event) {
        System.out.println(event.toString());
    }

}
