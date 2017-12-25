package org.safari.sport.main.protocol;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class ReachPro implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static class ReachAdd{
		
		/**
		 * 口号
		 */
		private String slogan;

	    /**
	     * 比赛时间
	     */
		@NotEmpty(message="比赛时间不能为空")
	    private String time;
	    
	    /**
	     * 比赛时长 (单位小时)
	     */
		@NotEmpty(message="比赛时长不能为空")
	    private String duration;

	    /**
	     * 参与人数
	     */
		@NotEmpty(message="参与人数不能为空")
	    private String people;

	    /**
	     * 类型 (1 全场 2 半场)
	     */
		@NotEmpty(message="约球类型不能为空")
	    private String type;

	    /**
	     * 图片
	     */
	    private String picture;

	    /**
	     * 经度
	     */
	    private String longitude;

	    /**
	     * 纬度
	     */
	    private String latitude;

	    /**
	     * 省份
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
	     * 详细地址
	     */
	    @NotEmpty(message="地址不能为空")
	    private String address;
	    
	    /**
	     * 备注
	     */
	    private String remarks;
	    
		public String getSlogan() {
			return slogan;
		}

		public void setSlogan(String slogan) {
			this.slogan = slogan;
		}

		public String getTime() {
			return time;
		}

		public void setTime(String time) {
			this.time = time;
		}

		public String getDuration() {
			return duration;
		}

		public void setDuration(String duration) {
			this.duration = duration;
		}

		public String getPeople() {
			return people;
		}

		public void setPeople(String people) {
			this.people = people;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getPicture() {
			return picture;
		}

		public void setPicture(String picture) {
			this.picture = picture;
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

		public String getRemarks() {
			return remarks;
		}

		public void setRemarks(String remarks) {
			this.remarks = remarks;
		}
	    
	}
	
	public static class MyReach{
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
		
		/**
		 * 开始时间
		 */
		private String beginTime;

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

		public String getBeginTime() {
			return beginTime;
		}

		public void setBeginTime(String beginTime) {
			this.beginTime = beginTime;
		}
		
	}
	
	public static class ReachList{
		
		/**
		 * 开始时间
		 */
		private String beginTime;
		
		/**
		 * 结束时间
		 */
		private String endTime;
		
		/**
		 * 类型
		 */
		private String type;
		
		/**
	     * 省份
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
	     * 经度
	     */
	    private String longitude;

	    /**
	     * 纬度
	     */
	    private String latitude;
	    
	    /**
	     * 限制距离
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
			
		/**
		 * 排序字段
		 */
		private String sort;

		public String getBeginTime() {
			return beginTime;
		}

		public void setBeginTime(String beginTime) {
			this.beginTime = beginTime;
		}

		public String getEndTime() {
			return endTime;
		}

		public void setEndTime(String endTime) {
			this.endTime = endTime;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
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

		public String getSort() {
			return sort;
		}

		public void setSort(String sort) {
			this.sort = sort;
		}
		
	}
	
	public static class ReachDetail{
		
		/**
		 * 主键ID
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
