package com.ps.heat.util;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.SecureRandom;

/**
 * Created by xbc on 2015/7/24.
 */
public class DESUtil {
    public static final String KEY_ALGORITHM = "DES";
    public static final String CIPHER_ALGORITHM = "DES/ECB/PKCS5Padding";

    /** 解密字符串
     * @param str 要解密的字符串
     * @param DESKey DES密钥
     * @return 解密后的字符串
     * @throws Exception
     */
    public static String decrypt(String str,String DESKey)throws Exception
    {
        return new String(decrypt(str.getBytes(), DESKey.getBytes()));
    }

    /**
     * 解密数据
     * @param data 要解密的数据
     * @param key DES密钥
     * @return 解密后的数据
     * @throws Exception
     */
    public static byte[] decrypt(byte[] data,byte[] key)throws Exception
    {
        //DES算法要求有一个可信任的随机数源
        SecureRandom sr = new SecureRandom();
        //创建一个DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);
        // 创建一个密匙工厂
        // 将DESKeySpec对象转换成SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(KEY_ALGORITHM);
        SecretKey securekey = keyFactory.generateSecret(dks);
        // Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        //用密匙初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, securekey, sr);
        //真正开始解密操作
        return cipher.doFinal(data);
    }

    /**
     * 对字符串进行DES加密
     * @param str 要加密的字符串
     * @param DESKey DES加密密钥
     * @return 加密后的字符串
     * @throws Exception
     */
    public static String encrypt(String str, String DESKey)throws Exception
    {
        return new String(encrypt(str.getBytes(),DESKey.getBytes()));
    }

    /**
     * 对数据进行加密
     * @param data 要加密的数据
     * @param key DES密钥
     * @return 加密后的数据
     * @throws Exception
     */
    public static byte[] encrypt(byte[] data, byte[] key) throws Exception
    {
        // DES算法要求有一个可信任的随机数源
        SecureRandom sr = new SecureRandom();
        //创建一个DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);
        // 创建一个密匙工厂
        // 将DESKeySpec对象转换成SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(KEY_ALGORITHM);
        SecretKey securekey = keyFactory.generateSecret(dks);
        // Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        //用密匙初始化Cipher对象
        cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);
        //现在，获取数据并加密
        //正式执行加密操作
        return cipher.doFinal(data);
    }

    public static void main(String[] args) {
        try {
            String str = "root";
            String cipherStr= "";
            String myKey = "S8g6%9h*";
            System.out.println("the original string is :"+str);

//            byte[] receiveBytes = Base64Coder.decodeBase64(cipherStr);
//            System.out.println("the decrypted string is :"+new String(receiveBytes));
//            byte[] decrytBytes = DESUtil.decrypt(receiveBytes, myKey.getBytes());
//            System.out.println("the decrypted string is :"+new String(decrytBytes));
            byte[] encrytBytes = DESUtil.encrypt(str.getBytes(), myKey.getBytes());
            System.out.println("the encrypted string is :"+ Base64Coder.encodeBase64(encrytBytes));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
