package com.ps.heat.util;

import java.lang.reflect.Method;

/**
 * Created by xbc on 2015/7/13.
 */
public class Base64Coder {
    /**
     * base64��������
     * @param input Ҫ���������
     * @return �������ַ���
     * @throws Exception
     */
    public static String encodeBase64(byte[]input) throws Exception {
        Class clazz= Class.forName("com.sun.org.apache.xerces.internal.impl.dv.util.Base64");
        Method mainMethod= clazz.getMethod("encode", byte[].class);
        mainMethod.setAccessible(true);
        Object retObj=mainMethod.invoke(null, new Object[]{input});
        return (String)retObj;
    }

    /**
     * base64�����ַ���
     * @param input Ҫ������ַ���
     * @return ����õ�������
     * @throws Exception
     */
    public static byte[] decodeBase64(String input) throws Exception {
        Class clazz= Class.forName("com.sun.org.apache.xerces.internal.impl.dv.util.Base64");
        Method mainMethod= clazz.getMethod("decode", String.class);
        mainMethod.setAccessible(true);
        Object retObj=mainMethod.invoke(null, input);
        return (byte[])retObj;
    }

}
