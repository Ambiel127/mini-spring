package com.minis.beans.factory.config;

import java.util.ArrayList;
import java.util.List;

/**
 * 封装操作方法，简化调用
 * 提供单个参数对象，也提供集合对象。
 *
 * @author <a href="mailto:ambiel127@163.com">Matianhao</a>
 * @since 1.1
 */

public class ConstructorArgumentValues {
    private final List<ConstructorArgumentValue> argumentValueList = new ArrayList<>();

    public ConstructorArgumentValues() {
    }

    public void addArgumentValue(ConstructorArgumentValue argumentValue) {
        this.argumentValueList.add(argumentValue);
    }

    public ConstructorArgumentValue getIndexedArgumentValue(int index) {
        return this.argumentValueList.get(index);
    }

    public int getArgumentCount() {
        return (this.argumentValueList.size());
    }

    public boolean isEmpty() {
        return (this.argumentValueList.isEmpty());
    }
}