package org.safari.sys.main.service;

import org.safari.sys.main.entity.SMS;

public interface SMSService {
	
	/**
	 * 发送短信
	 * @param sms SMS 
	 */
	public void send(SMS sms);
	
	

	/**
	 * 短信校验
	 * @param account String 账号 手机号/邮箱
	 * @param code String 验证码
	 * @param sendType String 验证类型
	 * @return boolean true 成功  false  失败
	 */
	public boolean valid(String account, String code, String sendType);



	/**
	 * 校验同种设备发送频率
	 * @param deviceId String 设备ID
	 * @param sendType String  发送类型
	 * @param time int  时间间隔
	 * @param num int 次数
	 * @return
	 */
	public boolean findValidDeviceId(String deviceId, String sendType,
			int time, int num);

	/**
	 * 校验同种手机号发送频率
	 * @param mobile String 手机号
	 * @param sendType String  发送类型
	 * @param time int  时间间隔
	 * @param num int 次数
	 * @return
	 */
	public boolean findValidMobile(String mobile, String sendType,
			int time, int num);

}
