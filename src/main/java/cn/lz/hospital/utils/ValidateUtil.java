package cn.lz.hospital.utils;

import jodd.util.StringUtil;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
* @Description:    验证工具
* @Author:         zhuwc
* @CreateDate:     2018/12/7 18:39
* @Version:        1.0
*/
public class ValidateUtil {
	private static SimpleDateFormat hsdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	private static SimpleDateFormat ssdf = new SimpleDateFormat("yyyy-MM-dd");
	private static String[] images = new String[] { "image", "png", "jpg", "jpeg", "pjpeg", "x-png" };

	public static boolean isInteger(String value) {
		try {
			Integer.parseInt(value);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public static int[] isIntegerArray(String value) {
		String[] cs = value.split(";");
		int[] cids = new int[cs.length];
		for (int i = 0; i < cs.length; i++) {
			try {
				cids[i] = Integer.parseInt(cs[i]);
			} catch (NumberFormatException e) {
				return null;
			}
		}
		return cids;
	}

	public static int[] isIntegerArray(String[] value) {
		int[] cids = new int[value.length];
		for (int i = 0; i < value.length; i++) {
			try {
				cids[i] = Integer.parseInt(value[i]);
			} catch (NumberFormatException e) {
				return null;
			}
		}
		return cids;
	}

	public static Calendar validateCalendar(String value) {
		try {
			Calendar c = Calendar.getInstance();
			c.setTime(ssdf.parse(value));
			return c;
		} catch (NumberFormatException e) {
			return null;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Calendar validateCalendarWithHour(String value) {
		try {
			Calendar c = Calendar.getInstance();
			c.setTime(hsdf.parse(value));
			return c;
		} catch (NumberFormatException e) {
			return null;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static boolean isBoolean(String value) {
		try {
			Boolean.parseBoolean(value);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	/**
	 * 审查是不是图片
	 */
	public static boolean validateImage(String imageName) {
		for (String s : images) {
			if (s.equals(imageName)) {
				return true;
			}
		}
		return false;
	}

	// 得到前几天，后几天（月，年）的时间
	// 年月日标记,正数前几天,负数后几天
	public static Timestamp getBeforeTime(int nyr, int nyrNum) {
		long newlong = System.currentTimeMillis();
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTimeInMillis(newlong);

		calendar.add(nyr, nyrNum);
		Timestamp time = new Timestamp(calendar.getTimeInMillis());

		// System.out.println(UtilBean.getBeforeTime(Calendar.DAY_OF_MONTH,
		// 100));
		// calendar.add(Calendar.MONTH, 1)

		return time;
	}

	public static boolean isFloat(String value) {
		try {
			Float.parseFloat(value);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public static boolean isDouble(String value) {
		try {
			Double.parseDouble(value);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public static String getSixIntStr() {
		Random r = new Random();
		int sint = r.nextInt(1000000);
		DecimalFormat dfInt = new DecimalFormat("000000");
		String str = dfInt.format(sint);
		return str;
	}

	public static boolean regex(String regex, String value) {
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(value);
		return m.find();
	}

	public static boolean checkMobile(String value) {
		// 手机号码
		String regex = "\\d{11}"; // 11位
		return regex(regex, value);
	}

	public static boolean isMobileNO(String mobiles) {
//		Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$");
		Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
		Matcher m = p.matcher(mobiles);
		return m.matches();
	}
	
	public static boolean checkUser2(String value) {
		String regex = "[a-zA-Z1-9_]{5,20}";
		return regex(regex, value);
	}

	public static boolean checkUser(String value) {
		String regex = "\\w{5,20}";
		return regex(regex, value);
	}

	public static boolean checkEmail(String value) {
		String regex = "[\\w\\.\\-]+@([\\w\\-]+\\.)+[\\w\\-]+";
		return regex(regex, value);
	}

	public static boolean checkPsw(String value) {
		String regex = "\\w{6}";
		return regex(regex, value);
	}

	public static boolean checkStr(String content, String filter) {
		// 要过虑的字符,用|分隔
		Matcher m = Pattern.compile(filter).matcher(content);
		if (m.find()) {
			return true; // 有非法字符
		} else {
			return false;
		}
	}

	public static boolean checkPostNo(String value) {
		String regex = "[0-9]{6}";
		return regex(regex, value);
	}

	public static boolean checkCertificate(String value) {
		String regex = "^[0-9]{15}$|^[0-9]{18}$";
		return regex(regex, value);
	}

	public static boolean checkTelno(String value) {
		if (StringUtil.isNotBlank(value)) {
			// 电话
			String regex = "^[0-9]{6,20}$";
			return regex(regex, value);
		} else {
			return false;
		}

	}

	public static boolean checkAddress(String value) {
		// 地址
		// String regex = "^\\w{1,100}$";
		// return regex(regex,value);

		if (value.length() < 100 && value.length() > 0)
			return true;
		return false;
	}

	public static boolean checkPerson(String value) {
		// String regex = "^\\w{1,20}$";
		// return regex(regex,value);
		if (value.length() < 20 && value.length() > 0)
			return true;
		return false;
	}

	public static boolean isFloat2(String value) // 价格不超过10位数字(可含两位小数)
	{
		String regex = "^[0-9]{1,10}$|^[0-9]{1,9}[.][0-9]{1}$|^[0-9]{1,8}[.][0-9]{1,2}$";
		return regex(regex, value);
	}

	public static boolean isFloat7(String value) // 价格不超过7位数字(可含两位小数)
	{
		String regex = "^[0-9]{1,7}$|^[0-9]{1,6}[.][0-9]{1}$|^[0-9]{1,5}[.][0-9]{1,2}$";
		return regex(regex, value);
	}

	public static boolean String20(String value) {
		// String regex = "^\\w{1,20}$";
		// return regex(regex,value);
		if (value.length() < 20 && value.length() > 0)
			return true;
		return false;
	}

	public static boolean StringLength(String value, int minLength, int maxLength) {

		if (StringUtil.isNotBlank(value) && value.length() <= maxLength && value.length() >= minLength)
			return true;
		return false;
	}

	public static boolean String7int(String value) {
		// String regex = "^\\w{1,20}$";
		// return regex(regex,value);
		if (value.length() < 7 && value.length() > 0 && isInteger(value))
			return true;
		return false;
	}

	public static boolean String500(String value) {
		if (value.length() < 500 && value.length() > 0)
			return true;
		return false;
	}

	public static boolean String30(String value) {
		if (value.length() < 30 && value.length() > 0)
			return true;
		return false;
	}

	public static boolean checkNum(String value) {
		String regex = "^[0-9]{1,4}$";
		return regex(regex, value);
	}

	public static boolean checkUid(String value) {
		String regex = "^[0-9]{7,10}$";
		return regex(regex, value);
	}

	public static boolean checkskren(String value) {
		if (value.length() < 25 && value.length() > 0)
			return true;
		return false;
	}

	public static boolean checkNum(String value, int rangeStart, int rangeEnd) {
		if (isInteger(value)) {
			int v = Integer.valueOf(value);
			if (v >= rangeStart && v <= rangeEnd)
				return true;
			return false;
		}
		return false;
	}

	public static boolean checkCourseNum(String value) {
		String regex = "^[0-9]{10}$";
		return regex(regex, value);
	}

	public static boolean checkNumberAndLetter(String value) {
		String regex = "^[A-Za-z0-9]+$";
		return regex(regex, value);
	}

	public static boolean checkChiness(String value) {
		String regex = "^[\u4e00-\u9fa5-a-zA-Z0-9]+$";
		return regex(regex, value);
	}

	public static <T> boolean checkListIsNotEmpty(List<T> list) {
		if (list != null && !list.isEmpty()) {
			return true;
		}
		return false;
	}



	public static boolean isnullStr(String str) {
		if ((str != null && str.trim().length() > 0) && (!"null".equalsIgnoreCase(str))) {
			return false;
		}
		return true;
	}

	/**
	 * 验证所有输入字符都不为空
	 *
	 * @param args
	 *            输入字符
	 * @return 都不为空返回true，否则返回false
	 */
	public static boolean isAllNotBlank(String... args) {
		for (String string : args) {
			if (isnullStr(string)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 验证指定长度字符串是否全为数字组成
	 *
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str, int length) {
		String regex = "^[0-9]{" + length + "}$";
		regex(regex, str);
		return regex(regex, str);
	}

	public static void main(String[] args) {

		System.out.println(Boolean.valueOf("true"));
	}

	public static boolean checkMapIsNotEmpty(Map errMap) {
		if (errMap!=null&&!errMap.isEmpty()) {
			return true;
		}
		return false;
	}
	
	/**
	 * 判断是否为空
	 * @param obj
	 * @return
	 */
	public static boolean isEmpty(Object obj) {
		return null == obj || "null".equalsIgnoreCase(obj.toString()) || obj.toString().length() == 0 || "".equals(obj.toString().trim());
	}
	
	/**
	 * 判断不为空
	 * @param obj
	 * @return
	 */
	public static boolean isNotEmpty(Object obj){
		return !isEmpty(obj);
	}

	/**
	 * 验证是否是UUID格式的字符串
	 *
	 * @param str
	 * @return <b>true</b> if {@code str} is {@link UUID} or <b>false</b> if not
	 */
	public static boolean checkStrIsUUID(String str) {
		try {
			UUID.fromString(str);
			return true;
		} catch (IllegalArgumentException e) {
			return false;
		}
	}

	/**
	 * 分页
	 *
	 * @param amount
	 * @param max
	 * @return
	 */
	public static Integer getPage(Integer amount, Integer max) {
		Integer count = (amount + max) / max;
		return count;
	}

	/**
	 * URL编码（utf-8）
	 *
	 * @param source
	 * @return
	 */
	public static String urlEncodeUTF8(String source) {
		String result = source;
		try {
			result = java.net.URLEncoder.encode(source, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}
}
