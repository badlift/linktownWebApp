package com.lincomb.web.app.utils;

import java.util.Iterator;
import java.util.Map.Entry;

import net.sf.json.JSONObject;


public class UrlUtils {
	public static String toGetURL(JSONObject params){
		String urlQuery = "";
		Iterator<Entry<String, Object>> it = params.entrySet().iterator();
		  while (it.hasNext()) {
		   Entry<String, Object> entry = it.next();
		   String value = entry.getValue()==null?"": entry.getValue().toString();
		   urlQuery += entry.getKey()+"="+value+"&";
		  }
		  urlQuery = urlQuery.substring(0,urlQuery.length()-1);
		return urlQuery;
	}
}
