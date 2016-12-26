package com.lincomb.web.app.servlet;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.lincomb.web.app.utils.PropertiesUtil;
/**
 * @author JimmyLee
 *
 * @createDate 2016年7月29日
 */
@MultipartConfig
public class WorkFlowServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6034914077638967638L;
	private String lbpHost;
	private String lbpPort;
	private String lbpProtocol;
	private String lbpServletContext;
	private String selfServletContext;

	public void init() throws ServletException {
		super.init();
		lbpHost = PropertiesUtil.getProperties().getProperty("restful_ws_host");
		lbpPort = PropertiesUtil.getProperties().getProperty("restful_ws_port");
		lbpProtocol = PropertiesUtil.getProperties().getProperty("restful_ws_protocol");
		lbpServletContext = PropertiesUtil.getProperties().getProperty("restful_ws_servlet_context");
		selfServletContext = PropertiesUtil.getProperties().getProperty("self_servlet_context");
	}

	@SuppressWarnings("unchecked")

	public void doPost(HttpServletRequest req, HttpServletResponse resp) {
		String url = req.getRequestURL().toString();
		if("login.jsp".equals(url)){
			try {
				super.doPost(req, resp);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (checkLegal(url)) {
			String query = "";
			
			query = req.getQueryString();
			/*if(query!=null && !query.equals("")){
				try {
					query = URLDecoder.decode(req.getQueryString(), "UTF-8");
				} catch (UnsupportedEncodingException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} 
			}*/
			
			String token[] = url.split("/" + selfServletContext + "/app/");
			if(token.length<2){
				token = url.split("/" + selfServletContext + "/share/app/");
			}
			// String lbpUrl = "/scenarion/" + token[1];
//			InputStream is = null;
			String res = null;
			MultipartEntityBuilder builder = MultipartEntityBuilder.create();
			try {
				String contentType = req.getContentType();
				InputStream in = null;
				if(contentType.indexOf("multipart/form-data") != -1){
					Collection<Part> parts = req.getParts();
					Iterator it = parts.iterator();
					JSONObject json1 = new JSONObject();
					while (it.hasNext()) {
						Part parttemp = (Part) it.next();
						String paramsName = parttemp.getName();
	
						if (paramsName.equals("file")) {
							Part part = req.getPart("file");
							String  header = part.getHeader("Content-Disposition");
							try {
							   if (!(new File("D:/LDupload/").isDirectory())) {
							    new File("D:/LDupload/").mkdir();
							   }
							} catch (SecurityException e) {
							   e.printStackTrace();
							}
					        String fileName = "d:\\\\LDupload\\" + header.substring(header.indexOf("filename=\"") + 10,header.lastIndexOf("\""));
					        FileOutputStream image = new FileOutputStream(fileName , true);
							in = parttemp.getInputStream();
							
							byte[] b = new byte[1024];
							int rc = 0;
							while ((rc = in.read(b)) > 0) {
								image.write(b, 0, rc);
							}
							image.close();
							File file = new File(fileName);
							builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
							FileBody fileBody = new FileBody(file);
							System.out.println(" filebody is " + fileBody.getContentLength() + "\n\n\n");
							builder.addPart("file", fileBody);
						}else{
							in = parttemp.getInputStream();
							String vaule = IOUtils.toString(in, "UTF-8");
							json1.put(paramsName, vaule);
							res = json1.toString();
						}
					}
					System.out.println(res);
					builder.addBinaryBody("reqJsonStr", res.getBytes());
				}else {
					in = req.getInputStream();
					res = IOUtils.toString(in, "UTF-8");
					System.out.println(res);
					
					builder.addBinaryBody("reqJsonStr", res.getBytes());
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String getMethodQuery = "";
			if (!StringUtils.isEmpty(query)) {
				getMethodQuery = "?" + query;
			}
			
			/**
			 * 内网配置
			 */
		   HttpPost httpPost = new HttpPost(lbpProtocol + "://" + lbpHost + ":" + lbpPort + "/" + lbpServletContext
					+ "/" + token[1] + getMethodQuery);
			/**
			 * 外网配置
			 */
//			HttpPost httpPost = new HttpPost(lbpProtocol + "://" + lbpHost +  "/" + lbpServletContext
//					+ "/" + token[1] + getMethodQuery);
			
			System.out.println(res);
			String cid = req.getParameter("cid");
			String userToken = (String) req.getSession().getAttribute("token");
			if (!StringUtils.isEmpty(userToken)) {
				httpPost.addHeader("token",userToken);
			}
			else{
				CloseableHttpResponse response = null;
				try {
					CloseableHttpClient httpClient = HttpClients.custom().build();
					response = httpClient.execute(httpPost);
					String aa= "{\"status\":\"1101\",\"repMsg\":\"操作成功\"}";
							
					resp.getOutputStream().write(aa.getBytes("utf-8"));
//					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					try {
						response.close();
						return ;
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			if (!StringUtils.isEmpty(cid)) {
				httpPost.addHeader("cid", "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
			}
			// httpPost.set
			builder.setCharset(Charset.forName("UTF-8"));
			httpPost.setEntity(builder.build());
			System.out.println(httpPost);
			
			// HttpPost post = createHttpPost();
			CloseableHttpResponse response = null;
			// JSONObject js = null;
			try {
				CloseableHttpClient httpClient = HttpClients.custom().build();
				// httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));

				response = httpClient.execute(httpPost);

				System.out.println(response.getStatusLine());
				HttpEntity entity = response.getEntity();

				

				// EntityUtils.consume(entity);
				String strResult = EntityUtils.toString(entity);
				System.out.println(strResult);
				// strResult = new String(strResult.getBytes("utf-8"));
				// resp.getOutputStream().write(new
				// String(strResult.getBytes("utf-8")).getBytes("gbk"));
				resp.getOutputStream().write(strResult.getBytes("utf-8"));
				// strResult = new
				// String(ServerSecurityKeeper.Decrypt(strResult,
				// AppCommonServerManager.TEA_PASS));
				// System.out.println(" entity " + strResult);
				// if(returnClass == null)
				// return strResult;
				// js = new JSONObject(strResult);
				//
				// result = gson.fromJson(js.getString("returnListObject"),
				// clazz);

				// js.getString("returnListObject");

				// result =
				// gson.fromJson(js.getString("returnListObject"),returnClass);
				
				File f = new File("D:/LDupload/");
				delPath(f);

			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
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
		} else {
		}
	}


	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		doPost(request, response);
	}

	private boolean checkLegal(String url) {
		// check white list
		return true;
	}

	
	/**
	 * 删除文件
	 * @param f
	 * @return
	 */

	
	private String delPath(File f){
		String success = "文件夹中没有文件";
		if (f == null){
			success = "没有文件夹";
		}else if(f.listFiles() != null){
			File[] fs = f.listFiles();
			for(File ff : fs){
				File fff[] = ff.listFiles();
				if(fff != null && fff.length > 0){
					delPath(ff);
				}
				ff.delete();
			}
			success = "文件删除成功";
		}
		return success;
	};
	
	 
    public String getJSONByUrl(String urls) {  
        String json = null;  
        StringBuffer sb = new StringBuffer();  
        try {  
            URL url = new URL(urls);  
            InputStreamReader isr = new InputStreamReader(url.openStream());  
            char[] buffer = new char[1024];  
            int len = 0;  
            while((len=isr.read(buffer))!=-1){  
                sb.append(buffer,0,len);  
            }  
              
        } catch (MalformedURLException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return sb.toString();  
    }  

}
