package org.safari.sys.main.service.impl;

import java.util.List;

import org.safari.pub.platform.global.Constants;
import org.safari.pub.utils.PropertiesUtil;
import org.safari.pub.utils.RegexUtil;
import org.safari.sys.main.entity.SMS;
import org.safari.sys.main.mapper.SMSMapper;
import org.safari.sys.main.service.SMSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("smsService")
public class SMSServiceImpl implements SMSService {
	
	private int limit = PropertiesUtil.getInt("safari.sms.limit");
	
	@Autowired
	private SMSMapper smsMapper;

	@Override
	@Transactional
	public void send(SMS sms) {
		smsMapper.insert(sms);
	}
	
	@Override
	@Transactional
	public boolean valid(String account, String code, String sendType) {
		boolean flag = false;
		String type = "";
		if(RegexUtil.isCellphone(account)){
			type = "1";
		}else if(RegexUtil.isEmail(account)){
			type = "2";
		}
		
		List<SMS> smses = smsMapper.findValid(account,type,sendType,Constants.FLAG_YES,Constants.STATUS_INIT,limit);
		if(null != smses && smses.size() > 0){
			for (SMS sms : smses) {
				if(sms.getCode().equalsIgnoreCase(code)){
					sms.setValidStat(Constants.FLAG_YES);
					flag = true;
					smsMapper.updateValid(sms);
					
					break;
				}
			}
		}
		return flag;
	}

	@Override
	public boolean findValidDeviceId(String deviceId, String sendType, int time,
			int num) {
		int count = smsMapper.findValidDeviceId(deviceId,sendType,time);
		if(count < num){
			return true;
		}
		return false;
	}

	@Override
	public boolean findValidMobile(String mobile, String sendType, int time, int num) {
		int count = smsMapper.findValidMobile(mobile,sendType,time);
		if(count < num){
			return true;
		}
		return false;
	}

}
