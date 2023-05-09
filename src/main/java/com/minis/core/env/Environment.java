package com.minis.core.env;

/**
 * 环境，获取配置文件
 *
 * @author <a href="mailto:ambiel127@163.com">Matianhao</a>
 * @since 1.5
 */
public interface Environment extends PropertyResolver {

    String[] getActiveProfiles();

    String[] getDefaultProfiles();

    boolean acceptsProfiles(String... profiles);

}
