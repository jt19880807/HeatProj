package com.ps.heat.configurer;

import com.ps.heat.util.Base64Coder;
import com.ps.heat.util.DESUtil;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * Created by xbc on 2015/9/10.
 */
public class EncryptPropertyPlaceholderConfigurer
        extends PropertyPlaceholderConfigurer
{
    private String[] encryptPropNames = { "", "" };
    private static final String DES_VECTOR="S8g6%9h*";

    @Override
    protected String convertProperty(String propertyName, String propertyValue) {
        if (isEncryptProp(propertyName)) {
            String decryptValue = null;
            try {
                decryptValue = new String(
                        DESUtil.decrypt(
                                Base64Coder.decodeBase64(propertyValue), DES_VECTOR.getBytes()
                        )
                );
            } catch (Exception e) {
                e.printStackTrace();
            }
//            System.out.println(propertyName + "解密内容:" + decryptValue);
            return decryptValue;
        } else {
            return propertyValue;
        }
    }

    /**
     * 判断是否是加密的属性
     *
     * @param propertyName
     * @return
     */
    private boolean isEncryptProp(String propertyName) {
        for (String encryptpropertyName : encryptPropNames) {
            if (encryptpropertyName.equals(propertyName))
                return true;
        }
        return false;
    }
}
