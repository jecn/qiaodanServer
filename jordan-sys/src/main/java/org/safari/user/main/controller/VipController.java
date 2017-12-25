package org.safari.user.main.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.safari.log.main.entity.LogLogin;
import org.safari.log.main.entity.LogLogout;
import org.safari.log.main.entity.LogRegister;
import org.safari.log.main.service.LogLoginService;
import org.safari.log.main.service.LogLogoutService;
import org.safari.log.main.service.LogRegisterService;
import org.safari.pub.platform.aop.annotation.SafariMethod;
import org.safari.pub.platform.enums.DeviceEnum;
import org.safari.pub.platform.enums.NetEnum;
import org.safari.pub.platform.global.Constants;
import org.safari.pub.platform.global.RespCode;
import org.safari.pub.platform.protocol.Keys;
import org.safari.pub.platform.protocol.ReqProtocol;
import org.safari.pub.platform.protocol.SafariResp;
import org.safari.pub.platform.tencent.utils.WXUtil;
import org.safari.pub.platform.validator.JSONValidator;
import org.safari.pub.platform.web.entity.BizDomain;
import org.safari.pub.platform.web.entity.IPEntity;
import org.safari.pub.platform.web.entity.ReqDomain;
import org.safari.pub.utils.DateUtil;
import org.safari.pub.utils.FileUtil;
import org.safari.pub.utils.MD5Util;
import org.safari.pub.utils.PropertiesUtil;
import org.safari.pub.utils.QRCodeUtil;
import org.safari.pub.utils.RandomUtil;
import org.safari.pub.utils.RegexUtil;
import org.safari.pub.utils.StringUtil;
import org.safari.pub.utils.TokenUtil;
import org.safari.pub.utils.UUIDUtil;
import org.safari.sys.core.enums.SMSEnum;
import org.safari.sys.main.service.SMSService;
import org.safari.user.main.entity.Vip;
import org.safari.user.main.protocol.VipPro.AuthLogin;
import org.safari.user.main.protocol.VipPro.Login;
import org.safari.user.main.protocol.VipPro.MobileAndPasswordAndTypeAndCode;
import org.safari.user.main.protocol.VipPro.Register;
import org.safari.user.main.protocol.VipPro.VipAccount;
import org.safari.user.main.protocol.VipPro.VipAccountAndType;
import org.safari.user.main.protocol.VipPro.VipAccountAndTypeAndCode;
import org.safari.user.main.protocol.VipPro.VipInfo;
import org.safari.user.main.protocol.VipPro.VipOldpassAndNewpass;
import org.safari.user.main.service.VipService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

@Controller
@RequestMapping("v0/user/vip")
public class VipController {

	protected Logger LOG = LoggerFactory.getLogger(VipController.class);
	
	private boolean smsFlag = PropertiesUtil.getBoolean("safari.sms.flag"); //是否校验短信验证码
	private String download = PropertiesUtil.getValue("safari.media.download"); //媒体文件下载路径
	
	private String qrs = "qrs";
	
	@Autowired
	private VipService vipService;
	
	@Autowired
	private SMSService smsService;
	
	@Autowired
	private LogLoginService logLoginService;
	
	@Autowired
	private LogRegisterService logRegisterService;
	
	@Autowired
	private LogLogoutService logLogoutService;
	
	@ResponseBody
	@RequestMapping("unique")
	@SafariMethod(desc="检测账号是否唯一",submit="POST")
	public Object unique(ReqProtocol reqProtocol) {

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
			LOG.error("unique>>>", e);
			e.printStackTrace();
		}

		try {
			VipAccountAndType pro = (VipAccountAndType) JSONObject
					.toBean(reqDomain.getMain(), VipAccountAndType.class);
			Object obj = new JSONValidator().valid(pro);
			if (null == obj) {
				String account = pro.getAccount(); // 账号  用户名/手机号/邮箱
				String type = pro.getType(); // 类型 1 用户名 2 手机号 3 邮箱
				
				//校验账号是否合法， 合法的手机格式/邮箱格式
				if("2".equals(type)){
					if(!RegexUtil.isCellphone(account)){
						return SafariResp.getInstance(RespCode.$1003, RespCode
								.getRspCode(RespCode.$1003).desc());
					}
				}
				
				if("3".equals(type)){
					if(!RegexUtil.isEmail(account)){
						return SafariResp.getInstance(RespCode.$1003, RespCode
								.getRspCode(RespCode.$1003).desc());
					}
				}

				//检测是否存在
				Vip vip = null;
				if ("1".equals(type)) {
					vip = vipService.findByUnique("username", account);
				} else if ("2".equals(type)) {
					vip = vipService.findByUnique("mobile", account);
				} else if ("3".equals(type)) {
					vip = vipService.findByUnique("email", account);
				} else {
					return SafariResp.getInstance(RespCode.$1006, RespCode
							.getRspCode(RespCode.$1006).desc());
				}

				if (null != vip) {
					return SafariResp.getInstance(RespCode.$1007, RespCode
							.getRspCode(RespCode.$1007).desc());
				} else {
					safariResp = SafariResp.getInstance();
				}
			} else {
				return obj;
			}
		} catch (Exception e) {
			safariResp = SafariResp.getInstance(RespCode.$1000, RespCode
					.getRspCode(RespCode.$1000).desc());
			LOG.error("unique>>>", e);
			e.printStackTrace();
		}

		return safariResp;
	}
	
	@ResponseBody
	@RequestMapping("register")
	@SafariMethod(desc="注册",submit="POST")
	public Object register(ReqProtocol reqProtocol){
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
			LOG.error("register>>>", e);
			e.printStackTrace();
		}
		//登录日志
		LogRegister log = new LogRegister();
		log.setId(UUIDUtil.generate());
		
		try {
			Register pro = (Register) JSONObject.toBean(reqDomain.getMain(), Register.class);
			parseLog(log,reqDomain);
			Object obj = new JSONValidator().valid(pro);
			if (null == obj) {
				String account = pro.getAccount(); // 账号  用户名/手机号/邮箱
				String password = pro.getPassword(); // 登录密码
				String type = pro.getType(); // 类型 1 用户名 2 手机号 3 邮箱
				String code = pro.getCode(); // 验证码
				
				log.setAccount(account);
				log.setType(type);
				
				//1. 校验账号是否合法， 合法的手机格式/邮箱格式
				if("2".equals(type)){
					if(!RegexUtil.isCellphone(account)){
						log.setRegCode(RespCode.$1003);
						log.setRegDesc(RespCode.getRspCode(RespCode.$1003).desc());
						
						return SafariResp.getInstance(RespCode.$1003, RespCode
								.getRspCode(RespCode.$1003).desc());
					}
				}
				
				if("3".equals(type)){
					if(!RegexUtil.isEmail(account)){
						log.setRegCode(RespCode.$1003);
						log.setRegDesc(RespCode.getRspCode(RespCode.$1003).desc());
						
						return SafariResp.getInstance(RespCode.$1003, RespCode
								.getRspCode(RespCode.$1003).desc());
					}
				}
				
				//2. 验证账号是否已被注册
				Vip vip = null;
				if ("1".equals(type)) {
					vip = vipService.findByUnique("username", account);
					if(null != vip){
						log.setRegCode(RespCode.$1027);
						log.setRegDesc(RespCode.getRspCode(RespCode.$1027).desc());
						
						return SafariResp.getInstance(RespCode.$1027, RespCode
								.getRspCode(RespCode.$1027).desc());
					}
				}else if ("2".equals(type)) {
					//验证验证码是否有效
					if(smsFlag && !smsService.valid(account,code,SMSEnum.getKey("register"))){
						log.setRegCode(RespCode.$1005);
						log.setRegDesc(RespCode.getRspCode(RespCode.$1005).desc());
						
						return SafariResp.getInstance(RespCode.$1005, RespCode
								.getRspCode(RespCode.$1005).desc());
					}
					
					vip = vipService.findByUnique("mobile", account);
					if(null != vip){
						log.setRegCode(RespCode.$1007);
						log.setRegDesc(RespCode.getRspCode(RespCode.$1007).desc());
						
						return SafariResp.getInstance(RespCode.$1007, RespCode
								.getRspCode(RespCode.$1007).desc());
					}
				}else if ("3".equals(type)) {
					vip = vipService.findByUnique("email", account);
					if(null != vip){
						log.setRegCode(RespCode.$1027);
						log.setRegDesc(RespCode.getRspCode(RespCode.$1027).desc());
						
						return SafariResp.getInstance(RespCode.$1027, RespCode
								.getRspCode(RespCode.$1027).desc());
					}
					
					//验证验证码是否有效
					if(smsFlag && !smsService.valid(account,code,SMSEnum.getKey("register"))){
						log.setRegCode(RespCode.$1005);
						log.setRegDesc(RespCode.getRspCode(RespCode.$1005).desc());
						
						return SafariResp.getInstance(RespCode.$1005, RespCode
								.getRspCode(RespCode.$1005).desc());
					}
					
				}else{
					log.setRegCode(RespCode.$1006);
					log.setRegDesc(RespCode.getRspCode(RespCode.$1006).desc());
					
					return SafariResp.getInstance(RespCode.$1006, RespCode
							.getRspCode(RespCode.$1006).desc());
					
				}
				
				vip = new Vip();
				vip.setId(UUIDUtil.generate());
				vip.setPassword(MD5Util.GetMD5Code(password));
				vip.setStat("1");  //正常使用
				if ("1".equals(type)) {
					vip.setUsername(account);
				} else if ("2".equals(type)) {
					//生成系统账号
					String username = RandomUtil.randomNInt(8);
					while(true){
						if(null == (vipService.findByUnique("username", username))){
							break;
						}
						username = RandomUtil.randomNInt(8);
					}
					vip.setUsername("s_" + username);
					
					vip.setMobile(account);
				} else if ("3".equals(type)) {
					//生成系统账号
					String username = RandomUtil.randomNInt(8);
					while(true){
						if(null == (vipService.findByUnique("username", username))){
							break;
						}
						username = RandomUtil.randomNInt(8);
					}
					vip.setUsername("s_" + username);
					
					vip.setEmail(account);
				} else {
					return SafariResp.getInstance(RespCode.$1006, RespCode
							.getRspCode(RespCode.$1006).desc());
				}
				
				String qrName = RandomUtil.randomNInt(20);//生成系统名
				String dir = download + qrs + FileUtil.fileSplit;  //存放目录
				
				QRCodeUtil.createQRFile(dir, qrName, vip.getId(), 200); //生成二维码

				vipService.register(vip);
				vip.setQr(qrs + FileUtil.fileSplit + qrName);
				
				log.setRegCode(RespCode.$0000);
				log.setRegDesc("注册成功");
				
				//设置token  有效期为7*24小时
				String token = TokenUtil.gen(JSON.toJSONString(vip), DateUtil.nextDate(
						new Date(),60*24*7, DateUtil.MI), Constants.TOKEN_KEY);
				
				Map<String,Object> map = new HashMap<String, Object>();
				map.put("token", token);
				
				safariResp = SafariResp.getInstance(map);
				
			} else {
				log.setRegCode(RespCode.$1003);
				log.setRegDesc(RespCode.getRspCode(RespCode.$1003).desc());
				return obj;
			}
		} catch (Exception e) {
			log.setRegCode(RespCode.$1000);
			log.setRegDesc(RespCode.getRspCode(RespCode.$1000).desc());
			
			safariResp = SafariResp.getInstance(RespCode.$1000, RespCode
					.getRspCode(RespCode.$1000).desc());
			LOG.error("register>>>", e);
			e.printStackTrace();
		}finally{
			logRegisterService.insert(log);
		}

		return safariResp;
	}

	@ResponseBody
	@RequestMapping("login")
	@SafariMethod(desc="系统登录",submit="POST")
	public Object login(ReqProtocol reqProtocol ){
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
			LOG.error("login>>>", e);
			e.printStackTrace();
		}

		//登录日志
		LogLogin log = new LogLogin();
		log.setId(UUIDUtil.generate());
		
		try {
			Login pro = (Login) JSONObject.toBean(reqDomain.getMain(), Login.class);
			parseLog(log,reqDomain);
			Object obj = new JSONValidator().valid(pro);
			if (null == obj) {
				String account = pro.getAccount(); // 账号 用户名/手机号/邮箱
				String type = pro.getType(); // 登录类型 1 密码登录 2 验证码登录
				String password = pro.getPassword(); //登录密码
				String code = pro.getCode(); //验证码
				
				log.setAccount(account);
				log.setType(type);
				
				Vip vip = null;
				if("1".equals(type)){
					vip = vipService.login(account, MD5Util.GetMD5Code(password));
					if(null == vip){
						log.setLoginCode(RespCode.$1011);
						log.setLoginDesc(RespCode.getRspCode(RespCode.$1011).desc());
						
						return SafariResp.getInstance(RespCode.$1011, RespCode
								.getRspCode(RespCode.$1011).desc());
					}
				}else if("2".equals(type)){
					//1. 验证验证码是否有效
					if(smsFlag && !smsService.valid(account,code,SMSEnum.getKey("login"))){
						log.setLoginCode(RespCode.$1005);
						log.setLoginDesc(RespCode.getRspCode(RespCode.$1005).desc());
						
						return SafariResp.getInstance(RespCode.$1005, RespCode
								.getRspCode(RespCode.$1005).desc());
					}
					
					//2. 验证账号是否已被注册
					vip = vipService.findByUnique("mobile", account);
					if(null == vip){
						vip = vipService.findByUnique("email", account);
						if(null == vip){
							log.setLoginCode(RespCode.$1008);
							log.setLoginDesc(RespCode.getRspCode(RespCode.$1008).desc());
							
							return SafariResp.getInstance(RespCode.$1008, RespCode
									.getRspCode(RespCode.$1008).desc());
						}
					}
				}
				
				String stat = vip.getStat();  //状态 0 注销 1 正常使用 2 暂停使用 
				if(!"1".equals(stat)){
					log.setLoginCode(RespCode.$1014);
					log.setLoginDesc(RespCode.getRspCode(RespCode.$1014).desc());
					
					return SafariResp.getInstance(RespCode.$1014, RespCode
							.getRspCode(RespCode.$1014).desc());
				}
				
				log.setLoginCode(RespCode.$0000);
				log.setLoginDesc("登录成功");
				
				//设置token  有效期为7*24小时
				String token = TokenUtil.gen(JSON.toJSONString(vip), DateUtil.nextDate(
						new Date(),60*24*7, DateUtil.MI), Constants.TOKEN_KEY);
				
				Map<String,Object> map = new HashMap<String, Object>();
				map.put("info", vip);
				map.put("token", token);
				
				safariResp = SafariResp.getInstance(map);
			} else {
				log.setLoginCode(RespCode.$1003);
				log.setLoginDesc(RespCode.getRspCode(RespCode.$1003).desc());
				
				return obj;
			}
		} catch (Exception e) {
			log.setLoginCode(RespCode.$1000);
			log.setLoginDesc(RespCode.getRspCode(RespCode.$1000).desc());
			
			safariResp = SafariResp.getInstance(RespCode.$1000, RespCode
					.getRspCode(RespCode.$1000).desc());
			LOG.error("login>>>", e);
			e.printStackTrace();
		}finally{
			logLoginService.insert(log);
		}

		return safariResp;
	}
	
	@ResponseBody
	@RequestMapping("authLogin")
	@SafariMethod(desc="授权登录",submit="POST")
	public Object authLogin(ReqProtocol reqProtocol){
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
			LOG.error("authLogin>>>", e);
			e.printStackTrace();
		}

		//登录日志
		LogLogin log = new LogLogin();
		log.setId(UUIDUtil.generate());
		
		try {
			AuthLogin pro = (AuthLogin) JSONObject.toBean(reqDomain.getMain(), AuthLogin.class);
			parseLog(log,reqDomain);
			Object obj = new JSONValidator().valid(pro);
			if (null == obj) {
				String openId = pro.getOpenId(); // 第三方授权ID
				String accessToken = pro.getAccessToken();  //第三方授权凭证
				String type = pro.getType(); // 授权类型 1微信、2微博、3 QQ、4百度 
				
				log.setOpenId(openId);
				log.setAccessToken(accessToken);
				log.setAuthType(type);

				//TODO 1. 校验openId的合法性   需调用第三方授权平台验证
				if("1".equals(type)){
					if(!WXUtil.checkToken(openId, accessToken)){
						log.setLoginCode(RespCode.$1006);
						log.setLoginDesc(RespCode.getRspCode(RespCode.$1006).desc());
						
						return SafariResp.getInstance(RespCode.$1006, RespCode
								.getRspCode(RespCode.$1006).desc());
					}
				}else if("2".equals(type)){
					//http://open.weibo.com/wiki/Oauth2/get_token_info
				}else if("3".equals(type)){
					
				}else if("4".equals(type)){
					
				}else{
					log.setLoginCode(RespCode.$1006);
					log.setLoginDesc(RespCode.getRspCode(RespCode.$1006).desc());
					
					return SafariResp.getInstance(RespCode.$1006, RespCode
							.getRspCode(RespCode.$1006).desc());
				}
				
				//2. 查询校验
				Vip vip = vipService.findByOpenIdAndType(openId, type);
				if(null == vip){
					vip = new Vip();
					vip.setId(UUIDUtil.generate());
					
					//生成系统账号
					String username = RandomUtil.randomNInt(8);
					while(true){
						if(null == (vipService.findByUnique("username", username))){
							break;
						}
						username = RandomUtil.randomNInt(8);
					}
					vip.setUsername("s_" + username);
					
					vip.setOpenId(openId);
					vip.setType(type);
					vip.setStat("1");  //正常使用
					
					String qrName = RandomUtil.randomNInt(20);//生成系统名
					String dir = download + qrs + FileUtil.fileSplit;  //存放目录
					
					QRCodeUtil.createQRFile(dir, qrName, vip.getId(), 200); //生成二维码
					
					vipService.register(vip);
				}
				
				log.setLoginCode(RespCode.$0000);
				log.setLoginDesc("授权登录成功");
				
				//设置token  有效期为7*24小时
				String token = TokenUtil.gen(JSON.toJSONString(vip), DateUtil.nextDate(
						new Date(),60*24*7, DateUtil.MI), Constants.TOKEN_KEY);
				
				Map<String,Object> map = new HashMap<String, Object>();
				map.put("info", vip);
				map.put("token", token);
				
				safariResp = SafariResp.getInstance(map);
			} else {
				log.setLoginCode(RespCode.$1003);
				log.setLoginDesc(RespCode.getRspCode(RespCode.$1003).desc());
				
				return obj;
			}
		} catch (Exception e) {
			log.setLoginCode(RespCode.$1000);
			log.setLoginDesc(RespCode.getRspCode(RespCode.$1000).desc());
			
			safariResp = SafariResp.getInstance(RespCode.$1000, RespCode
					.getRspCode(RespCode.$1000).desc());
			LOG.error("authLogin>>>", e);
			e.printStackTrace();
		}finally{
			logLoginService.insert(log);
		}

		return safariResp;
	}
	
	@ResponseBody
	@RequestMapping("info")
	@SafariMethod(checkToken=true,desc="获取个人信息",submit="POST")
	public Object info(ReqProtocol reqProtocol){
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
			LOG.error("info>>>", e);
			e.printStackTrace();
		}

		try {
			VipAccount pro  = (VipAccount) JSONObject.toBean(reqDomain.getMain(), VipAccount.class);
			Object obj = new JSONValidator().valid(pro);
			if (null == obj) {
				String token = reqDomain.getBiz().getToken();
				String subject = TokenUtil.parse(token, Constants.TOKEN_KEY);
				Vip info = (Vip) JSON.parseObject(subject, Vip.class);
				
				String account = pro.getAccount(); //账号 用户名/手机号/邮箱
				
				Vip vip = null;
				if(!StringUtil.isEmpty(account)){
					vip = vipService.findByUnique("username", account);
					if(null == vip){
						vip = vipService.findByUnique("mobile", account);
						if(null == vip){
							vip = vipService.findByUnique("email", account);
						}
					}
				}else{
					vip = vipService.findById(info.getId());
				}
				
				if(null != vip){
					if(!vip.getId().equals(info.getId())){
						vip.setQr("");
					}
					
					safariResp = SafariResp.getInstance(vip);
				}else{
					safariResp = SafariResp.getInstance(RespCode.$1008, RespCode
							.getRspCode(RespCode.$1008).desc());
				}
			}else{
				return obj;
			}
		} catch (Exception e) {
			safariResp = SafariResp.getInstance(RespCode.$1000, RespCode
					.getRspCode(RespCode.$1000).desc());
			LOG.error("info>>>", e);
			e.printStackTrace();
		}
		return safariResp;
	}
	
	@ResponseBody
	@RequestMapping("accountInfo")
	@SafariMethod(checkToken=true,desc="获取账户信息",submit="POST")
	public Object accountInfo(ReqProtocol reqProtocol){
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
			LOG.error("accountInfo>>>", e);
			e.printStackTrace();
		}

		try {
			VipAccount pro  = (VipAccount) JSONObject.toBean(reqDomain.getMain(), VipAccount.class);
			Object obj = new JSONValidator().valid(pro);
			if (null == obj) {
				String token = reqDomain.getBiz().getToken();
				String subject = TokenUtil.parse(token, Constants.TOKEN_KEY);
				Vip info = (Vip) JSON.parseObject(subject, Vip.class);
				
				String account = pro.getAccount(); //账号 用户名/手机号/邮箱
				
				Vip vip = null;
				if(!StringUtil.isEmpty(account)){
					vip = vipService.findByUnique("username", account);
					if(null == vip){
						vip = vipService.findByUnique("mobile", account);
						if(null == vip){
							vip = vipService.findByUnique("email", account);
						}
					}
				}else{
					vip = vipService.findById(info.getId());
				}
				
				if(null != vip){
					JSONObject json = new JSONObject();
					
					json.put("vipId", vip.getId());
					json.put("username", vip.getUsername() == null ? "": vip.getUsername());
					json.put("mobile", vip.getMobile() == null ? "": vip.getMobile());
					json.put("email", vip.getEmail() == null ? "": vip.getEmail());
					
					safariResp = SafariResp.getInstance(json);
				}else{
					safariResp = SafariResp.getInstance(RespCode.$1008, RespCode
							.getRspCode(RespCode.$1008).desc());
				}
			}else{
				return obj;
			}
		} catch (Exception e) {
			safariResp = SafariResp.getInstance(RespCode.$1000, RespCode
					.getRspCode(RespCode.$1000).desc());
			LOG.error("accountInfo>>>", e);
			e.printStackTrace();
		}
		return safariResp;
	}
	
	@ResponseBody
	@RequestMapping("bind")
	@SafariMethod(checkToken=true,desc="绑定账号",submit="POST")
	public Object bind(ReqProtocol reqProtocol){
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
			VipAccountAndTypeAndCode pro  = (VipAccountAndTypeAndCode) JSONObject
					.toBean(reqDomain.getMain(), VipAccountAndTypeAndCode.class);
			Object obj = new JSONValidator().valid(pro);
			if (null == obj) {
				String token = reqDomain.getBiz().getToken();
				String subject = TokenUtil.parse(token, Constants.TOKEN_KEY);
				Vip info = (Vip) JSON.parseObject(subject, Vip.class);	
				
				String account = pro.getAccount(); //账号
				String type = pro.getType(); //类型 2 手机号 3 邮箱
				String code = pro.getCode(); //验证码 
				
				//1. 校验账号是否合法， 合法的手机格式/邮箱格式
				if("2".equals(type)){
					if(!RegexUtil.isCellphone(account)){
						return SafariResp.getInstance(RespCode.$1003, RespCode
								.getRspCode(RespCode.$1003).desc());
					}
				}
				
				if("3".equals(type)){
					if(!RegexUtil.isEmail(account)){
						return SafariResp.getInstance(RespCode.$1003, RespCode
								.getRspCode(RespCode.$1003).desc());
					}
				}
				
				//2. 验证验证码是否有效
				if(smsFlag && !smsService.valid(account,code,SMSEnum.getKey("bind"))){
						return SafariResp.getInstance(RespCode.$1005, RespCode
								.getRspCode(RespCode.$1005).desc());
				}
				
				//3. 绑定账号
				if("2".equals(type)){
					if(StringUtil.isEmpty(info.getMobile()) && null == vipService.findByUnique("mobile", account)){
						vipService.updateBind("mobile", account ,info.getId());
					}else{
						return SafariResp.getInstance(RespCode.$1007, RespCode
								.getRspCode(RespCode.$1007).desc());
					}
				}else if("3".equals(type)){
					if(StringUtil.isEmpty(info.getEmail()) && null == vipService.findByUnique("email", account)){
						vipService.updateBind("email", account ,info.getId());
					}else{
						return SafariResp.getInstance(RespCode.$1007, RespCode
								.getRspCode(RespCode.$1007).desc());
					}
				}
				
				safariResp = SafariResp.getInstance();
			}else{
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
	@SafariMethod(checkToken=true,desc="解绑账号",submit="POST")
	public Object unbund(ReqProtocol reqProtocol){
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
			VipAccountAndTypeAndCode pro  = (VipAccountAndTypeAndCode) JSONObject
					.toBean(reqDomain.getMain(), VipAccountAndTypeAndCode.class);
			Object obj = new JSONValidator().valid(pro);
			if (null == obj) {
				String token = reqDomain.getBiz().getToken();
				String subject = TokenUtil.parse(token, Constants.TOKEN_KEY);
				Vip info = (Vip) JSON.parseObject(subject, Vip.class);	
				
				String account = pro.getAccount(); //账号
				String type = pro.getType(); //类型 2 手机号 3 邮箱
				String code = pro.getCode(); //验证码 
				
				//1. 校验账号是否合法， 合法的手机格式/邮箱格式
				if("2".equals(type)){
					if(!RegexUtil.isCellphone(account)){
						return SafariResp.getInstance(RespCode.$1003, RespCode
								.getRspCode(RespCode.$1003).desc());
					}
				}
				
				if("3".equals(type)){
					if(!RegexUtil.isEmail(account)){
						return SafariResp.getInstance(RespCode.$1003, RespCode
								.getRspCode(RespCode.$1003).desc());
					}
				}
				
				//2. 验证验证码是否有效
				if(smsFlag && !smsService.valid(account,code,SMSEnum.getKey("unbund"))){
						return SafariResp.getInstance(RespCode.$1005, RespCode
								.getRspCode(RespCode.$1005).desc());
				}
				
				//3. 解绑账号
				if("2".equals(type)){
					vipService.updateBind("mobile", "" ,info.getId());
				}else if("3".equals(type)){
					vipService.updateBind("email", "" ,info.getId());
				}
				
				safariResp = SafariResp.getInstance();
			}else{
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
	@RequestMapping("setSingleInfo")
	@SafariMethod(checkToken=true,desc="修改用户信息(单个)",submit="POST")
	public Object singleInfo(ReqProtocol reqProtocol){
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
			LOG.error("singleInfo>>>", e);
			e.printStackTrace();
		}

		try {
			Keys pro  = (Keys) JSONObject.toBean(reqDomain.getMain(), Keys.class);
			Object obj = new JSONValidator().valid(pro);
			if (null == obj) {
				String token = reqDomain.getBiz().getToken();
				String subject = TokenUtil.parse(token, Constants.TOKEN_KEY);
				Vip info = (Vip) JSON.parseObject(subject, Vip.class);	
				
				String key = pro.getKey(); //Key值
				String value = pro.getValue(); //Value值
				
				vipService.updateSingle(key, value, info.getId());
				
				safariResp = SafariResp.getInstance();
			}else{
				return obj;
			}
		} catch (Exception e) {
			safariResp = SafariResp.getInstance(RespCode.$1000, RespCode
					.getRspCode(RespCode.$1000).desc());
			LOG.error("singleInfo>>>", e);
			e.printStackTrace();
		}
		return safariResp;
	}
	
	@ResponseBody
	@RequestMapping("setAllInfo")
	@SafariMethod(checkToken=true,desc="修改用户信息(所有)",submit="POST")
	public Object allInfo(ReqProtocol reqProtocol){
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
			LOG.error("allInfo>>>", e);
			e.printStackTrace();
		}

		try {
			VipInfo pro  = (VipInfo) JSONObject.toBean(reqDomain.getMain(), VipInfo.class);
			Object obj = new JSONValidator().valid(pro);
			if (null == obj) {
				String token = reqDomain.getBiz().getToken();
				String subject = TokenUtil.parse(token, Constants.TOKEN_KEY);
				Vip info = (Vip) JSON.parseObject(subject, Vip.class);	
				
				Vip vip = vipService.findById(info.getId());
				convert(pro,vip);
				vipService.update(vip);
				
				safariResp = SafariResp.getInstance();
			}else{
				return obj;
			}
		} catch (Exception e) {
			safariResp = SafariResp.getInstance(RespCode.$1000, RespCode
					.getRspCode(RespCode.$1000).desc());
			LOG.error("allInfo>>>", e);
			e.printStackTrace();
		}
		return safariResp;
	}
	
	@ResponseBody
	@RequestMapping("modifyPassword")
	@SafariMethod(checkToken=true,desc="修改登录密码",submit="POST")
	public Object modifyPassword(ReqProtocol reqProtocol){
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
			LOG.error("modifyPassword>>>", e);
			e.printStackTrace();
		}

		try {
			VipOldpassAndNewpass pro  = (VipOldpassAndNewpass) JSONObject.toBean(reqDomain.getMain(),
					VipOldpassAndNewpass.class);
			Object obj = new JSONValidator().valid(pro);
			if (null == obj) {
				String token = reqDomain.getBiz().getToken();
				String subject = TokenUtil.parse(token, Constants.TOKEN_KEY);
				Vip info = (Vip) JSON.parseObject(subject, Vip.class);
				
				String oldPass = pro.getOldPass();  //原密码
				String newPass = pro.getNewPass();  //新密码
				
				Vip vip = vipService.findById(info.getId());
				if(!vip.getPassword().equals(MD5Util.GetMD5Code(oldPass))){
					return SafariResp.getInstance(RespCode.$1012, RespCode
							.getRspCode(RespCode.$1012).desc());
				}
				
				vipService.updatePassword(MD5Util.GetMD5Code(newPass), info.getId());
				
				safariResp = SafariResp.getInstance();
			}else{
				return obj;
			}
		} catch (Exception e) {
			safariResp = SafariResp.getInstance(RespCode.$1000, RespCode
					.getRspCode(RespCode.$1000).desc());
			LOG.error("modifyPassword>>>", e);
			e.printStackTrace();
		}
		return safariResp;
	}
	
	@ResponseBody
	@RequestMapping("forgetPassword")
	@SafariMethod(desc="忘记登录密码",submit="POST")
	public Object forgetPassword(ReqProtocol reqProtocol){
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
			LOG.error("forgetPassword>>>", e);
			e.printStackTrace();
		}

		try {
			MobileAndPasswordAndTypeAndCode pro  = (MobileAndPasswordAndTypeAndCode) JSONObject
					.toBean(reqDomain.getMain(),MobileAndPasswordAndTypeAndCode.class);
			Object obj = new JSONValidator().valid(pro);
			if (null == obj) {
				String mobile = pro.getMobile();  //会员手机号
				String password = pro.getPassword();  //新密码
				String type = pro.getType(); //验证类型 1 密保验证 2 短信验证 3 邮箱验证
				String code = pro.getCode(); //验证码
				
				Vip vip = vipService.findByUnique("mobile", mobile);
				if(null == vip){
					return SafariResp.getInstance(RespCode.$1025, RespCode
							.getRspCode(RespCode.$1025).desc());
				}
				
				String account = "";
				if("2".equals(type)){
					account = vip.getMobile();
				}else if("3".equals(type)){
					account = vip.getEmail();
				}
				
				// 校验验证码是否有效
				if(smsFlag && !smsService.valid(account,code,SMSEnum.getKey("forgetLoginPass"))){
						return SafariResp.getInstance(RespCode.$1005, RespCode
								.getRspCode(RespCode.$1005).desc());
				}
				
				vipService.updatePassword(MD5Util.GetMD5Code(password), vip.getId());
				
				safariResp = SafariResp.getInstance();
			}else{
				return obj;
			}
		} catch (Exception e) {
			safariResp = SafariResp.getInstance(RespCode.$1000, RespCode
					.getRspCode(RespCode.$1000).desc());
			LOG.error("forgetPassword>>>", e);
			e.printStackTrace();
		}
		return safariResp;
	}
	
	@ResponseBody
	@RequestMapping("logout")
	@SafariMethod(checkToken=true,desc="退出",submit="POST")
	public Object logout(ReqProtocol reqProtocol){
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
			LOG.error("logout>>>", e);
			e.printStackTrace();
		}

		//退出日志
		LogLogout log = new LogLogout();
		log.setId(UUIDUtil.generate());
		
		try {
			parseLog(log,reqDomain);
			
			String token = reqDomain.getBiz().getToken();
			String subject = TokenUtil.parse(token, Constants.TOKEN_KEY);
			Vip info = (Vip) JSON.parseObject(subject, Vip.class);	
				
			log.setVipId(info.getId());
			log.setLogoutCode(RespCode.$0000);
			log.setLogoutDesc("退出成功");
			
			safariResp = SafariResp.getInstance();
		} catch (Exception e) {
			log.setLogoutCode(RespCode.$1000);
			log.setLogoutDesc(RespCode.getRspCode(RespCode.$1000).desc());
			
			safariResp = SafariResp.getInstance(RespCode.$1000, RespCode
					.getRspCode(RespCode.$1000).desc());
			LOG.error("logout>>>", e);
			e.printStackTrace();
		}finally{
			logLogoutService.insert(log);
		}

		return safariResp;
	}
	
	@ResponseBody
	@RequestMapping("logoff")
	@SafariMethod(checkToken=true,desc="注销",submit="POST")
	public Object logoff(ReqProtocol reqProtocol){
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
			LOG.error("logoff>>>", e);
			e.printStackTrace();
		}

		try {
			
			safariResp = SafariResp.getInstance();
		} catch (Exception e) {
			safariResp = SafariResp.getInstance(RespCode.$1000, RespCode
					.getRspCode(RespCode.$1000).desc());
			LOG.error("logoff>>>", e);
			e.printStackTrace();
		}

		return safariResp;
	}
	
	private void convert(VipInfo vipInfo, Vip info) {
		info.setName(vipInfo.getName());
		info.setNick(vipInfo.getNick());
		info.setGender(vipInfo.getGender());
		info.setAge(vipInfo.getAge());
		info.setBirthday(vipInfo.getBirthday());
		info.setQq(vipInfo.getQq());
		info.setImg(vipInfo.getImg());
		info.setPosition(vipInfo.getPosition());
		info.setHeight(vipInfo.getHeight());
		info.setWeight(vipInfo.getWeight());
		info.setFooter(vipInfo.getFooter());
	}

	private void parseLog(LogRegister log, ReqDomain reqDomain) {
		BizDomain biz = reqDomain.getBiz();
		IPEntity visit = reqDomain.getVisit();
		
		log.setDeviceId(biz.getDeviceId());
		log.setDeviceName(biz.getDeviceName());
		log.setDeviceOs(biz.getDeviceOs());
		log.setDeviceToken(biz.getDeviceToken());
		log.setDeviceVer(biz.getDeviceVersion());
		log.setLang(biz.getLang());
		log.setNetType(NetEnum.getValue(biz.getNetType()));
		log.setTerminal(DeviceEnum.getValue(biz.getDeviceType()));
		log.setVersion(biz.getVersion());
		
		log.setIp(visit.getIp());
		log.setCountry(visit.getCountry());
		log.setArea(visit.getArea());
		log.setRegion(visit.getRegion());
		log.setCity(visit.getCity());
		log.setCounty(visit.getCounty());
		log.setIsp(visit.getIsp());
	}
	
	private void parseLog(LogLogin log, ReqDomain reqDomain) {
		BizDomain biz = reqDomain.getBiz();
		IPEntity visit = reqDomain.getVisit();
		
		log.setDeviceId(biz.getDeviceId());
		log.setDeviceName(biz.getDeviceName());
		log.setDeviceOs(biz.getDeviceOs());
		log.setDeviceToken(biz.getDeviceToken());
		log.setDeviceVer(biz.getDeviceVersion());
		log.setLang(biz.getLang());
		log.setNetType(NetEnum.getValue(biz.getNetType()));
		log.setTerminal(DeviceEnum.getValue(biz.getDeviceType()));
		log.setVersion(biz.getVersion());
		
		log.setIp(visit.getIp());
		log.setCountry(visit.getCountry());
		log.setArea(visit.getArea());
		log.setRegion(visit.getRegion());
		log.setCity(visit.getCity());
		log.setCounty(visit.getCounty());
		log.setIsp(visit.getIsp());
	}
	
	private void parseLog(LogLogout log, ReqDomain reqDomain) {
		BizDomain biz = reqDomain.getBiz();
		IPEntity visit = reqDomain.getVisit();
		
		log.setDeviceId(biz.getDeviceId());
		log.setDeviceName(biz.getDeviceName());
		log.setDeviceOs(biz.getDeviceOs());
		log.setDeviceToken(biz.getDeviceToken());
		log.setDeviceVer(biz.getDeviceVersion());
		log.setLang(biz.getLang());
		log.setNetType(NetEnum.getValue(biz.getNetType()));
		log.setTerminal(DeviceEnum.getValue(biz.getDeviceType()));
		log.setVersion(biz.getVersion());
		
		log.setIp(visit.getIp());
		log.setCountry(visit.getCountry());
		log.setArea(visit.getArea());
		log.setRegion(visit.getRegion());
		log.setCity(visit.getCity());
		log.setCounty(visit.getCounty());
		log.setIsp(visit.getIsp());
	}

}
