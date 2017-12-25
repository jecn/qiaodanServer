package org.safari.sys.main.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.safari.sys.main.entity.SMS;

public interface SMSMapper {

    public int insert(SMS sms);

	public List<SMS> findValid(@Param("account")String account, @Param("type")String type,
			@Param("sendType")String sendType, @Param("sendStat")String sendStat,
			@Param("validStat")String validStat,@Param("limit")int limit);

	public void updateValid(SMS sms);

	public int findValidDeviceId(@Param("deviceId")String deviceId, @Param("sendType")String sendType,
			@Param("time") int time);
	
	public int findValidMobile(@Param("mobile")String mobile, @Param("sendType")String sendType,
			@Param("time")int time);
}