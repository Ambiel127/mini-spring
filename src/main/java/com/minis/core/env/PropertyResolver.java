package com.minis.core.env;

/**
 * 属性解析器，用于获取属性
 *
 * @author <a href="mailto:ambiel127@163.com">Matianhao</a>
 * @since 1.5
 */
public interface PropertyResolver {

    boolean containsProperty(String key);

    String getProperty(String key);

    String getProperty(String key, String defaultValue);

    <T> T getProperty(String key, Class<T> targetType);

    <T> T getProperty(String key, Class<T> targetType, T defaultValue);

    <T> Class<T> getPropertyAsClass(String key, Class<T> targetType);

    String getRequiredProperty(String key) throws IllegalStateException;

    <T> T getRequiredProperty(String key, Class<T> targetType) throws IllegalStateException;

    String resolvePlaceholders(String text);

    String resolveRequiredPlaceholders(String text) throws IllegalArgumentException;

}
