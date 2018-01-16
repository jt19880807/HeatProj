package com.ps.heat.util;


import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * Created by xbc on 2015/7/24.
 */
public class RSAUtil {
    public static final String KEY_ALGORITHM = "RSA";
    public static final String CIPHER_ALGORITHM = "RSA/ECB/OAEPWithSHA-1AndMGF1Padding";

    /**
     * 获取RSA密钥对
     * @return KeyPair RSA密钥对
     * @throws Exception
     */
    public static KeyPair getRSAKeyPair()throws Exception
    {
        SecureRandom random = new SecureRandom();
        KeyPairGenerator keyPairGen = KeyPairGenerator
                .getInstance(KEY_ALGORITHM);
        keyPairGen.initialize(2048,random);

        KeyPair keyPair = keyPairGen.generateKeyPair();
        return keyPair;
    }

    /**
     * 根据公钥存储字符串获取公钥
     * @param key 密钥字符串（经过base64编码）
     * @return PublicKey RSA公钥
     * @throws Exception
     */
    public static PublicKey getPublicKey(String key) throws Exception {
        byte[] keyBytes;
        keyBytes = (new BASE64Decoder()).decodeBuffer(key);

        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(keySpec);
        return publicKey;
    }
    /**
     * 根据私钥存储字符串获取私钥
     * @param key 密钥字符串（经过base64编码）
     * @return PrivateKey 私钥
     * @throws Exception
     */
    public static PrivateKey getPrivateKey(String key) throws Exception {
        byte[] keyBytes;
        keyBytes = (new BASE64Decoder()).decodeBuffer(key);

        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
        return privateKey;
    }

    /**
     * 得到密钥字符串（经过base64编码）
     * @return String Base64编码之后的密钥字符串
     */
    public static String getKeyString(Key key) throws Exception {
        byte[] keyBytes = key.getEncoded();
        String s = (new BASE64Encoder()).encode(keyBytes);
        return s;
    }

    /**
     * 用私钥解密数据
     * @param data 要解密的数据
     * @param key 经过base64编码后的私钥描述字符串
     * @return 解密后的数据
     * @throws Exception
     */
    public static byte[] decryptByPrivateKey(byte[] data, String key)
            throws Exception {
        // 对密钥解密
        byte[] keyBytes = Base64Coder.decodeBase64(key);

        // 取得私钥
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);

        // 对数据解密
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher.doFinal(data);
    }

    /**
     * 用公钥加密数据
     * @param data 要加密的数据
     * @param key 经过base64编码后的公钥描述字符串
     * @return 加密后的数据
     * @throws Exception
     */
    public static byte[] encryptByPublicKey(byte[] data, String key)
            throws Exception {
        // 对密钥解密
        byte[] keyBytes = Base64Coder.decodeBase64(key);;

        // 取得公钥
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PublicKey publicKey = keyFactory.generatePublic(keySpec);

        // 对数据加密
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(data);
    }

    public static void main(String[] args) {
        try {

            KeyPair keyPair = RSAUtil.getRSAKeyPair();
            RSAPublicKey publicKey = (RSAPublicKey)keyPair.getPublic();
            System.out.println("the length of public key:"+publicKey.getEncoded().length);
            String publicKeyStr= Base64Coder.encodeBase64(publicKey.getEncoded());
            System.out.println("the length of public key str:"+publicKeyStr.length());
            RSAPrivateKey privateKey = (RSAPrivateKey)keyPair.getPrivate();
            String privateKeyStr = Base64Coder.encodeBase64(privateKey.getEncoded());
            String str = "1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890";
            System.out.println("the original string is :"+str);

            System.out.println("the public key:"+publicKeyStr);
            System.out.println("the private key:"+privateKeyStr);
            byte[] encrytBytes = RSAUtil.encryptByPublicKey(str.getBytes(), publicKeyStr);
            System.out.println("the encrypted string is :"+new String(encrytBytes));

            byte[] decrytBytes = RSAUtil.decryptByPrivateKey(encrytBytes, privateKeyStr);
            System.out.println("the decrypted string is :"+new String(decrytBytes));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
