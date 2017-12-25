package org.safari.log.main.controller;

import net.sf.json.JSONObject;

import org.safari.log.main.entity.LogExcption;
import org.safari.log.main.protocol.ExceptionPro;
import org.safari.log.main.service.ExceptionService;
import org.safari.pub.platform.aop.annotation.SafariMethod;
import org.safari.pub.platform.enums.DeviceEnum;
import org.safari.pub.platform.enums.NetEnum;
import org.safari.pub.platform.global.RespCode;
import org.safari.pub.platform.protocol.ReqProtocol;
import org.safari.pub.platform.protocol.SafariResp;
import org.safari.pub.platform.validator.JSONValidator;
import org.safari.pub.platform.web.entity.BizDomain;
import org.safari.pub.platform.web.entity.IPEntity;
import org.safari.pub.platform.web.entity.ReqDomain;
import org.safari.pub.utils.UUIDUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("v0/log")
public class ExceptionController {

	protected Logger LOG = LoggerFactory.getLogger(ExceptionController.class);
	
	@Autowired
	private ExceptionService exceptionService;
	
	@ResponseBody
	@RequestMapping("upload")
	@SafariMethod(checkToken=false,desc="上传异常信息",submit="POST")
	public Object valid(ReqProtocol reqProtocol) {

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
			ExceptionPro pro = (ExceptionPro) JSONObject.toBean(reqDomain.getMain(), ExceptionPro.class);
			Object obj = new JSONValidator().valid(pro);
			if (null == obj) {
		        
		        LogExcption log = new LogExcption();
		        log.setId(UUIDUtil.generate());
		        log.setExcpClass(pro.getExClass());
		        log.setExcpMethod(pro.getExMethod());
		        log.setExcpDesc(pro.getExDesc());
		        log.setExcpData(pro.getExData());
		        
		        parse(reqDomain, log);
		        
		        exceptionService.insert(log);
				
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
	
	private void parse(ReqDomain reqDomain, LogExcption log) {
		BizDomain biz = reqDomain.getBiz();
		IPEntity visit = reqDomain.getVisit();
		
		log.setDeviceName(biz.getDeviceName());
		log.setDeviceOs(biz.getDeviceOs());
		log.setDeviceVer(biz.getDeviceVersion());
		log.setIsp(visit.getIsp());
		log.setLang(biz.getLang());
		log.setNetType(NetEnum.getValue(biz.getNetType()));
		log.setTerminal(DeviceEnum.getValue(biz.getDeviceType()));
		log.setVersion(biz.getVersion());
	}
}
