package cn.lz.hospital.utils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.math.BigDecimal;
import java.security.SecureRandom;

/**
* @Description:    字符串加密工具
* @Author:         zhuwc
* @CreateDate:     2019/1/7 16:53
* @Version:        1.0
*/
public class DesUtil {
	/**
	 * 秘钥
	 */
	private static final String PASSWORD_CRYPT_KEY = "7zMw$0j#";
	private final static String DES = "DES";


	/**
	 * 加密
	 *
	 * @param src 数据源
	 * @param key 密钥，长度必须是8的倍数
	 * @return 返回加密后的数据
	 * @throws Exception
	 */
	public static byte[] encrypt(byte[] src, byte[] key) throws Exception {
		// DES算法要求有一个可信任的随机数源
		SecureRandom sr = new SecureRandom();
		// 从原始密匙数据创建DESKeySpec对象
		DESKeySpec dks = new DESKeySpec(key);
		// 创建一个密匙工厂，然后用它把DESKeySpec转换成
		// 一个SecretKey对象
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
		SecretKey securekey = keyFactory.generateSecret(dks);
		// Cipher对象实际完成加密操作
		Cipher cipher = Cipher.getInstance(DES);
		// 用密匙初始化Cipher对象
		cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);
		// 现在，获取数据并加密
		// 正式执行加密操作
		return cipher.doFinal(src);
	}

	/**
	 * 解密
	 *
	 * @param src 数据源
	 * @param key 密钥，长度必须是8的倍数
	 * @return 返回解密后的原始数据
	 * @throws Exception
	 */
	public static byte[] decrypt(byte[] src, byte[] key) throws Exception {
		// DES算法要求有一个可信任的随机数源
		SecureRandom sr = new SecureRandom();
		// 从原始密匙数据创建一个DESKeySpec对象
		DESKeySpec dks = new DESKeySpec(key);
		// 创建一个密匙工厂，然后用它把DESKeySpec对象转换成
		// 一个SecretKey对象
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
		SecretKey securekey = keyFactory.generateSecret(dks);
		// Cipher对象实际完成解密操作
		Cipher cipher = Cipher.getInstance(DES);
		// 用密匙初始化Cipher对象
		cipher.init(Cipher.DECRYPT_MODE, securekey, sr);
		// 现在，获取数据并解密
		// 正式执行解密操作
		return cipher.doFinal(src);
	}


	/**
	 * 解密
	 *
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public final static String decrypt2(String data,String key) {
		try {
			return new String(decrypt(hex2byte(data.getBytes()),
					key.getBytes()));
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * 加密
	 *
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public final static String encrypt2(String password,String key) {
		try {
			return byte2hex(encrypt(password.getBytes(), key.getBytes()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}



	/**
	 * 解密
	 *
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public final static String decrypt(String data) {
		try {
			return new String(decrypt(hex2byte(data.getBytes()),
					PASSWORD_CRYPT_KEY.getBytes()));
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * 加密
	 *
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public final static String encrypt(String password) {
		try {
			return byte2hex(encrypt(password.getBytes(), PASSWORD_CRYPT_KEY
					.getBytes()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 二行制转字符串
	 *
	 * @param b
	 * @return
	 */
	public static String byte2hex(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else
				hs = hs + stmp;
		}
		return hs.toUpperCase();
	}

	public static byte[] hex2byte(byte[] b) {
		if ((b.length % 2) != 0)
			throw new IllegalArgumentException("长度不是偶数");
		byte[] b2 = new byte[b.length / 2];
		for (int n = 0; n < b.length; n += 2) {
			String item = new String(b, n, 2);
			b2[n / 2] = (byte) Integer.parseInt(item, 16);
		}
		return b2;
	}

	public static synchronized int parseMoney(Double money){
		BigDecimal bd1 = new BigDecimal(Double.toString(money));
	    BigDecimal bd2 = new BigDecimal(Double.toString(100));
	    return (int)Math.floor(bd1.multiply(bd2).doubleValue());
	}


	public static void main(String[] args) {

//		System.out.println(encrypt("0c3e9425-deab-4e29-aaeb-6eb380495dcf"));
//		System.out.println(encrypt("1"));
//		System.out.println(StrUtil.getUUID());
//		STRING ID = STRUTIL.GETUUID();
//		SYSTEM.OUT.PRINTLN(ID);
//		System.out.println(encrypt("1"));
//
//		System.out.println(encrypt("0c3e9425-deab-4e29-aaeb-6eb380495dcf"));

//		System.out.println(encrypt("loKERRe;/.2014525").length());
//		System.out.println(DesUtil.decrypt("9ceb441c-0648-433c-9c8f-82e074ef7662"));


		System.out.println(decrypt("752D8B8679D33C88"));



	}
}