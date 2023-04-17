package com.minis.beans;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * BeanFactory 的简单实现
 *
 * @author <a href="mailto:ambiel127@163.com">Matianhao</a>
 * @version 1.1
 * a) 继承管理单例 Bean 的默认实现类 DefaultSingletonBeanRegistry 用来实现 BeanFactory 的接口方法，确保通过 SimpleBeanFactory 创建的 Bean 默认就是单例的；
 * b) 实现 BeanDefinitionRegistry 接口，这样 SimpleBeanFactory 既是工厂同时也是仓库；
 * @since 1.0
 */
public class SimpleBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory, BeanDefinitionRegistry {

    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>(256);
    private List<String> beanDefinitionNames = new ArrayList<>();

    /**
     * 早期毛坯实例，bean 的三级缓存
     */
    private Map<String, Object> earlySingletonObjects = new HashMap<>(16);

    /**
     * 包装容器启动的各个步骤
     */
    public void refresh() {
        for (String beanName : beanDefinitionNames) {
            try {
                getBean(beanName);
            } catch (BeansException e) {
                e.printStackTrace();
            }
        }
    }


    /*
     BeanFactory 接口实现
     */

    @Override
    public Object getBean(String beanName) throws BeansException {
        // 先尝试获取 Bean 实例
        Object singleton = this.getSingleton(beanName);
        if (singleton != null) {
            return singleton;
        }

        // 如果没有实例，尝试从毛坯实例中获取
        singleton = this.earlySingletonObjects.get(beanName);
        if (singleton != null) {
            return singleton;
        }

        // 此时连毛坯实例都还没有，则获取 BeanDefinition 并创建实例
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if (beanDefinition == null) {
            throw new BeansException("No Bean...");
        }

        // 创建 bean
        singleton = createBean(beanDefinition);

        // 注册单例 bean
        registerSingleton(beanName, singleton);

        // 预留 beanPostProcessor 位置
        // step 1: postProcessBeforeInitialization
        // step 2: afterPropertiesSet
        // step 3: init-method
        // step 4: postProcessAfterInitialization

        return singleton;
    }

    @Override
    public void registerBean(String beanName, Object obj) {
        this.registerSingleton(beanName, obj);
    }

    @Override
    public boolean containsBean(String beanName) {
        return this.containsSingleton(beanName);
    }

    @Override
    public boolean isSingleton(String beanName) {
        return this.beanDefinitionMap.get(beanName).isSingleton();
    }

    @Override
    public boolean isPrototype(String beanName) {
        return this.beanDefinitionMap.get(beanName).isPrototype();
    }

    @Override
    public Class<?> getType(String beanName) {
        return this.beanDefinitionMap.get(beanName).getBeanClass();
    }


    /*
     BeanDefinitionRegistry 接口实现
     */

    @Override
    public void registerBeanDefinition(String name, BeanDefinition bd) {
        this.beanDefinitionMap.put(name, bd);
        this.beanDefinitionNames.add(name);

        // 立即初始化
        if (!bd.isLazyInit()) {
            try {
                getBean(name);
            } catch (BeansException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void removeBeanDefinition(String name) {
        this.beanDefinitionMap.remove(name);
        this.beanDefinitionNames.remove(name);
        this.removeSingleton(name);
    }

    @Override
    public BeanDefinition getBeanDefinition(String name) {
        return this.beanDefinitionMap.get(name);
    }

    @Override
    public boolean containsBeanDefinition(String name) {
        return this.beanDefinitionMap.containsKey(name);
    }

    /**
     * 创建 bean
     *
     * @param bd bean 定义
     * @return 创建的 bean 对象
     */
    private Object createBean(BeanDefinition bd) {
        Class<?> clz = null;
        try {
            clz = Class.forName(bd.getClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // 创建毛坯实例
        Object bean = doCreateBean(bd, clz);

        // 放入毛坯实例缓存中（三级缓存）
        this.earlySingletonObjects.put(bd.getId(), bean);

        // 处理属性
        handleProperties(bd, clz, bean);

        return bean;
    }

    /**
     * 创建早期毛坯 bean，仅调用构造方法，不进行属性处理
     *
     * @param bd  bean 定义
     * @param clz bean class 类型
     * @return bean 实例
     */
    private Object doCreateBean(BeanDefinition bd, Class<?> clz) {
        Object bean = null;

        try {
            // 处理构造器参数
            ArgumentValues argumentValues = bd.getConstructorArgumentValues();
            if (argumentValues.isEmpty()) {
                bean = clz.newInstance();
            } else {
                Class<?>[] paramTypes = new Class[argumentValues.getArgumentCount()];
                Object[] paramValues = new Object[argumentValues.getArgumentCount()];

                for (int i = 0; i < argumentValues.getArgumentCount(); i++) {
                    ArgumentValue argumentValue = argumentValues.getIndexedArgumentValue(i);
                    if ("String".equals(argumentValue.getType())) {
                        paramTypes[i] = String.class;
                        paramValues[i] = argumentValue.getValue();
                    } else if ("int".equals(argumentValue.getType())) {
                        paramTypes[i] = int.class;
                        paramValues[i] = Integer.valueOf((String) argumentValue.getValue());
                    } else {
                        paramTypes[i] = String.class;
                        paramValues[i] = argumentValue.getValue();
                    }
                }

                // 反射获取构造器，创建 bean
                Constructor<?> con = clz.getConstructor(paramTypes);
                bean = con.newInstance(paramValues);

            }
        } catch (IllegalAccessException | InstantiationException
                | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return bean;
    }

    /**
     * 处理属性
     *
     * @param bd   bean 定义
     * @param clz  bean class 类型
     * @param bean bean 对象
     */
    private void handleProperties(BeanDefinition bd, Class<?> clz, Object bean) {
        System.out.println("handle properties for bean : " + bd.getId());

        PropertyValues propertyValues = bd.getPropertyValues();
        if (!propertyValues.isEmpty()) {
            for (int i = 0; i < propertyValues.getPropertyCount(); i++) {
                PropertyValue propertyValue = propertyValues.getIndexedPropertyValue(i);
                String type = propertyValue.getType();
                String name = propertyValue.getName();
                Object value = propertyValue.getValue();
                boolean isRef = propertyValue.isRef();

                Class<?> paramType = null;

                if (isRef) {
                    // 是 ref，创建依赖的 bean
                    try {
                        // type 值例如 com.minis.test.AServiceImpl
                        paramType = Class.forName(type);
                        // value 设置的是 ref 的值，例如 aService。这里获取另一个 bean 的实例（毛坯 bean），实现注入
                        value = getBean((String) value);
                    } catch (ClassNotFoundException | BeansException e) {
                        e.printStackTrace();
                    }
                } else {
                    // 不是 ref，根据数据类型分别处理每一个属性
                    if ("String".equals(type)) {
                        paramType = String.class;
                    } else if ("int".equals(type)) {
                        paramType = int.class;
                    } else {
                        paramType = String.class;
                    }
                }

                String methodName = "set" + name.substring(0, 1).toUpperCase() + name.substring(1);

                try {
                    // 反射获取 set 方法，设置 bean 属性值
                    Method method = clz.getMethod(methodName, paramType);
                    method.invoke(bean, value);
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
