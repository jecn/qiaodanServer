package org.safari.user.main.protocol;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.safari.pub.platform.validator.my.MobileValid;
import org.safari.pub.platform.validator.my.QQValid;

public class VipPro implements Serializable{
	
	private static final long serialVersionUID = 1L;

	public static class VipAccountAndType{
		/**
		 * 账号  用户名/手机号/邮箱
		 */
		@NotEmpty
		private String account;
		
		/**
		 * 类型 1 用户名 2 手机号 3 邮箱
		 */
		@NotEmpty
		private String type;

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
		
	}
		
	
	public static class VipAccountAndTypeAndCode{
		/**
		 * 账号  用户名/手机号/邮箱
		 */
		@NotEmpty
		private String account;
		
		/**
		 * 类型 1 用户名 2 手机号 3 邮箱
		 */
		@NotEmpty
		private String type;
		
		/**
		 * 验证码
		 */
		@NotEmpty
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
	
	public static class VipAccount{
		/**
		 * 账号  用户名/手机号/邮箱
		 */
		private String account;
		
		public String getAccount() {
			return account;
		}

		public void setAccount(String account) {
			this.account = account;
		}
		
	}
	
	
	public static class Register{
		/**
		 * 账号  用户名/手机号/邮箱
		 */
		@NotEmpty
		private String account;
		
		/**
		 * 登录密码
		 */
		@NotEmpty
		@Length(min=6, max=18,message="密码长度必须为6到18位")
		private String password;
		
		/**
		 * 类型 1 用户名 2 手机号 3 邮箱
		 */
		@NotEmpty
		private String type;
		
		/**
		 * 验证码
		 */
		private String code;

		public String getAccount() {
			return account;
		}

		public void setAccount(String account) {
			this.account = account;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
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
	
	public static class Login{
		/**
		 * 账号  用户名/手机号/邮箱
		 */
		@NotEmpty
		private String account;
		
		/**
		 * 类型 1 密码登录  2 验证码登录
		 */
		@NotEmpty
		private String type;
		
		/**
		 * 登录密码
		 */
		private String password;
		
		/**
		 * 验证码
		 */
		private String code;

		public String getAccount() {
			return account;
		}

		public void setAccount(String account) {
			this.account = account;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
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

	public static class AuthLogin{
		/**
		 * 第三方授权ID
		 */
		@NotEmpty
		private String openId;
		
		/**
		 * 授权凭证
		 */
		@NotEmpty
		private String accessToken;
		
		/**
		 * 授权类型 1微信、2微博、3 QQ、4百度 
		 */
		@NotEmpty
		private String type;

		public String getOpenId() {
			return openId;
		}

		public void setOpenId(String openId) {
			this.openId = openId;
		}

		public String getAccessToken() {
			return accessToken;
		}

		public void setAccessToken(String accessToken) {
			this.accessToken = accessToken;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}
		
	}
	
	public static class VipInfo{
		
		/**
		 * 姓名
		 */
		private String name;
		
		/**
		 * 昵称
		 */
		private String nick;
		
		/**
		 * 性别 1 男 2 女
		 */
		private String gender;
		
		/**
		 * 年龄
		 */
		private Integer age;
		
		/**
		 * 出生年月
		 */
		private String birthday;
		
		/**
		 * QQ值
		 */
		@QQValid
		private String qq;
		
		/**
		 * 头像
		 */
		private String img;
		
		/**
		 * 擅长位置
		 */
		private String position;
		
		/**
		 * 身高
		 */
		private String height;
		
		/**
		 * 体重
		 */
		private String weight;
		
		/**
		 * 左右脚 R 右脚 L左脚
		 */
		private String footer;
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getNick() {
			return nick;
		}
		public void setNick(String nick) {
			this.nick = nick;
		}
		public String getGender() {
			return gender;
		}
		public void setGender(String gender) {
			this.gender = gender;
		}
		public Integer getAge() {
			return age;
		}
		public void setAge(Integer age) {
			this.age = age;
		}
		public String getBirthday() {
			return birthday;
		}
		public void setBirthday(String birthday) {
			this.birthday = birthday;
		}
		public String getQq() {
			return qq;
		}
		public void setQq(String qq) {
			this.qq = qq;
		}
		public String getImg() {
			return img;
		}
		public void setImg(String img) {
			this.img = img;
		}
		public String getPosition() {
			return position;
		}
		public void setPosition(String position) {
			this.position = position;
		}
		public String getHeight() {
			return height;
		}
		public void setHeight(String height) {
			this.height = height;
		}
		public String getWeight() {
			return weight;
		}
		public void setWeight(String weight) {
			this.weight = weight;
		}
		public String getFooter() {
			return footer;
		}
		public void setFooter(String footer) {
			this.footer = footer;
		}
		
	}
	
	public static class VipOldpassAndNewpass{
		
		/**
		 * 旧密码
		 */
		@NotEmpty
		@Length(min=6, max=18,message="密码长度必须为6到18位")
		public String oldPass;
		
		/**
		 * 新密码
		 */
		@NotEmpty
		@Length(min=6, max=18,message="密码长度必须为6到18位")
		public String newPass;

		public String getOldPass() {
			return oldPass;
		}

		public void setOldPass(String oldPass) {
			this.oldPass = oldPass;
		}

		public String getNewPass() {
			return newPass;
		}

		public void setNewPass(String newPass) {
			this.newPass = newPass;
		}
		
	}
	
	public static class VipVipIDAndPasswordAndTypeAndCode{
		
		/**
		 * 会员ID
		 */
		@NotEmpty
		private String vipId;
		
		/**
		 * 密码
		 */
		@NotEmpty
		private String password;
		
		/**
		 * 验证类型 1 密码验证 2 短信验证 3 邮箱验证
		 */
		@NotEmpty
		private String type;
		
		/**
		 * 验证码
		 */
		private String code;

		public String getVipId() {
			return vipId;
		}

		public void setVipId(String vipId) {
			this.vipId = vipId;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
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
	
	public static class MobileAndPasswordAndTypeAndCode{
		
		/**
		 * 会员手机号
		 */
		@MobileValid(message="手机号不能为空")
		private String mobile;
		
		/**
		 * 密码
		 */
		@NotEmpty
		private String password;
		
		/**
		 * 验证类型 1 密码验证 2 短信验证 3 邮箱验证
		 */
		@NotEmpty
		private String type;
		
		/**
		 * 验证码
		 */
		private String code;

		public String getMobile() {
			return mobile;
		}

		public void setMobile(String mobile) {
			this.mobile = mobile;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
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
