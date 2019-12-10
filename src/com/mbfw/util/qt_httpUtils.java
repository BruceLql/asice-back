package com.mbfw.util;

import net.sf.json.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;

  /**
   * 外部调我们系统的接口  Demo
   */
public class qt_httpUtils {


	
	    public static void main (String []args){
	    	String url="http://data.topting-fintech.com/app/hjsj/v1/daiksq"; // 贷款申请详情  请求地址  http://
	    	JSONObject js_=new JSONObject ();
	    	js_.put("appkey", "");//提供的appkey 代表公司的秘钥
	    	js_.put("Name", "杨美兰");//参数
	    	js_.put("idCard", "532701199301170929");
	    	js_.put("rtype", "json");//返回类型 json  
	    	String result = PostGetJSONString(url,js_);
	    	System.out.println(result);
	    	
	    }
	    /**
	     * post请求
	     * @param url 路径，要有http://开头。
	     * @param json JSONObject对象，包含传递的参数。
	     * @return json字符串
	     * @author suj
	     */
		public static String PostGetJSONString(String url, JSONObject json) {
			CloseableHttpClient httpclient = HttpClientBuilder.create().build();
			HttpPost post = new HttpPost(url);
			post.addHeader("Content-type", "application/json;charset=UTF-8");
			String response = null;
			try {
				String str2 = json.toString();
				System.out.println("入参1：" + str2);
				String str4 = URLEncoder.encode(str2, "UTF-8");
				System.out.println("入参2：" + str4);
				StringEntity s = new StringEntity(str4);

				s.setContentEncoding("UTF-8");
				s.setContentType("application/json;charset=UTF-8");
				post.setEntity(s);
				long time_state = new Date().getTime();
				HttpResponse res = httpclient.execute(post);

				System.out.println("请求到返回耗时：" + (new Date().getTime() - time_state) / 1000d + "秒");

				if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
					long time_state111 = new Date().getTime();
					String result = EntityUtils.toString(res.getEntity());
					System.out.println("EntityUtils.toString(res.getEntity()) 耗时：" + (new Date().getTime() - time_state111) / 1000f + "秒");

					if (result == null || "".equals(result)) {
						System.out.println("\n at AppClient.PostGetJSONString() result is null or empty.");
						return null;
					}
					System.out.println("返回原始结果：" + result);

					long time_state222 = new Date().getTime();
					response = URLDecoder.decode(result, "UTF-8");

					System.out.println("URLDecoder.decode解码耗时：" + (new Date().getTime() - time_state222) + "毫秒");

				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			return response;
		}


  }
