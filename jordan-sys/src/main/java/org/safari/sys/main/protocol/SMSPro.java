package org.safari.sys.main.protocol;

import java.io.Serializable;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class SMSPro implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static class SMSSend{
		
		/**
		 * 账号 手机号/邮箱
		 */
		@NotEmpty(message="手机号或邮箱不能为空")
		private String account;
		
		/**
		 * 类型 1 注册 2 登录 3 忘记密码 4 绑定账号 5 解绑账号 6 注销账号
		 */
		@NotEmpty(message="类型不能为空")
		private String type;
		
		/**
		 * 发送类型 1 手机短信 2 邮件 
		 */
		@NotEmpty(message="发送类型不能为空")
		@Size(min=1,max=2)
		private String genre;

		public String getAccount() {
			return account;
		}

		public void setAccount(String account) {
			this.account = account;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getGenre() {
			return genre;
		}

		public void setGenre(String genre) {
			this.genre = genre;
		}
		
	}
	
	public static class SMSValid{
		/**
		 * 账号 手机号/邮箱
		 */
		@NotEmpty(message="手机号或邮箱不能为空")
		private String account;
		
		/**
		 * 类型 1 注册 2 登录 3 忘记密码 4 绑定账号 5 解绑账号 6 注销账号
		 */
		@NotEmpty(message="类型不能为空")
		private String type;
		
		/**
		 * 验证码
		 */
		@NotEmpty(message="验证码不能为空")
		private String code;

		public String getAccount() {
			return account;
		}

		public void setAccount(String account) {
			this.account = account;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}
		
	}

}
