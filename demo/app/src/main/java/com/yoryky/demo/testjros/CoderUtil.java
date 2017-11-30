/**
 * <p>文件名称: CoderUtil.java</p>
 * <p>文件描述: 加码器 </p>
 * <p>版权所有: 版权所有(C)2017-2020</p>
 * <p>公    司: 深圳市金证科技股份有限公司</p>
 * <p>内容摘要: // </p>
 * <p>其他说明: // </p>
 * <p>完成日期：2017/3/1</p>
 *
 * @author PengJian
 * @version 1.0
 */
package com.yoryky.demo.testjros;

//import org.apache.commons.codec.binary.Base64;
import java.security.MessageDigest;

/**
 * 加密相关辅助类
 * 加密相关辅助类
 * @author yjing
 * @version 1.0.0
 * @see CryptUtil
 */
public class CoderUtil {
    public static final String KEY_SHA = "SHA";

    public static final String KEY_MD5 = "MD5";

    /**
     * MAC算法可选以下多种算法
     * <p>
     * <pre>
     * HmacMD5
     * HmacSHA1
     * HmacSHA256
     * HmacSHA384
     * HmacSHA512
     * </pre>
     */
    public static final String KEY_MAC = "HmacMD5";

    /**
     * BASE64解密
     *
     * @param key
     * @return
     * @throws Exception
     */
    /* public static byte[] decryptBASE64(String key) throws Exception
    {
       return Base64.decodeBase64(new String(key).getBytes());
    }*/

    /**
     * BASE64加密
     *
     * @param key
     * @return
     * @throws Exception
     */
    /*public static String encryptBASE64(byte[] key) throws Exception
    {
        byte[] enbytes = Base64.encodeBase64Chunked(key);
        return new String(enbytes);
    }*/

    /**
     * MD5加密
     *
     * @param data
     * @return
     * @throws Exception
     */
    public static byte[] encryptMD5(byte[] data) throws Exception {
        MessageDigest md5 = MessageDigest.getInstance(KEY_MD5);
        md5.update(data);
        return md5.digest();
    }

    /**
     * SHA加密
     *
     * @param data
     * @return
     * @throws Exception
     */
    public static byte[] encryptSHA(byte[] data) throws Exception {
        MessageDigest sha = MessageDigest.getInstance(KEY_SHA);
        sha.update(data);

        return sha.digest();
    }

    /**
     * 初始化HMAC密钥
     *
     * @return
     * @throws Exception
     */
    /*public static String initMacKey() throws Exception
    {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_MAC);

        SecretKey secretKey = keyGenerator.generateKey();
        return encryptBASE64(secretKey.getEncoded());
    }*/

    /**
     * HMAC加密
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    /*public static byte[] encryptHMAC(byte[] data, String key) throws Exception
    {
        SecretKey secretKey = new SecretKeySpec(decryptBASE64(key), KEY_MAC);
        Mac mac = Mac.getInstance(secretKey.getAlgorithm());
        mac.init(secretKey);

        return mac.doFinal(data);
    }*/

}
