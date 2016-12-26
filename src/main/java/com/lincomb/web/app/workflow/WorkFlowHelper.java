package com.lincomb.web.app.workflow;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.lincomb.web.app.utils.PropertiesUtil;

@Service
public class WorkFlowHelper extends HttpRequestSupport implements WorkFlowService{
	
	public WorkFlowHelper(){
		
		lbpHost = PropertiesUtil.getProperties().getProperty("restful_ws_host");
		lbpPort = PropertiesUtil.getProperties().getProperty("restful_ws_port");
		lbpProtocol = PropertiesUtil.getProperties().getProperty("restful_ws_protocol");
		lbpServletContext = PropertiesUtil.getProperties().getProperty("restful_ws_servlet_context");
		selfServletContext  = PropertiesUtil.getProperties().getProperty("self_servlet_context");
	}
	

	@Override
	public String getServiceByApp(String url, boolean hasCid,String token) {
		// TODO Auto-generated method stub
		String urlArr[] = url.split("/" + selfServletContext + "/");
		//String lbpUrl = "/scenarion/" + tokens[1];
		String sendUrl = lbpProtocol + "://" + lbpHost + ":" + lbpPort + "/" + lbpServletContext +"/" + urlArr[1];
		System.out.println(sendUrl);
		return makeCallGet(sendUrl,hasCid,token);
	}
	
	@Override
	public String postServiceByApp(String url, String params, boolean hasCid,String token) {
		// TODO Auto-generated method stub
		String urlArr[] = url.split("/" + selfServletContext + "/");
	
		//String lbpUrl = "/scenarion/" + tokens[1];
		String sendUrl = lbpProtocol + "://" + lbpHost + ":" + lbpPort + "/" + lbpServletContext + "/" + urlArr[1]; 
		System.out.println(sendUrl);
		return postServiceByApp(sendUrl,hasCid,params,token);
		
	}
	

	
}
