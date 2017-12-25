package org.safari.sys.main.controller;

import net.sf.json.JSONObject;

import org.safari.pub.platform.aop.annotation.SafariMethod;
import org.safari.pub.platform.cache.Ehcache;
import org.safari.pub.platform.global.RespCode;
import org.safari.pub.platform.protocol.ReqProtocol;
import org.safari.pub.platform.protocol.SafariResp;
import org.safari.pub.platform.validator.JSONValidator;
import org.safari.pub.platform.web.entity.ReqDomain;
import org.safari.sys.main.entity.Version;
import org.safari.sys.main.protocol.VersionPro;
import org.safari.sys.main.service.VersionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("v0/sys/ver")
public class VersionController {
	
	protected Logger LOG = LoggerFactory.getLogger(VersionController.class);
	
	@Autowired
	private VersionService versionService;
	
	@ResponseBody
	@RequestMapping("renew")
	@SafariMethod(desc="检查更新",submit="POST")
	public Object renew(ReqProtocol reqProtocol) {

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
			LOG.error("renew>>>", e);
			e.printStackTrace();
		}

		try {
			VersionPro pro = (VersionPro) JSONObject.toBean(reqDomain.getMain(), VersionPro.class);
			Object obj = new JSONValidator().valid(pro);
			if (null == obj) {
				String type = pro.getType();
				//Version version = (Version) Ehcache.get("version", type);
				//if(null == version){
					Version version = versionService.findByType(type);
					//Ehcache.put("version", type, version);
				//}

				safariResp = SafariResp.getInstance(version);
			} else {
				return obj;
			}
		} catch (Exception e) {
			safariResp = SafariResp.getInstance(RespCode.$1000, RespCode
					.getRspCode(RespCode.$1000).desc());
			LOG.error("renew>>>", e);
			e.printStackTrace();
		}

		return safariResp;
	}

}
