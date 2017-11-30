/**
 * <p>文件名称: DesUtil.java</p>
 * <p>文件描述: DES加解密</p>
 * <p>版权所有: 版权所有(C)2017-2020</p>
 * <p>公    司: 深圳市金证科技股份有限公司</p>
 * <p>内容摘要: // </p>
 * <p>其他说明: // </p>
 * <p>完成日期：2017/11/6</p>
 *
 * @author unknown
 * @version 1.0.0
 */
package com.yoryky.demo.testjros;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 * DES操作方法实现
 * 提供了DES加密和解密两个方法
 * @author  yjing
 * @version 1.0.0
 */
public class DesUtil {

    public DesUtil() {

    }

    /**
     *
     * @param dataSource  需要加密的数据源
     * @param password    加密密钥
     * @return 返回加密后的二进制数据流
     */
    public static byte[] encrypt(byte[] dataSource, String password) {
        try {
            SecureRandom random = new SecureRandom();
            DESKeySpec desKey = new DESKeySpec(password.getBytes());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey securekey = keyFactory.generateSecret(desKey);
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
            return cipher.doFinal(dataSource);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param src  需要解密的二进制数据流
     * @param password  解密密钥
     * @return 解密后的二进制数据流
     * @throws Exception
     */
    public static byte[] decrypt(byte[] src, String password) throws Exception {
        SecureRandom random = new SecureRandom();
        DESKeySpec desKey = new DESKeySpec(password.getBytes());
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey securekey = keyFactory.generateSecret(desKey);
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.DECRYPT_MODE, securekey, random);
        return cipher.doFinal(src);
    }

}
