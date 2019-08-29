package cn.lz.hospital.utils;



/**
 * 通用工具类
 * 
 * @author liufeng
 * @date 2013-10-17
 */
public class CommonUtil {
	
	/**
	 * 根据内容类型判断文件扩展名
	 * 
	 * @param contentType 内容类型
	 * @return
	 */
	public static String getFileExt(String contentType) {
		String fileExt = "";
		if ("image/jpeg".equals(contentType))
			fileExt = ".jpg";
		else if ("image/jpg".equals(contentType))
			fileExt = ".jpg";
		else if ("image/bmp".equals(contentType))
			fileExt = ".bmp";
		else if ("image/png".equals(contentType))
			fileExt = ".png";
		else if ("image/gif".equals(contentType))
			fileExt = ".gif";
		return fileExt;
	}
	

}