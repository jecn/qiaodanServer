package org.safari.sport.main.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
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
import org.safari.sport.main.entity.JoinReach;
import org.safari.sport.main.entity.ReachBall;
import org.safari.sport.main.protocol.ReachPro.MyReach;
import org.safari.sport.main.protocol.ReachPro.ReachAdd;
import org.safari.sport.main.protocol.ReachPro.ReachDetail;
import org.safari.sport.main.protocol.ReachPro.ReachList;
import org.safari.sport.main.service.ReachService;
import org.safari.user.main.entity.Vip;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

@Controller
@RequestMapping("v0/sport/reach")
public class ReachController {
	
	protected Logger LOG = LoggerFactory.getLogger(ReachController.class);

	@Autowired
	private ReachService reachService;
	
	@ResponseBody
	@RequestMapping("create")
	@SafariMethod(checkToken=true, desc="创建约球",submit="POST")
	public Object create(ReqProtocol reqProtocol) {
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
			LOG.error("create>>>", e);
			e.printStackTrace();
		}

		try {
			ReachAdd pro = (ReachAdd) JSONObject
					.toBean(reqDomain.getMain(), ReachAdd.class);
			Object obj = new JSONValidator().valid(pro);
			if (null == obj) {
				String token = reqDomain.getBiz().getToken();
				String subject = TokenUtil.parse(token, Constants.TOKEN_KEY);
				Vip info = (Vip) JSON.parseObject(subject, Vip.class);
				
				ReachBall reachBall = new ReachBall();
				reachBall.setId(UUIDUtil.generate());
				reachBall.setVipId(info.getId());
				
				convert(pro,reachBall);
				
				reachService.create(reachBall);
				
				safariResp = SafariResp.getInstance();
			} else {
				return obj;
			}
		} catch (Exception e) {
			safariResp = SafariResp.getInstance(RespCode.$1000, RespCode
					.getRspCode(RespCode.$1000).desc());
			LOG.error("create>>>", e);
			e.printStackTrace();
		}
		
		return safariResp;
	}
	
	@ResponseBody
	@RequestMapping("createList")
	@SafariMethod(checkToken=true, desc="我创建的约球列表",submit="POST")
	public Object createList(ReqProtocol reqProtocol) {
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
			LOG.error("createList>>>", e);
			e.printStackTrace();
		}

		try {
			MyReach pro = (MyReach) JSONObject
					.toBean(reqDomain.getMain(), MyReach.class);
			Object obj = new JSONValidator().valid(pro);
			if (null == obj) {
				String token = reqDomain.getBiz().getToken();
				String subject = TokenUtil.parse(token, Constants.TOKEN_KEY);
				Vip info = (Vip) JSON.parseObject(subject, Vip.class);
				
				PageUtil<ReachBall> pager = reachService.createList(info.getId(),
						pro.getPageNo(),pro.getPageSize());
				
				safariResp = SafariResp.getInstance(pager);
			} else {
				return obj;
			}
		} catch (Exception e) {
			safariResp = SafariResp.getInstance(RespCode.$1000, RespCode
					.getRspCode(RespCode.$1000).desc());
			LOG.error("createList>>>", e);
			e.printStackTrace();
		}
		
		return safariResp;
	}
	
	@ResponseBody
	@RequestMapping("list")
	@SafariMethod(checkToken=true, desc="约球列表",submit="POST")
	public Object list(ReqProtocol reqProtocol) {
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
			LOG.error("list>>>", e);
			e.printStackTrace();
		}

		try {
			ReachList pro = (ReachList) JSONObject
					.toBean(reqDomain.getMain(), ReachList.class);
			Object obj = new JSONValidator().valid(pro);
			if (null == obj) {
				ReachBall reachBall = new ReachBall();
				convert(pro,reachBall);
				
				PageUtil<ReachBall> pager = reachService.list(reachBall,pro.getSort(),
						pro.getPageNo(),pro.getPageSize());
				
				safariResp = SafariResp.getInstance(pager);
			} else {
				return obj;
			}
		} catch (Exception e) {
			safariResp = SafariResp.getInstance(RespCode.$1000, RespCode
					.getRspCode(RespCode.$1000).desc());
			LOG.error("list>>>", e);
			e.printStackTrace();
		}
		
		return safariResp;
	}
	
	@ResponseBody
	@RequestMapping("detail")
	@SafariMethod(checkToken=true, desc="约球详情",submit="POST")
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
			ReachDetail pro = (ReachDetail) JSONObject
					.toBean(reqDomain.getMain(), ReachDetail.class);
			Object obj = new JSONValidator().valid(pro);
			if (null == obj) {
				String isJoin = "0";   //我是否参与  1 参与  0 未参与
				String token = reqDomain.getBiz().getToken();
				String subject = TokenUtil.parse(token, Constants.TOKEN_KEY);
				Vip info = (Vip) JSON.parseObject(subject, Vip.class);
				
				ReachBall reachBall = reachService.findById(pro.getId());
				
				JSONArray array = new JSONArray();
				List<JoinReach> joinReachs = reachService.findJoinReachByRdId(pro.getId());
				for (JoinReach joinReach : joinReachs) {
					JSONObject json  = new JSONObject();
					json.put("joinId", joinReach.getVip().getId());
					if("0".equals(isJoin) &&  joinReach.getVip().getId().equals(info.getId())){
						isJoin = "1";
					}
					json.put("joinName", joinReach.getVip().getName());
					json.put("joinNick", joinReach.getVip().getNick());
					json.put("joinMobile", joinReach.getVip().getMobile());
					json.put("joinImg", joinReach.getVip().getImg());
					
					array.add(json);
				}
				
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("reach", reachBall);
				map.put("joins", array);
				map.put("isJoin", isJoin);
			
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
	
	@ResponseBody
	@RequestMapping("join")
	@SafariMethod(checkToken=true, desc="参与约球",submit="POST")
	public Object join(ReqProtocol reqProtocol) {
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
			LOG.error("join>>>", e);
			e.printStackTrace();
		}

		try {
			ReachDetail pro = (ReachDetail) JSONObject
					.toBean(reqDomain.getMain(), ReachDetail.class);
			Object obj = new JSONValidator().valid(pro);
			if (null == obj) {
				ReachBall reachBall = reachService.findById(pro.getId());
				
				// 1. 判断是否过期
				String time = reachBall.getTime();
				if((System.currentTimeMillis() / 1000) >= Long.parseLong(time) ){
					return SafariResp.getInstance(RespCode.$1021, RespCode
							.getRspCode(RespCode.$1021).desc());
				}
				
				// 2. 判断是否满员
				if(Integer.parseInt(reachBall.getPeople()) <= Integer.parseInt(reachBall.getJoin())){
					return SafariResp.getInstance(RespCode.$1022, RespCode
							.getRspCode(RespCode.$1022).desc());
				}
				
				// 3. 判断是否已参与
				
				String token = reqDomain.getBiz().getToken();
				String subject = TokenUtil.parse(token, Constants.TOKEN_KEY);
				Vip info = (Vip) JSON.parseObject(subject, Vip.class);
				
				JoinReach joinReach = new JoinReach();
				joinReach.setId(UUIDUtil.generate());
				joinReach.setVipId(info.getId());
				joinReach.setRbId(pro.getId());
				
				if(reachService.isJoin(joinReach)){
					return SafariResp.getInstance(RespCode.$1023, RespCode
							.getRspCode(RespCode.$1023).desc());
				}
				
				reachService.join(joinReach);
				
				safariResp = SafariResp.getInstance();
			} else {
				return obj;
			}
		} catch (Exception e) {
			safariResp = SafariResp.getInstance(RespCode.$1000, RespCode
					.getRspCode(RespCode.$1000).desc());
			LOG.error("join>>>", e);
			e.printStackTrace();
		}
		
		return safariResp;
	}
	
	@ResponseBody
	@RequestMapping("joinList")
	@SafariMethod(checkToken=true, desc="我参与的约球列表",submit="POST")
	public Object joinList(ReqProtocol reqProtocol) {
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
			LOG.error("joinList>>>", e);
			e.printStackTrace();
		}

		try {
			MyReach pro = (MyReach) JSONObject
					.toBean(reqDomain.getMain(), MyReach.class);
			Object obj = new JSONValidator().valid(pro);
			if (null == obj) {
				String token = reqDomain.getBiz().getToken();
				String subject = TokenUtil.parse(token, Constants.TOKEN_KEY);
				Vip info = (Vip) JSON.parseObject(subject, Vip.class);
				
				PageUtil<JoinReach> pager = reachService.joinList(info.getId(),
						pro.getBeginTime(),pro.getPageNo(),pro.getPageSize());
				
				safariResp = SafariResp.getInstance(pager);
			} else {
				return obj;
			}
		} catch (Exception e) {
			safariResp = SafariResp.getInstance(RespCode.$1000, RespCode
					.getRspCode(RespCode.$1000).desc());
			LOG.error("joinList>>>", e);
			e.printStackTrace();
		}
		
		return safariResp;
	}

	private void convert(ReachAdd pro, ReachBall reachBall) {
		reachBall.setAddress(pro.getAddress());
		reachBall.setCity(pro.getCity());
		reachBall.setDistrict(pro.getDistrict());
		reachBall.setDuration(pro.getDuration());
		reachBall.setLatitude(pro.getLatitude());
		reachBall.setLongitude(pro.getLongitude());
		reachBall.setPeople(pro.getPeople());
		reachBall.setPicture(pro.getPicture());
		reachBall.setProvince(pro.getProvince());
		reachBall.setRemarks(pro.getRemarks());
		reachBall.setSlogan(pro.getSlogan());
		reachBall.setStreet(pro.getStreet());
		reachBall.setTime(pro.getTime());
		reachBall.setType(pro.getType());
	}
	
	private void convert(ReachList pro, ReachBall reachBall) {
		reachBall.setType(pro.getType());
		reachBall.setProvince(pro.getProvince());
		reachBall.setCity(pro.getCity());
		reachBall.setDistrict(pro.getDistrict());
		reachBall.setBeginTime(pro.getBeginTime());
		reachBall.setEndTime(pro.getEndTime());
		reachBall.setLimited(pro.getLimited());
		reachBall.setLatitude(pro.getLatitude());
		reachBall.setLongitude(pro.getLongitude());
	}
		
	
}
