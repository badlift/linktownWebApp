package com.lincomb.web.app.workflow;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.HashMap;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONException;

public class HttpRequestSupport {
	 String lbpHost;
	 String lbpPort;
	 String lbpProtocol;
	 String lbpServletContext;
	 String selfServletContext;
	 HttpPost createPost(final String urlAllInOne){

		HttpPost httpPost = new HttpPost(urlAllInOne);
		
		return httpPost;
	} 
	
	 private HttpGet createGet(String urlAllInOne) {
		HttpGet httpGet = new HttpGet(urlAllInOne);
		return httpGet;
	} 
	 
	 /**
	  * get
	  * @param url
	  * @param cid
	  * @param type 请求类型
	  * @return
	  */
	 
	protected String makeCallGet(String urlAllInOne,boolean cid,String Token){
		HttpGet httpGet = createGet(urlAllInOne);
	
		CloseableHttpResponse response = null;
		if(!Token.isEmpty() && !Token.equals("")){
			httpGet.addHeader("token",Token);
		}
		if(cid){
			httpGet.addHeader("cid","xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
		}
		// JSONObject js = null;
		try {
			CloseableHttpClient httpClient = HttpClients.custom().build();
			// post.setEntity(new UrlEncodedFormEntity(nvps));
			response = httpClient.execute(httpGet);

			System.out.println(response.getStatusLine());
			HttpEntity entity = response.getEntity();

			// EntityUtils.consume(entity);
			String strResult = EntityUtils.toString(entity);
			return strResult;

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				response.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * post
	 * @param urlAllInOne
	 * @param cid
	 * @param params
	 * @return
	 */
	protected String postServiceByApp(String url,boolean cid,String params,String Token){
		MultipartEntityBuilder builder = MultipartEntityBuilder.create();
		builder.addBinaryBody("reqJsonStr", params.getBytes());
		HttpPost httpPost = new HttpPost(url);
		CloseableHttpResponse response = null;
		if(!Token.isEmpty() && !Token.equals("")){
			httpPost.addHeader("token",Token);
		}
		if(cid){
			httpPost.addHeader("cid","xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
		}
		builder.setCharset(Charset.forName("UTF-8"));
		httpPost.setEntity(builder.build());
		// JSONObject js = null;
		try {
			CloseableHttpClient httpClient = HttpClients.custom().build();
			// post.setEntity(new UrlEncodedFormEntity(nvps));
			response = httpClient.execute(httpPost);

			System.out.println(response.getStatusLine());
			HttpEntity entity = response.getEntity();

			// EntityUtils.consume(entity);
			String strResult = EntityUtils.toString(entity);
			return  strResult;

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				response.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	
}
