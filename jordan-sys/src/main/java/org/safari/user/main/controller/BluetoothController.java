package org.safari.user.main.controller;

import java.util.HashMap;
import java.util.List;
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
import org.safari.user.main.entity.Bluetooth;
import org.safari.user.main.entity.Vip;
import org.safari.user.main.protocol.BluetoothPro.BluetoothBind;
import org.safari.user.main.protocol.BluetoothPro.BluetoothUnbund;
import org.safari.user.main.protocol.BluetoothPro.BluetoothUpdate;
import org.safari.user.main.service.BluetoothService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

@Controller
@RequestMapping("v0/user/bht")
public class BluetoothController {
	

	protected Logger LOG = LoggerFactory.getLogger(BluetoothController.class);
	
	@Autowired
	private BluetoothService bluetoothService;
	
	@ResponseBody
	@RequestMapping("bind")
	@SafariMethod(checkToken=true, desc="绑定蓝牙设备",submit="POST")
	public Object bind(ReqProtocol reqProtocol) {

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
			LOG.error("bind>>>", e);
			e.printStackTrace();
		}

		try {
			BluetoothBind pro = (BluetoothBind) JSONObject
					.toBean(reqDomain.getMain(), BluetoothBind.class);
			Object obj = new JSONValidator().valid(pro);
			if (null == obj) {
				String token = reqDomain.getBiz().getToken();
				String subject = TokenUtil.parse(token, Constants.TOKEN_KEY);
				Vip info = (Vip) JSON.parseObject(subject, Vip.class);
				
				//1 检测是否已经绑定过该蓝牙设备
				Bluetooth bluetooth = bluetoothService.findByVipIdAndMac(info.getId(),pro.getMac());
				if(null != bluetooth){
					return SafariResp.getInstance(RespCode.$1024, RespCode
							.getRspCode(RespCode.$1024).desc());
				}
				
				bluetooth = new Bluetooth();
				bluetooth.setId(UUIDUtil.generate());
				bluetooth.setVipId(info.getId());
				bluetooth.setName(pro.getName());
				bluetooth.setMac(pro.getMac());
				bluetooth.setSn(pro.getSn());
				
				bluetoothService.bind(bluetooth);
				
				safariResp = SafariResp.getInstance();
			} else {
				return obj;
			}
		} catch (Exception e) {
			safariResp = SafariResp.getInstance(RespCode.$1000, RespCode
					.getRspCode(RespCode.$1000).desc());
			LOG.error("bind>>>", e);
			e.printStackTrace();
		}
		
		return safariResp;
	}
	
	@ResponseBody
	@RequestMapping("unbund")
	@SafariMethod(checkToken=true ,desc="解绑蓝牙设备",submit="POST")
	public Object unbund(ReqProtocol reqProtocol) {

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
			LOG.error("unbund>>>", e);
			e.printStackTrace();
		}

		try {
			BluetoothUnbund pro = (BluetoothUnbund) JSONObject
					.toBean(reqDomain.getMain(), BluetoothUnbund.class);
			Object obj = new JSONValidator().valid(pro);
			if (null == obj) {
				
				bluetoothService.unbund(pro.getIds());
				
				safariResp = SafariResp.getInstance();
			} else {
				return obj;
			}
		} catch (Exception e) {
			safariResp = SafariResp.getInstance(RespCode.$1000, RespCode
					.getRspCode(RespCode.$1000).desc());
			LOG.error("unbund>>>", e);
			e.printStackTrace();
		}
		
		return safariResp;
	}
	
	@ResponseBody
	@RequestMapping("update")
	@SafariMethod(checkToken=true ,desc="更改蓝牙设备",submit="POST")
	public Object update(ReqProtocol reqProtocol) {

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
			LOG.error("update>>>", e);
			e.printStackTrace();
		}

		try {
			BluetoothUpdate pro = (BluetoothUpdate) JSONObject
					.toBean(reqDomain.getMain(), BluetoothUpdate.class);
			Object obj = new JSONValidator().valid(pro);
			if (null == obj) {
				
				bluetoothService.update(pro.getId(),pro.getName());
				
				safariResp = SafariResp.getInstance();
			} else {
				return obj;
			}
		} catch (Exception e) {
			safariResp = SafariResp.getInstance(RespCode.$1000, RespCode
					.getRspCode(RespCode.$1000).desc());
			LOG.error("update>>>", e);
			e.printStackTrace();
		}
		
		return safariResp;
	}
	
	@ResponseBody
	@RequestMapping("list")
	@SafariMethod(checkToken=true, desc="用户绑定蓝牙设备",submit="POST")
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
			String token = reqDomain.getBiz().getToken();
			String subject = TokenUtil.parse(token, Constants.TOKEN_KEY);
			Vip info = (Vip) JSON.parseObject(subject, Vip.class);
				
			List<Bluetooth> list = bluetoothService.findByVipId(info.getId());
			
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("bhts", list);
			
			safariResp = SafariResp.getInstance(map);
		} catch (Exception e) {
			safariResp = SafariResp.getInstance(RespCode.$1000, RespCode
					.getRspCode(RespCode.$1000).desc());
			LOG.error("list>>>", e);
			e.printStackTrace();
		}
		
		return safariResp;
	}
}
