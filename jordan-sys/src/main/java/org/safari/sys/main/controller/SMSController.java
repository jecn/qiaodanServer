package org.safari.sys.main.controller;

import net.sf.json.JSONObject;

import org.safari.pub.platform.aop.annotation.SafariMethod;
import org.safari.pub.platform.global.Constants;
import org.safari.pub.platform.global.RespCode;
import org.safari.pub.platform.protocol.ReqProtocol;
import org.safari.pub.platform.protocol.SafariResp;
import org.safari.pub.platform.sms.wiki.SMSUtil;
import org.safari.pub.platform.validator.JSONValidator;
import org.safari.pub.platform.web.entity.ReqDomain;
import org.safari.pub.utils.PropertiesUtil;
import org.safari.pub.utils.RandomUtil;
import org.safari.pub.utils.RegexUtil;
import org.safari.pub.utils.StringUtil;
import org.safari.pub.utils.UUIDUtil;
import org.safari.sys.core.enums.SMSEnum;
import org.safari.sys.main.entity.SMS;
import org.safari.sys.main.protocol.SMSPro.SMSSend;
import org.safari.sys.main.protocol.SMSPro.SMSValid;
import org.safari.sys.main.service.SMSService;
import org.safari.user.main.service.VipService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("v0/sys/sms")
public class SMSController {

	protected Logger LOG = LoggerFactory.getLogger(SMSController.class);
	
	private boolean smsFlag = PropertiesUtil.getBoolean("safari.sms.flag"); //是否校验短信验证码
	
	private int deviceShortTime = PropertiesUtil.getInt("safari.sms.device.short.time"); //同种设备ID短信发送验证间隔较短时间
	private int deviceShortNum = PropertiesUtil.getInt("safari.sms.device.short.number"); //同种设备ID短信发送验证间隔较短时间次数
	private int deviceLongTime = PropertiesUtil.getInt("safari.sms.device.long.time"); //同种设备ID短信发送验证间隔较长时间
	private int deviceLongNum = PropertiesUtil.getInt("safari.sms.device.long.number"); //同种设备ID短信发送验证间隔较长时间次数
	
	private int mobileShortTime = PropertiesUtil.getInt("safari.sms.mobile.short.time"); //同种手机号短信发送验证间隔较短时间
	private int mobileShortNum = PropertiesUtil.getInt("safari.sms.mobile.short.number"); //同种手机号短信发送验证间隔较短时间次数
	private int mobileLongTime = PropertiesUtil.getInt("safari.sms.mobile.long.time"); //同种手机号短信发送验证间隔较长时间
	private int mobileLongNum = PropertiesUtil.getInt("safari.sms.mobile.long.number"); //同种手机号短信发送验证间隔较长时间次数
	
	@Autowired
	private SMSService smsService;
	
	@Autowired
	private VipService vipService;
	
	@ResponseBody
	@RequestMapping("send")
	@SafariMethod(desc="发送验证码",submit="POST")
	public Object send(ReqProtocol reqProtocol) {

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
			LOG.error("send>>>", e);
			e.printStackTrace();
		}

		try {
			String deviceId = reqDomain.getBiz().getDeviceId();   //设备ID
			SMSSend pro = (SMSSend) JSONObject.toBean(reqDomain.getMain(), SMSSend.class);
			Object obj = new JSONValidator().valid(pro);
			if (null == obj) {
				String account = pro.getAccount(); // 手机号/邮箱
				String genre = pro.getGenre();  // 发送类型 1 手机短信 2 邮件 
				String type = pro.getType(); // 1 注册 2 登录 3 忘记密码 4 绑定账号 5 解绑账号 6 注销账号
				
				//校验账号是否合法， 合法的手机格式/邮箱格式
				if("1".equals(genre)){
					if(!RegexUtil.isCellphone(account)){
						return SafariResp.getInstance(RespCode.$1003, RespCode
								.getRspCode(RespCode.$1003).desc());
					}
				}
				
				if("2".equals(genre)){
					if(!RegexUtil.isEmail(account)){
						return SafariResp.getInstance(RespCode.$1003, RespCode
								.getRspCode(RespCode.$1003).desc());
					}
				}
				
				SMS sms = new SMS();
				String code = RandomUtil.randomNum(6);
				String content = "";
				String sendStat = Constants.FLAG_YES;
				String resCode = "";
				String resMsg = "";
				if("1".equals(genre)){
					
					if("1".equals(type) && null != vipService.findByUnique("mobile", account)){
						return SafariResp.getInstance(RespCode.$1027, RespCode
								.getRspCode(RespCode.$1027).desc());
					}
					
					//1.同一设备号，每10分钟最多发送相同类型的短信8次。邮件忽略
					if(!smsService.findValidDeviceId(deviceId,type,deviceLongTime,deviceLongNum)){
						 return SafariResp.getInstance(RespCode.$1004, RespCode
									.getRspCode(RespCode.$1004).desc());
					}
					//2.同一设备号，每5分钟最多发送相同类型的短信5次。邮件忽略
					if(!smsService.findValidDeviceId(deviceId,type,deviceShortTime,deviceShortNum)){
						return SafariResp.getInstance(RespCode.$1004, RespCode
								.getRspCode(RespCode.$1004).desc());
					}
					//3.同一手机号，每10分钟最多发送相同类型的短信5次。 邮件忽略
					if(!smsService.findValidMobile(account,type,mobileLongTime,mobileLongNum)){
						return SafariResp.getInstance(RespCode.$1004, RespCode
								.getRspCode(RespCode.$1004).desc());
					}
					//4.同一手机号，每5分钟最多发送相同类型的短信3次。邮件忽略
					if(!smsService.findValidMobile(account,type,mobileShortTime,mobileShortNum)){
						return SafariResp.getInstance(RespCode.$1004, RespCode
								.getRspCode(RespCode.$1004).desc());
					}
					
					//发送短信
					content = SMSEnum.getContent(type);
					content = content.replace("code", code);
					String result = SMSUtil.send(account, content);
					if(!StringUtil.isEmpty(result)){
						JSONObject json = JSONObject.fromObject(result);
						try {
							resCode = json.getInt("code") + "";
							if(!"1".equals(resCode)){
								sendStat = Constants.FLAG_NO;
							}
							
							resMsg = json.getString("msg");
						} catch (Exception e) {
							sendStat = Constants.FLAG_NO;
							resMsg = "发送失败";
							LOG.error("发送短信失败>>>" + result);
						}
					}else{
						sendStat = Constants.FLAG_NO;
						resMsg = "响应数据为空";
					}
				}else{
					if("2".equals(type) && null != vipService.findByUnique("email", account)){
						return SafariResp.getInstance(RespCode.$1027, RespCode
								.getRspCode(RespCode.$1027).desc());
					}
				}
				
				sms.setId(UUIDUtil.generate());
				sms.setDeviceId(deviceId);
				sms.setAccount(account);
				sms.setType(genre);
				sms.setSendType(type);
				sms.setSendStat(sendStat);
				sms.setCode(code);
				sms.setContent(content);
				sms.setResCode(resCode);
				sms.setResMsg(resMsg);
				sms.setValidStat(Constants.STATUS_INIT);
				
				smsService.send(sms);
				
				if("1".equals(sendStat)){
					safariResp = SafariResp.getInstance();
				}else{
					safariResp = SafariResp.getInstance(RespCode.$1029, RespCode
							.getRspCode(RespCode.$1029).desc());
				}
			} else {
				return obj;
			}
		} catch (Exception e) {
			safariResp = SafariResp.getInstance(RespCode.$1000, RespCode
					.getRspCode(RespCode.$1000).desc());
			LOG.error("send>>>", e);
			e.printStackTrace();
		}

		return safariResp;
	}
	
	@ResponseBody
	@RequestMapping("valid")
	@SafariMethod(desc="校验验证码",submit="POST")
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
			LOG.error("valid>>>", e);
			e.printStackTrace();
		}

		try {
			SMSValid pro = (SMSValid) JSONObject.toBean(reqDomain.getMain(), SMSValid.class);
			Object obj = new JSONValidator().valid(pro);
			if (null == obj) {
				String account = pro.getAccount(); // 手机号/邮箱
				String type = pro.getType(); // 1 注册 2 登录 3 忘记密码 4 绑定账号 5 解绑账号 6 注销账号
				String code = pro.getCode(); //验证码
				
				if(smsFlag &&!smsService.valid(account, code, type)){
					return SafariResp.getInstance(RespCode.$1005, RespCode
							.getRspCode(RespCode.$1005).desc());
				}

				safariResp = SafariResp.getInstance();
			} else {
				return obj;
			}
		} catch (Exception e) {
			safariResp = SafariResp.getInstance(RespCode.$1000, RespCode
					.getRspCode(RespCode.$1000).desc());
			LOG.error("valid>>>", e);
			e.printStackTrace();
		}

		return safariResp;
	}
}
