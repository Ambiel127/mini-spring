package com.minis.context;

import java.util.EventObject;

/**
 * 监听应用的事件
 *
 * @author <a href="mailto:ambiel127@163.com">Matianhao</a>
 * @since 1.1
 */
public class ApplicationEvent extends EventObject {

    private static final long serialVersionUID = 1L;
    protected String msg;

    public ApplicationEvent(Object source) {
        super(source);
        this.msg = source.toString();
    }
}
