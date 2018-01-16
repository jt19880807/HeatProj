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
     * ��ȡRSA��Կ��
     * @return KeyPair RSA��Կ��
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
     * ���ݹ�Կ�洢�ַ�����ȡ��Կ
     * @param key ��Կ�ַ���������base64���룩
     * @return PublicKey RSA��Կ
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
     * ����˽Կ�洢�ַ�����ȡ˽Կ
     * @param key ��Կ�ַ���������base64���룩
     * @return PrivateKey ˽Կ
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
     * �õ���Կ�ַ���������base64���룩
     * @return String Base64����֮�����Կ�ַ���
     */
    public static String getKeyString(Key key) throws Exception {
        byte[] keyBytes = key.getEncoded();
        String s = (new BASE64Encoder()).encode(keyBytes);
        return s;
    }

    /**
     * ��˽Կ��������
     * @param data Ҫ���ܵ�����
     * @param key ����base64������˽Կ�����ַ���
     * @return ���ܺ������
     * @throws Exception
     */
    public static byte[] decryptByPrivateKey(byte[] data, String key)
            throws Exception {
        // ����Կ����
        byte[] keyBytes = Base64Coder.decodeBase64(key);

        // ȡ��˽Կ
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);

        // �����ݽ���
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher.doFinal(data);
    }

    /**
     * �ù�Կ��������
     * @param data Ҫ���ܵ�����
     * @param key ����base64�����Ĺ�Կ�����ַ���
     * @return ���ܺ������
     * @throws Exception
     */
    public static byte[] encryptByPublicKey(byte[] data, String key)
            throws Exception {
        // ����Կ����
        byte[] keyBytes = Base64Coder.decodeBase64(key);;

        // ȡ�ù�Կ
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PublicKey publicKey = keyFactory.generatePublic(keySpec);

        // �����ݼ���
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
