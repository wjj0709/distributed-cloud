package com.phoenix.distributed.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author wjj-phoenix
 * @since 2025-09-19
 */
public class SpringUtil implements BeanFactoryPostProcessor, ApplicationContextAware  {
    private static ApplicationContext applicationContext;
    private static ConfigurableListableBeanFactory beanFactory;

    /**
     * 获取配置文件配置项的值
     *
     * @param key 配置项key
     * @return 属性值
     * @since 5.3.3
     */
    public static String getProperty(String key) {
        if (null == applicationContext) {
            return null;
        }
        return applicationContext.getEnvironment().getProperty(key);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        SpringUtil.beanFactory = beanFactory;
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        SpringUtil.applicationContext = applicationContext;
    }
}
