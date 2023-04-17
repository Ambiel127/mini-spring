package com.minis.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * 封装操作方法，简化调用
 * 提供单个参数对象，也提供集合对象。
 *
 * @author <a href="mailto:ambiel127@163.com">Matianhao</a>
 * @since 1.1
 */

public class PropertyValues {
    private final List<PropertyValue> propertyValueList;

    public PropertyValues() {
        this.propertyValueList = new ArrayList<>(0);
    }

    public List<PropertyValue> getPropertyValueList() {
        return this.propertyValueList;
    }

    public int size() {
        return this.propertyValueList.size();
    }

    public void addPropertyValue(PropertyValue pv) {
        this.propertyValueList.add(pv);
    }

    public void removePropertyValue(PropertyValue pv) {
        this.propertyValueList.remove(pv);
    }

    public void removePropertyValue(String propertyName) {
        this.propertyValueList.remove(getPropertyValue(propertyName));
    }

    public PropertyValue[] getPropertyValues() {
        return this.propertyValueList.toArray(new PropertyValue[0]);
    }

    public PropertyValue getPropertyValue(String propertyName) {
        return this.propertyValueList.stream()
                .filter(item -> item.getName().equals(propertyName))
                .findFirst()
                .orElse(null);
    }

    public Object get(String propertyName) {
        PropertyValue pv = getPropertyValue(propertyName);
        return pv != null ? pv.getValue() : null;
    }

    public boolean contains(String propertyName) {
        return getPropertyValue(propertyName) != null;
    }

    public int getPropertyCount() {
        return (this.propertyValueList.size());
    }

    public PropertyValue getIndexedPropertyValue(int index) {
        return this.propertyValueList.get(index);
    }

    public boolean isEmpty() {
        return this.propertyValueList.isEmpty();
    }
}
