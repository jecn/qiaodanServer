package org.safari.sys.main.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.safari.pub.platform.aop.annotation.SafariMethod;
import org.safari.pub.platform.global.RespCode;
import org.safari.pub.platform.protocol.ReqProtocol;
import org.safari.pub.platform.protocol.SafariResp;
import org.safari.sys.main.entity.SecurityDict;
import org.safari.sys.main.service.SecurityDictService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("v0/sys/security")
public class SecurityController {

	protected Logger LOG = LoggerFactory.getLogger(SecurityController.class);
	
	@Autowired
	private SecurityDictService securityDictService;
	
	@ResponseBody
	@RequestMapping("list")
	@SafariMethod(desc="密保问题列表",submit="POST")
	public Object list(ReqProtocol reqProtocol) {

		SafariResp<?> safariResp = null;
		try {
			List<SecurityDict> dicts = securityDictService.findAll();
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("securitys", dicts);
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
