package com.ps.heat.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * ��ȡspring�������Է��������ж��������bean
 * Created by xbc on 2015/8/6.
 */
public class SpringContextUtil implements ApplicationContextAware {

    // SpringӦ�������Ļ���
    private static ApplicationContext applicationContext;

    /**
     * ʵ��ApplicationContextAware�ӿڵĻص����������������Ļ���
     *
     * @param applicationContext
     */
    public void setApplicationContext(ApplicationContext applicationContext) {
        SpringContextUtil.applicationContext = applicationContext;
    }

    /**
     * @return ApplicationContext
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * ��ȡ����
     * ������д��bean����������Ҫ����
     * @param name
     * @return Object һ������������ע���bean��ʵ��
     * @throws BeansException
     */
    public static Object getBean(String name) throws BeansException {
        return applicationContext.getBean(name);
    }

    /**
     * ���applicationContext��̬����.
     */
    public static void cleanApplicationContext() {
        applicationContext = null;
    }

    private static void checkApplicationContext() {
        if (applicationContext == null) {
            throw new IllegalStateException("applicaitonContextδע��,����applicationContext.xml�ж���SpringContextHolder");
        }
    }
}


