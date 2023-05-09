package com.minis.context;

/**
 * 上下文刷新事件
 *
 * @author <a href="mailto:ambiel127@163.com">Matianhao</a>
 * @since 1.5
 */
public class ContextRefreshEvent extends ApplicationEvent {

    public ContextRefreshEvent(Object source) {
        super(source);
    }

    @Override
    public String toString() {
        return this.msg;
    }
}
