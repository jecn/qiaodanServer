package org.safari.sport.main.controller;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.safari.pub.platform.aop.annotation.SafariMethod;
import org.safari.pub.platform.global.Constants;
import org.safari.pub.platform.global.RespCode;
import org.safari.pub.platform.protocol.ReqProtocol;
import org.safari.pub.platform.protocol.SafariResp;
import org.safari.pub.platform.validator.JSONValidator;
import org.safari.pub.platform.web.entity.ReqDomain;
import org.safari.pub.utils.TokenUtil;
import org.safari.pub.utils.UUIDUtil;
import org.safari.pub.utils.page.PageUtil;
import org.safari.sport.main.entity.Court;
import org.safari.sport.main.protocol.CourtPro.CourtDetail;
import org.safari.sport.main.protocol.CourtPro.CourtFind;
import org.safari.sport.main.protocol.CourtPro.CourtUpload;
import org.safari.sport.main.service.CourtService;
import org.safari.user.main.entity.Vip;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

@Controller
@RequestMapping("v0/sport/court")
public class CourtController {

	protected Logger LOG = LoggerFactory.getLogger(CourtController.class);
	
	@Autowired
	private CourtService courtService;
	
	@ResponseBody
	@RequestMapping("upload")
	@SafariMethod(checkToken=true, desc="上传球场信息",submit="POST")
	public Object upload(ReqProtocol reqProtocol) {

		String request = null;
		JSONObject reqJson = null;
		ReqDomain reqDomain = null;
		SafariResp<?> safariResp = null;
		try {
			request = reqProtocol.getInfoJson();
			reqJson = JSONObject.fromObject(request);
			reqDomain = (ReqDomain) JSONObject.toBean(reqJson, ReqDomain.class);
		} catch (Exception e) {
			safariResp = SafariResp.getInstance(RespCode.$1001, RespCode
					.getRspCode(RespCode.$1001).desc());
			LOG.error("upload>>>", e);
			e.printStackTrace();
		}

		try {
			CourtUpload pro = (CourtUpload) JSONObject
					.toBean(reqDomain.getMain(), CourtUpload.class);
			Object obj = new JSONValidator().valid(pro);
			if (null == obj) {
				String token = reqDomain.getBiz().getToken();
				String subject = TokenUtil.parse(token, Constants.TOKEN_KEY);
				Vip info = (Vip) JSON.parseObject(subject, Vip.class);
				
				Court court = new Court();
				court.setId(UUIDUtil.generate());
				court.setVipId(info.getId());
				
				convert(pro,court);
				
				courtService.upload(court);
				
				safariResp = SafariResp.getInstance();
			} else {
				return obj;
			}
		} catch (Exception e) {
			safariResp = SafariResp.getInstance(RespCode.$1000, RespCode
					.getRspCode(RespCode.$1000).desc());
			LOG.error("upload>>>", e);
			e.printStackTrace();
		}
		
		return safariResp;
	}
	
	@ResponseBody
	@RequestMapping("find")
	@SafariMethod(checkToken=true, desc="查找附近球场信息",submit="POST")
	public Object find(ReqProtocol reqProtocol) {

		String request = null;
		JSONObject reqJson = null;
		ReqDomain reqDomain = null;
		SafariResp<?> safariResp = null;
		try {
			request = reqProtocol.getInfoJson();
			reqJson = JSONObject.fromObject(request);
			reqDomain = (ReqDomain) JSONObject.toBean(reqJson, ReqDomain.class);
		} catch (Exception e) {
			safariResp = SafariResp.getInstance(RespCode.$1001, RespCode
					.getRspCode(RespCode.$1001).desc());
			LOG.error("find>>>", e);
			e.printStackTrace();
		}

		try {
			CourtFind pro = (CourtFind) JSONObject
					.toBean(reqDomain.getMain(), CourtFind.class);
			Object obj = new JSONValidator().valid(pro);
			if (null == obj) {
				int pageNo = pro.getPageNo();
				int pageSize = pro.getPageSize();
				
				int pageIndex = (pageNo - 1) * pageSize;
				
				Court court = new Court();
				court.setLatitude(pro.getLatitude());
				court.setLongitude(pro.getLongitude());
				court.setLimited(pro.getLimited());
				court.setPageIndex(pageIndex);
				court.setPageSize(pageSize);
				
				PageUtil<Court> pager = courtService.findPage(court);
				
				safariResp = SafariResp.getInstance(pager);
			} else {
				return obj;
			}
		} catch (Exception e) {
			safariResp = SafariResp.getInstance(RespCode.$1000, RespCode
					.getRspCode(RespCode.$1000).desc());
			LOG.error("find>>>", e);
			e.printStackTrace();
		}
		
		return safariResp;
	}
	
	@ResponseBody
	@RequestMapping("detail")
	@SafariMethod(checkToken=true, desc="球场详情",submit="POST")
	public Object detail(ReqProtocol reqProtocol) {

		String request = null;
		JSONObject reqJson = null;
		ReqDomain reqDomain = null;
		SafariResp<?> safariResp = null;
		try {
			request = reqProtocol.getInfoJson();
			reqJson = JSONObject.fromObject(request);
			reqDomain = (ReqDomain) JSONObject.toBean(reqJson, ReqDomain.class);
		} catch (Exception e) {
			safariResp = SafariResp.getInstance(RespCode.$1001, RespCode
					.getRspCode(RespCode.$1001).desc());
			LOG.error("detail>>>", e);
			e.printStackTrace();
		}

		try {
			CourtDetail pro = (CourtDetail) JSONObject
					.toBean(reqDomain.getMain(), CourtDetail.class);
			Object obj = new JSONValidator().valid(pro);
			if (null == obj) {
				Court court = courtService.findById(pro.getId());
				
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("court", court);
				
				safariResp = SafariResp.getInstance(map);
			} else {
				return obj;
			}
		} catch (Exception e) {
			safariResp = SafariResp.getInstance(RespCode.$1000, RespCode
					.getRspCode(RespCode.$1000).desc());
			LOG.error("detail>>>", e);
			e.printStackTrace();
		}
		
		return safariResp;
	}

	private void convert(CourtUpload pro, Court court) {
		court.setAddress(pro.getAddress());
		court.setCity(pro.getCity());
		court.setDistrict(pro.getDistrict());
		court.setLatitude(pro.getLatitude());
		court.setLongitude(pro.getLongitude());
		court.setName(pro.getName());
		court.setPicture(pro.getPicture());
		court.setProvince(pro.getProvince());
		court.setStreet(pro.getStreet());
	}
}
