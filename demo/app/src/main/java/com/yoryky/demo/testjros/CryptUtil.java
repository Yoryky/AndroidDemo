/**
 * <p>文件名称: CryptUtil.java</p>
 * <p>文件描述: // </p>
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


/**
 * 加密相关辅助类
 * 加密相关辅助类
 * @author yjing
 * @version 1.0.0
 */
public class CryptUtil {

    /**
     * 使用AES方式加密
     * @param plainText
     * @return
     */
    public static String encodeWithAes(String plainText) {
        try {
            return new AesUtil().encode(getAesKey(), plainText);
        } catch (AesUtil.EnCryptoException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static String decodeWithAes(String plainText){
        try{
            return new AesUtil().decode(getAesKey(),plainText);
        }catch (AesUtil.DeCryptoException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 获得MD5串
     * @param data
     * @return
     */
    public static String encodeMD5(String data) {
        String strMD5 = null;
        try {
            byte[] byteData = data.getBytes("UTF-8");
            byte[] result = CoderUtil.encryptMD5(byteData);
            strMD5 = byteArray2HexStr(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strMD5;
    }

    /**
     * 处理字节数组转换为16进制表示的字符串方法
     * @param bs
     * @return
     */
    private static String byteArray2HexStr(byte[] bs) {
        StringBuffer sb = new StringBuffer();
        for (byte b : bs) {
            sb.append(byte2HexStr(b));
        }
        return sb.toString();
    }

    /**
     * 字节标准移位转十六进制方法
     * @param b
     * @return
     */
    private static String byte2HexStr(byte b) {
        String hexStr = null;
        int n = b;
        if (n < 0) {
            // 若需要自定义加密,请修改这个移位算法即可
            n = b & 0x7F + 128;
        }
        hexStr = Integer.toHexString(n / 16) + Integer.toHexString(n % 16);
        return hexStr.toUpperCase();
    }

    /**
     * 处理字节数组转换为16进制表示的字符串方法二（备用）
     * @param rSArr
     * @return
     */
    public static String toHexStr(byte[] rSArr) {
        StringBuffer md5Str = new StringBuffer(rSArr.length * 2);
        char[] hexChar =
                {
                        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B',
                        'C', 'D', 'E', 'F'
                };
        for (int i = 0; i < rSArr.length; i++) {
            md5Str.append(hexChar[(rSArr[i] & 0xf0) >>> 4]);
            md5Str.append(hexChar[rSArr[i] & 0x0f]);
        }
        return md5Str.toString();
    }

    /**
     * 获得aes加密密钥
     * @return
     */
    //public static native String getAesKey(); todo
    public static String getAesKey() {
        return "123456789aabcdef";
    }

    /**
     * 获得aes向量
     * @return
     */
    // public static native String getAesIv(); todo
    public static String getAesIv() {
        return "0102020303040506";
    }

}
