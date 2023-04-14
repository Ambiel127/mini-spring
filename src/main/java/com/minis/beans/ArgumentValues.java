package com.minis.beans;

import java.util.*;

/**
 * 封装操作方法，简化调用
 * 提供单个参数对象，也提供集合对象。
 *
 * @author <a href="mailto:ambiel127@163.com">Matianhao</a>
 * @since 1.1
 */

public class ArgumentValues {
    private final Map<Integer, ArgumentValue> indexedArgumentValues = new HashMap<>(0);
    private final List<ArgumentValue> genericArgumentValues = new LinkedList<>();

    public ArgumentValues() {
    }

    private void addArgumentValue(Integer key, ArgumentValue newValue) {
        this.indexedArgumentValues.put(key, newValue);
    }

    public boolean hasIndexedArgumentValue(int index) {
        return this.indexedArgumentValues.containsKey(index);
    }

    public ArgumentValue getIndexedArgumentValue(int index) {
        return this.indexedArgumentValues.get(index);
    }

    public void addGenericArgumentValue(String type, Object value) {
        this.genericArgumentValues.add(new ArgumentValue(type, value));
    }

    private void addGenericArgumentValue(ArgumentValue newValue) {
        if (newValue.getName() != null) {
            this.genericArgumentValues.removeIf(currentValue -> newValue.getName().equals(currentValue.getName()));
        }
        this.genericArgumentValues.add(newValue);
    }

    public ArgumentValue getGenericArgumentValue(String requiredName) {
        for (ArgumentValue value : this.genericArgumentValues) {
            if (value.getName() != null && !value.getName().equals(requiredName)) {
                continue;
            }
            return value;
        }
        return null;
    }

    public int getArgumentCount() {
        return this.genericArgumentValues.size();
    }

    public boolean isEmpty() {
        return this.genericArgumentValues.isEmpty();
    }
}