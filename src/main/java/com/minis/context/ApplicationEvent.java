package com.minis.context;

import java.util.EventObject;

/**
 * 监听应用的事件
 *
 * @author <a href="mailto:ambiel127@163.com">Matianhao</a>
 * @since 1.1
 */
public class ApplicationEvent extends EventObject {
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ApplicationEvent(Object source) {
        super(source);
    }
}
