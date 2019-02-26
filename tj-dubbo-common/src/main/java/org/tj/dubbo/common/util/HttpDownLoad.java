package org.tj.dubbo.common.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.tj.dubbo.common.exception.DubboException;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * 文件下载工具类
 * 
 * @ClassName: HttpDownLoad
 * @Description: TODO
 * @author: 唐靖
 * @date: 2017年7月24日 下午3:32:10
 */
@SuppressWarnings("deprecation")
public class HttpDownLoad {
	private static DefaultHttpClient httpClient = new DefaultHttpClient();

	public static void downLoad(String url, String dst) throws Exception {
		OutputStream out = null;
		InputStream in = null;
		HttpGet httpGet = new HttpGet(url);
		HttpResponse httpResponse = httpClient.execute(httpGet);
		HttpEntity entity = httpResponse.getEntity();
		in = entity.getContent();
		long length = entity.getContentLength();
		if (length <= 0) {
			throw new DubboException("下载文件不存在");
		}
		out = new FileOutputStream(new File(dst));
		saveTo(in, out);
	}

	private static void saveTo(InputStream in, OutputStream out) throws Exception {
		byte[] data = new byte[1024 * 1024];
		int index = 0;
		while ((index = in.read(data)) != -1) {
			out.write(data, 0, index);
		}
		in.close();
		out.close();
	}

	/**
	 * 发送post请求
	 * 
	 * @Title: sendPost
	 * @Description: TODO
	 * @author: 唐靖
	 * @return: void
	 */
	public static void sendPost(String url, Map<String, Object> map) {
		OkHttpClient okHttpClient = new OkHttpClient();
		FormBody.Builder builder = new FormBody.Builder();
		if (null != map && map.keySet().size() > 0) {
			for (String key : map.keySet()) {
				builder.add(key, map.get(key) + "");
			}
		}
		RequestBody requestBodyPost = builder.build();
		Request request = new Request.Builder().url(url.toString()).post(requestBodyPost).build();
		Call call = okHttpClient.newCall(request);
		try {
			call.execute();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void downLoad(String url, String amrPath, CountDownLatch count) throws Exception {
		downLoad(url, amrPath);
		count.countDown();
	}
}
