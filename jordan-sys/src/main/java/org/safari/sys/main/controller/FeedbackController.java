package org.safari.sys.main.controller;

import net.sf.json.JSONObject;

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
import org.safari.sys.main.entity.Feedback;
import org.safari.sys.main.protocol.FeedbackPro;
import org.safari.sys.main.service.FeedbackService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("v0/sys/feed")
public class FeedbackController {
	
	protected Logger LOG = LoggerFactory.getLogger(FeedbackController.class);
	
	@Autowired
	private FeedbackService feedbackService;
	
	@ResponseBody
	@RequestMapping("upload")
	@SafariMethod(checkToken=true,desc="上传反馈内容",submit="POST")
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
			FeedbackPro pro = (FeedbackPro) JSONObject.toBean(reqDomain.getMain(), FeedbackPro.class);
			Object obj = new JSONValidator().valid(pro);
			if (null == obj) {
				String content = pro.getContent();// 反馈内容
				
				Feedback feedback = new Feedback();
				parse(reqDomain,feedback);
				feedback.setContent(content);
				
				feedbackService.insert(feedback);

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

	private void parse(ReqDomain reqDomain, Feedback feedback) {
		BizDomain biz = reqDomain.getBiz();
		IPEntity visit = reqDomain.getVisit();
		
		feedback.setId(UUIDUtil.generate());
		feedback.setDeviceName(biz.getDeviceName());
		feedback.setDeviceOs(biz.getDeviceOs());
		feedback.setDeviceVer(biz.getDeviceVersion());
		feedback.setIsp(visit.getIsp());
		feedback.setLang(biz.getLang());
		feedback.setNetType(NetEnum.getValue(biz.getNetType()));
		feedback.setTerminal(DeviceEnum.getValue(biz.getDeviceType()));
		feedback.setVersion(biz.getVersion());
	}

}
