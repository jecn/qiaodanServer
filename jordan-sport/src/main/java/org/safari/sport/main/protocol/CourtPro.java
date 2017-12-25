package org.safari.sport.main.protocol;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class CourtPro implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public static class CourtUpload{
		 /**
	     * 名称
	     */
	    private String name;

	    /**
	     * 经度
	     */
	    @NotEmpty(message="球场地址不能为空")
	    private String longitude;

	    /**
	     * 纬度
	     */
	    @NotEmpty(message="球场地址不能为空")
	    private String latitude;

	    /**
	     * 省名
	     */
	    private String province;

	    /**
	     * 城市名
	     */
	    private String city;

	    /**
	     * 区县名
	     */
	    private String district;

	    /**
	     * 街道
	     */
	    private String street;

	    /**
	     * 地址
	     */
	    @NotEmpty(message="球场地址不能为空")
	    private String address;
	    
	    /**
	     * 图片
	     */
	    private String picture;
	    
	    /**
	     * 距离限制
	     */
	    private String limited;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getLongitude() {
			return longitude;
		}

		public void setLongitude(String longitude) {
			this.longitude = longitude;
		}

		public String getLatitude() {
			return latitude;
		}

		public void setLatitude(String latitude) {
			this.latitude = latitude;
		}

		public String getProvince() {
			return province;
		}

		public void setProvince(String province) {
			this.province = province;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public String getDistrict() {
			return district;
		}

		public void setDistrict(String district) {
			this.district = district;
		}

		public String getStreet() {
			return street;
		}

		public void setStreet(String street) {
			this.street = street;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getPicture() {
			return picture;
		}

		public void setPicture(String picture) {
			this.picture = picture;
		}

		public String getLimited() {
			return limited;
		}

		public void setLimited(String limited) {
			this.limited = limited;
		}
	    
	}
	
	public static class CourtFind{

	    /**
	     * 经度
	     */
	    @NotEmpty(message="球场地址不能为空")
	    private String longitude;

	    /**
	     * 纬度
	     */
	    @NotEmpty(message="球场地址不能为空")
	    private String latitude;

	    /**
	     * 距离限制
	     */
	    private String limited;
	    
	    /**
		 * 第几页
		 */
		@NotNull
		@Min(value=1,message="分页必须从第一页开始")
		private int pageNo;
		
		/**
		 * 分页 每页记录数
		 */
		@NotNull
		@Min(value=1,message="分页每页记录数不得少于1")
		private int pageSize;

		public String getLongitude() {
			return longitude;
		}

		public void setLongitude(String longitude) {
			this.longitude = longitude;
		}

		public String getLatitude() {
			return latitude;
		}

		public void setLatitude(String latitude) {
			this.latitude = latitude;
		}

		public String getLimited() {
			return limited;
		}

		public void setLimited(String limited) {
			this.limited = limited;
		}

		public int getPageNo() {
			return pageNo;
		}

		public void setPageNo(int pageNo) {
			this.pageNo = pageNo;
		}

		public int getPageSize() {
			return pageSize;
		}

		public void setPageSize(int pageSize) {
			this.pageSize = pageSize;
		}

	}
	
	public static class CourtDetail{
		/**
		 * 主键
		 */
		@NotEmpty
		private String id;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}
		
	}

}
