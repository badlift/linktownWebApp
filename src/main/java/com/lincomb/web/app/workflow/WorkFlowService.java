package com.lincomb.web.app.workflow;


public interface WorkFlowService {
	public String getServiceByApp(final String url, boolean hasCid,String token);

	public String postServiceByApp(String url, String params, boolean hasCid,String token);
}
