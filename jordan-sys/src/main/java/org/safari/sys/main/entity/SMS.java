package org.safari.sys.main.entity;

import java.io.Serializable;
import java.util.Date;

/**
 *<p>Title:短信</p>
 *<p>Description: </p>
 *<p>Company: 深圳市萨法瑞科技有限公司</p>
 *@author Alitalk
 *@date 2016-09-12
 */
public class SMS implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;
    
    /**
     * 设备ID
     */
    private String deviceId;

    /**
     * 账号 手机号/邮箱
     */
    private String account;

    /**
     * 发送类型 1 所有 2 手机号 3 邮箱
     */
    private String type;

    /**
     * 发送时间
     */
    private Date sendTime;

    /**
     * 验证类型 1 注册 2 登录 3 忘记密码 4 绑定账号 5 解绑账号 
     */
    private String sendType;

    /**
     * 发送状态 0 失败 1 成功
     */
    private String sendStat;

    /**
     * 结果编码
     */
    private String resCode;

    /**
     * 结果信息
     */
    private String resMsg;

    /**
     * 验证码
     */
    private String code;

    /**
     * 校验时间
     */
    private Date validTime;

    /**
     * 校验状态 0 失败 1 成功
     */
    private String validStat;

    /**
     * 内容信息
     */
    private String content;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }
    
    public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId == null ? null : deviceId.trim();
	}

	public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public String getSendType() {
        return sendType;
    }

    public void setSendType(String sendType) {
        this.sendType = sendType == null ? null : sendType.trim();
    }

    public String getSendStat() {
        return sendStat;
    }

    public void setSendStat(String sendStat) {
        this.sendStat = sendStat == null ? null : sendStat.trim();
    }

    public String getResCode() {
        return resCode;
    }

    public void setResCode(String resCode) {
        this.resCode = resCode == null ? null : resCode.trim();
    }

    public String getResMsg() {
        return resMsg;
    }

    public void setResMsg(String resMsg) {
        this.resMsg = resMsg == null ? null : resMsg.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public Date getValidTime() {
        return validTime;
    }

    public void setValidTime(Date validTime) {
        this.validTime = validTime;
    }

    public String getValidStat() {
        return validStat;
    }

    public void setValidStat(String validStat) {
        this.validStat = validStat == null ? null : validStat.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}