package org.safari.user.main.protocol;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotEmpty;

public class BluetoothPro implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public static class BluetoothBind{
		/**
		 * 蓝牙名称
		 */
		private String name;
		
		/**
		 * 蓝牙MAC地址
		 */
		@NotEmpty(message="MAC地址不能为空")
		private String mac;
		
		/**
		 * 蓝牙MAC地址
		 */
		@NotEmpty(message="SN号不能为空")
		private String sn;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getMac() {
			return mac;
		}

		public void setMac(String mac) {
			this.mac = mac;
		}

		public String getSn() {
			return sn;
		}

		public void setSn(String sn) {
			this.sn = sn;
		}
		
	}
	
	public static class BluetoothUpdate{
		
		@NotEmpty(message="参数为空")
		private String id;
		
		/**
		 * 名称
		 */
		@NotEmpty(message="名称不能为空")
		private String name;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
		
	}
	
	public static class BluetoothUnbund{
		/**
		 * 绑定ID
		 */
		@NotEmpty(message="参数为空")
		private String ids;

		public String getIds() {
			return ids;
		}

		public void setIds(String ids) {
			this.ids = ids;
		}
		
	}
	
	
}
