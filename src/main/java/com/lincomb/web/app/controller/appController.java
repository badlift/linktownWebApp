package com.lincomb.web.app.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.lincomb.web.app.workflow.WorkFlowHelper;

@Controller
@RequestMapping("/*")
@Component
public class appController {
	
	@Autowired
	WorkFlowHelper workFlowHelper;
	/**
	 * 首页轮播图
	 * @param req
	 * @param pos 广告位
	 * @param disabled 是否启用
	 * @return
	 */
	@RequestMapping(value = "ad/get", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String homeAdBanner(HttpServletRequest req,@RequestParam("pos") String pos,@RequestParam("disabled") String disabled
	// @RequestHeader(value = "token") String token
	) {
		String url = req.getRequestURL().toString()+"?pos="+pos + "&disabled="+disabled;;	
		String result = workFlowHelper.getServiceByApp(url, false,"");
		System.out.println(result);
		return result;
	}
	
	/**
	 * 首页推送的消息
	 * @param req
	 * @return
	 */
	
	@RequestMapping(value = "push/staticnews", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String homePushStaticnews(HttpServletRequest req
//	 @RequestHeader(value = "token") String token
	) {
		String url = req.getRequestURL().toString();	
		String result = workFlowHelper.getServiceByApp(url,  false,"");
		System.out.println(result);
		return result;
	}
	
	
	/**
	 * 首页足迹
	 * @param req
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "footmark/homelist", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String homeSpoor(HttpServletRequest req,
			@RequestHeader(value = "AccessToken") String token
	) {
		String url = req.getRequestURL().toString();	
		String result = workFlowHelper.postServiceByApp(url,"", false,token);
		System.out.println(result);
		return result;
	}
	
	/**
	 * 发现页推荐
	 * @param req
	 * @param group
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "recommend/list", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String recommendList(HttpServletRequest req ,@RequestParam("group") String group ,@RequestParam("id") String id
//	 @RequestHeader(value = "token") String token
	) {
		String url = req.getRequestURL().toString()+"?group="+group+"&id="+id;;	
		String result = workFlowHelper.getServiceByApp(url, false,"");
		System.out.println(result);
		return result;
	}
	/**
	 * 推荐标签
	 * @param req
	 * @param group
	 * @return
	 */
	@RequestMapping(value = "recommend/label/list", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String recommendLabelList(HttpServletRequest req,@RequestParam("group") String group
//	 @RequestHeader(value = "token") String token
	) {
		String url = req.getRequestURL().toString()+"?group="+group;
		String result = workFlowHelper.getServiceByApp(url, false,"");
		System.out.println(result);
		return result;
	}
	
	@RequestMapping(value = "dict/cc/list", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String dictCcList(HttpServletRequest req,@RequestParam("codegroy") String codegroy,
	 @RequestHeader(value = "AccessToken") String token
	) {
		String url = req.getRequestURL().toString()+"?codegroy="+codegroy;	
		String query = "";
		String result = workFlowHelper.postServiceByApp(url, query, false,token);
		System.out.println(result);
		return result;
	
	}
	/**
	 * 场景详情
	 * @param req
	 * @param gid
	 * @return
	 */
	@RequestMapping(value = "scenen/detail", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String sceneDetail(HttpServletRequest req,@RequestParam("gid") String gid,
	 @RequestHeader(value = "AccessToken") String token
	) {
		String url = req.getRequestURL().toString()+"?gid="+gid;;	
		String result = workFlowHelper.getServiceByApp(url,false,token);
		System.out.println(result);
		return result;
	}
	
	/**
	 * 足迹
	 * @param req
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "footmark/list", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String spoorMark(HttpServletRequest req,@RequestParam("footdate") String footdate,
			@RequestHeader(value = "AccessToken") String token
	) {
		String url = req.getRequestURL().toString()+"?footdate="+footdate;
		String result = workFlowHelper.postServiceByApp(url, "", false,token);
		System.out.println(result);
		return result;
	}
	
	/**
	 * 活动详情
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "activity/detail", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String activityDetail(HttpServletRequest req,@RequestParam("gid") String gid,
			@RequestHeader(value = "AccessToken") String token
	) {
		String url = req.getRequestURL().toString()+"?gid="+gid;
		String result = workFlowHelper.getServiceByApp(url,  false,token);
		System.out.println(result);
		return result;
	}
	
	/**
	 * 评价
	 * @param req
	 * @param sourceID
	 * @param sourceType
	 * @return
	 */
	@RequestMapping(value = "rate/list", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String rateList(HttpServletRequest req,@RequestParam("sourceID") String sourceID,@RequestParam("sourceType") String sourceType

	) {
		String url = req.getRequestURL().toString()+"?sourceID="+sourceID+"&sourceType="+sourceType;
		
		/*JSONObject  params = new JSONObject();
		params.put("sourceID", sourceID);
		params.put("sourceType", sourceType);
		String query = params.toJSONString();*/
		String result = workFlowHelper.postServiceByApp(url, "", false,"");
		System.out.println(result);
		return result;
	}
	
	
	/**
	 * 服务详情
	 * @param req
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "skill/detail", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String serverDetail(HttpServletRequest req,@RequestParam("id") String id,
			@RequestHeader(value = "AccessToken") String token
	) {
		String url = req.getRequestURL().toString()+"?id="+id;
		
		/*JSONObject  params = new JSONObject();
		params.put("sourceID", sourceID);
		params.put("sourceType", sourceType);
		String query = params.toJSONString();*/
		String result = workFlowHelper.getServiceByApp(url, false,token);
		System.out.println(result);
		return result;
	}
	
	/**
	 * 收藏
	 * @param req
	 * @param gid
	 * @param typeid
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "favorite/collectbyGid", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String collectbyGid(HttpServletRequest req,@RequestParam("gid") String gid,@RequestParam("typeid") String typeid,
			@RequestHeader(value = "AccessToken") String token
	) {
		String url = req.getRequestURL().toString()+"?typeid="+typeid +"&gid="+gid;
		
		/*JSONObject  params = new JSONObject();
		params.put("sourceID", sourceID);
		params.put("sourceType", sourceType);
		String query = params.toJSONString();*/
		String result = workFlowHelper.postServiceByApp(url, "", false,token);
		System.out.println(result);
		return result;
	}
	
	/**
	 * 取消收藏
	 * @param req
	 * @param gid
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "favorite/cancelCollectbyGid", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String cancelCollectbyGid(HttpServletRequest req,@RequestParam("gid") String gid,
			@RequestHeader(value = "AccessToken") String token
	) {
		String url = req.getRequestURL().toString()+"?gid="+gid;
		
		/*JSONObject  params = new JSONObject();
		params.put("sourceID", sourceID);
		params.put("sourceType", sourceType);
		String query = params.toJSONString();*/
		String result = workFlowHelper.postServiceByApp(url, "", false,token);
		System.out.println(result);
		return result;
	}
	
	/**
	 * 活动状态
	 * @param req
	 * @param gid
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "activity/detail/status", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String activityDetailStatus(HttpServletRequest req,@RequestParam("gid") String gid,
			@RequestHeader(value = "AccessToken") String token
	) {
		String url = req.getRequestURL().toString()+"?gid="+gid;
		String result = workFlowHelper.postServiceByApp(url, "", false,token);
		System.out.println(result);
		return result;
	}
	
	/**
	 * 获取某场景下的所有商品列表
	 * @param req
	 * @param gid 场景GID
	 * @param startpage 起始页
	 * @param size
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "scenen/byScenarionId", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String scenenByScenarionId(HttpServletRequest req,
			@RequestParam("gid") String gid,
			@RequestParam("startpage") String startpage,
			@RequestParam("size") String size,
			@RequestHeader(value = "AccessToken") String token
	) {
		String url = req.getRequestURL().toString()+"?gid="+gid+"&startpage="+startpage+"&size="+size;
		String result = workFlowHelper.getServiceByApp(url, false,token);
		System.out.println(result);
		return result;
	}
	
	/**
	 * 获取场景列表通过code
	 * @param req
	 * @param code 分类code
	 * @param kws 关键词
	 * @param desc 是否排序
	 * @param token token
	 * @return
	 */
	@RequestMapping(value = "scenen/foundScenceByCode", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String foundScenceByCode(HttpServletRequest req,
			@RequestParam("code") String code,
			@RequestParam("kws") String kws, 
			@RequestParam("desc") boolean desc,
			@RequestHeader(value = "AccessToken") String token
	) {
		String url = req.getRequestURL().toString()+"?code="+code+"&kws="+kws+"&desc="+desc;
		String result = workFlowHelper.getServiceByApp(url, false,token);
		System.out.println(result);
		return result;
	}
	
	
	/**
	 * 场景店铺商品详情
	 * @param req
	 * @param commoditygid
	 * @param scenariongid
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "scenen/getCommodityByid", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String scenarionCommodityDetail(HttpServletRequest req,
			@RequestParam("commoditygid") String commoditygid,
			@RequestParam("scenariongid") String scenariongid, 
			@RequestHeader(value = "AccessToken") String token
	) {
		String url = req.getRequestURL().toString()+"?commoditygid="+commoditygid+"&scenariongid="+scenariongid;
		String result = workFlowHelper.getServiceByApp(url, false,token);
		System.out.println(result);
		return result;
	}
	
	
	/**
	 * 添加购物车
	 * @param req
	 * @param userGid
	 * @param productGid
	 * @param count
	 * @param siginprice
	 * @param totalprice
	 * @param imgUrl
	 * @param address
	 * @param productName
	 * @param shopName
	 * @param tel
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "shopcart/add", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String shopcarAdd(HttpServletRequest req,
			@RequestParam("userGid") String userGid,
			@RequestParam("productGid") String productGid, 
			@RequestParam("count") String count,
			@RequestParam("siginprice") String siginprice, 
			@RequestParam("totalprice") String totalprice,
			@RequestParam("imgUrl") String imgUrl, 
			@RequestParam("address") String address,
			@RequestParam("productName") String productName, 
			@RequestParam("shopName") String shopName, 
			@RequestParam("tel") String tel, 
			@RequestParam("shopGid") String shopGid, 
			@RequestHeader(value = "AccessToken") String token
	) {
		String url = req.getRequestURL().toString();
		JSONObject  params = new JSONObject();
		params.put("userGid", userGid);
		params.put("productGid", productGid);
		params.put("count", count);
		params.put("siginprice", siginprice);
		params.put("totalprice", totalprice);
		params.put("imgUrl", imgUrl);
		params.put("address", address);
		params.put("productName", productName);
		params.put("shopName", shopName);
		params.put("tel", tel);
		params.put("shopGid", shopGid);
		String query = params.toJSONString();
		String result = workFlowHelper.postServiceByApp(url,query, false,token);
		System.out.println(result);
		return result;
	}
	
	/**
	 * 获取购物车总数
	 * @param req
	 * @param userGid
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "shopcart/totalcount", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String shopcartTotalcount(HttpServletRequest req,
			@RequestParam("userGid") String userGid,
			@RequestHeader(value = "AccessToken") String token
	) {
		String url = req.getRequestURL().toString()+"?userGid="+userGid;
		String result = workFlowHelper.getServiceByApp(url, false,token);
		System.out.println(result);
		return result;
	}
	

}
