package cn.lz.hospital.utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

public class HttpUtil {

	// 请求服务器端的url
	private static String PATH = "http://localhost/weixin/playReception";
	private static URL url;

	public HttpUtil() {
	}

	static {
		try {
			url = new URL(PATH);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 发送POST请求
	 * 
	 * @param url
	 *            目的地址
	 * @param parameters
	 *            请求参数，Map类型。
	 * @return 远程响应结果
	 */
	public static String sendPost(String url, Map<String, Object> parameters) {
		String result = "";// 返回的结果
		BufferedReader in = null;// 读取响应输入流
		PrintWriter out = null;
		StringBuffer sb = new StringBuffer();// 处理请求参数
		String params = "";// 编码之后的参数
		try {
			// 编码请求参数
			if(parameters!=null) {
				if (parameters.size() == 1) {
					for (String name : parameters.keySet()) {
						sb.append(name).append("=").append(URLEncoder.encode(parameters.get(name).toString(), "UTF-8"));
					}
					params = sb.toString();
				} else {
					for (String name : parameters.keySet()) {
						sb.append(name).append("=").append(URLEncoder.encode(parameters.get(name).toString(), "UTF-8")).append("&");
					}
					String temp_params = sb.toString();
					params = temp_params.substring(0, temp_params.length() - 1);
				}
			}
			// 创建URL对象
			URL connURL = new URL(url);
			// 打开URL连接
			HttpURLConnection httpConn = (HttpURLConnection) connURL.openConnection();
			// 设置通用属性
			httpConn.setRequestProperty("Accept", "*/*");
			httpConn.setRequestProperty("Connection", "Keep-Alive");
			httpConn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1)");
			// 设置POST方式
			httpConn.setDoInput(true);
			httpConn.setDoOutput(true);
			// 获取HttpURLConnection对象对应的输出流
			out = new PrintWriter(httpConn.getOutputStream());
			// 发送请求参数
			out.write(params);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应，设置编码方式
			in = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), "UTF-8"));
			String line;
			// 读取返回的内容
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * @param params
	 *            填写的url的参数
	 * @param encode
	 *            字节编码
	 * @return
	 */
	public static String sendPostMessage(Map<String, String> params,
			String encode) {
		// 作为StringBuffer初始化的字符串
		StringBuffer buffer = new StringBuffer();
		try {
			if (params != null && !params.isEmpty()) {
				for (Map.Entry<String, String> entry : params.entrySet()) {
					// 完成转码操作
					buffer.append(entry.getKey())
							.append("=")
							.append(URLEncoder.encode(entry.getValue(), encode))
							.append("&");
				}
				buffer.deleteCharAt(buffer.length() - 1);
			}
			// System.out.println(buffer.toString());
			// 删除掉最有一个&

			System.out.println("-->>" + buffer.toString());
			HttpURLConnection urlConnection = (HttpURLConnection) url
					.openConnection();
			urlConnection.setConnectTimeout(3000);
			urlConnection.setRequestMethod("POST");
			urlConnection.setDoInput(true);// 表示从服务器获取数据
			urlConnection.setDoOutput(true);// 表示向服务器写数据
			// 获得上传信息的字节大小以及长度
			byte[] mydata = buffer.toString().getBytes();
			// 表示设置请求体的类型是文本类型
			urlConnection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			urlConnection.setRequestProperty("Content-Length",
					String.valueOf(mydata.length));
			// 获得输出流,向服务器输出数据
			OutputStream outputStream = urlConnection.getOutputStream();
			outputStream.write(mydata, 0, mydata.length);
			outputStream.close();
			// 获得服务器响应的结果和状态码
			int responseCode = urlConnection.getResponseCode();
			if (responseCode == 200) {
				return changeInputStream(urlConnection.getInputStream(), encode);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}

	
	/**
	 * @param params
	 *            填写的url的参数
	 * @param encode
	 *            字节编码
	 * @return
	 */
	public static String sendPostMessage(String path,String content, String encode) {
		try {
			String string = null;
			if (content != null) {
				string = content;
			}
			URL url = new URL(path);
			HttpURLConnection urlConnection = (HttpURLConnection) url
					.openConnection();
			urlConnection.setConnectTimeout(3000);
			urlConnection.setRequestMethod("POST");
			urlConnection.setRequestProperty("Content-type", "application/xml");
			urlConnection.setRequestProperty("Accept-Charset", "utf-8");
			urlConnection.setRequestProperty("contentType", "utf-8");
			urlConnection.setDoInput(true);// 表示从服务器获取数据
			urlConnection.setDoOutput(true);// 表示向服务器写数据
			// 获得上传信息的字节大小以及长度
			byte[] mydata = string.getBytes("UTF-8");
			// 表示设置请求体的类型是文本类型
			urlConnection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			urlConnection.setRequestProperty("Content-Length",
					String.valueOf(mydata.length));
			// 获得输出流,向服务器输出数据
			OutputStream outputStream = urlConnection.getOutputStream();
			outputStream.write(mydata, 0, mydata.length);
			// outputStream.close();
			// 获得服务器响应的结果和状态码
			int responseCode = urlConnection.getResponseCode();
			if (responseCode == 200) {
				return changeInputStream(urlConnection.getInputStream(), encode);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

		}
		return "";
	}
	
	/**
	 * @param path
	 *            url地址
	 * @param encode
	 *            字节编码
	 * @return
	 */
	public static String sendGetMessage(String path, String encode) {
		try {
			URL url = new URL(path);
			HttpURLConnection urlConnection = (HttpURLConnection) url
					.openConnection();
			urlConnection.setConnectTimeout(30000);
			urlConnection.setRequestMethod("GET");
			urlConnection.setDoInput(true);// 表示从服务器获取数据
			// 获得服务器响应的结果和状态码
			int responseCode = urlConnection.getResponseCode();
			if (responseCode == 200) {
				return changeInputStream(urlConnection.getInputStream(), encode);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

		}
		return "";
	}

	/**
	 * 将一个输入流转换成指定编码的字符串
	 * 
	 * @param inputStream
	 * @param encode
	 * @return
	 */
	public static String changeInputStream(InputStream inputStream,
			String encode) {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		byte[] data = new byte[1024];
		int len = 0;
		String result = "";
		if (inputStream != null) {
			try {
				while ((len = inputStream.read(data)) != -1) {
					outputStream.write(data, 0, len);
				}
				result = new String(outputStream.toByteArray(), encode);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 发送HTTP请求取得返回的输入流
	 * 
	 * @param requestUrl
	 *            请求地址
	 * @return InputStream
	 */
	public static InputStream sendGetRequest(String requestUrl) {
		InputStream inputStream = null;
		try {
			URL url = new URL(requestUrl);
			HttpURLConnection httpUrlConn = (HttpURLConnection) url
					.openConnection();
			httpUrlConn.setDoInput(true);
			httpUrlConn.setRequestMethod("GET");
			httpUrlConn.connect();
			// 获得返回的输入流
			inputStream = httpUrlConn.getInputStream();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return inputStream;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// String result =
		// HttpUtils.sendGetMessage("http://localhost/weixin/CoreServlet",
		// "utf-8");
		// System.out.println(result);
	}

}
