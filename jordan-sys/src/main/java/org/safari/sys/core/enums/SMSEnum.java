package org.safari.sys.core.enums;

/**
*  短信枚举类型
 * @author Alitalk
 * @date 2016-9-12
 */
public enum SMSEnum {
	
	/**
	 * 获取短信类型  
	 *  1 注册 2 登录 3 忘记密码 4 绑定账号 5 解绑账号 6 注销账号
	 */
	SMS_REGISTER("1","register","【乔丹体育】您注册账号的验证码是code，请在10分钟内输入。请勿告诉其他人。"),
	SMS_LOGIN("2","login",""),
	SMS_FORGETLOGINPASS("3","forgetLoginPass","【乔丹体育】你找回密码的验证码是code，请在10分钟内输入。请勿告诉其他人。"),
	SMS_BIND("4","bind",""),
	SMS_UNBUND("5","unbund",""),
	SMS_LOGOFF("6","logoff","");
	
	// 成员变量  
    private String key;
    private String value;  
    private String content;
    
    // 构造方法  
    private SMSEnum(String key, String value,String content) {  
        this.key = key;  
        this.value = value;  
        this.content = content;
    } 
    
    // 普通方法  
    public static String getKey(String value) {  
        for (SMSEnum sms : SMSEnum.values()) {  
            if (sms.getValue().equals(value)) {  
                return sms.key;  
            }  
        }  
        return null;  
    }  
    
    // 普通方法  
    public static String getValue(String key) {  
        for (SMSEnum sms : SMSEnum.values()) {  
            if (sms.getKey().equals(key)) {  
                return sms.value;  
            }  
        }  
        return null;  
    }  
    
    // 普通方法  
    public static String getContent(String key) {  
        for (SMSEnum sms : SMSEnum.values()) {  
            if (sms.getKey().equals(key)) {  
                return sms.content;  
            }  
        }  
        return null;  
    }  
    
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
