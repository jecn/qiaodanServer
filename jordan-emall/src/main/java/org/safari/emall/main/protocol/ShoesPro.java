package org.safari.emall.main.protocol;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class ShoesPro implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static class ShoesBind{
		
		/**
		 * 绑定方式   1 定向绑定 2 自定义绑定
		 */
		private String type;
		
		/**
		 * 球鞋ID
		 */
		private String shoesId;
		
		/**
		 * 球鞋编号
		 */
		private String code;
		
		/**
		 * 球鞋名称
		 */
		private String name;
		
		/**
	     * 价格
	     */
	    private String price;

	    /**
	     * 颜色
	     */
	    private String color;
		
		/**
		 * 球鞋尺寸
		 */
		@NotEmpty(message="球鞋尺码不能为空")
		private String size;
		
		/**
	     * 款式
	     */
	    private String style;

	    /**
	     * 图片
	     */
	    private String picture;

	    /**
	     * 购买时间
	     */
	    private String buyTime;
	    
		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getShoesId() {
			return shoesId;
		}

		public void setShoesId(String shoesId) {
			this.shoesId = shoesId;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getPrice() {
			return price;
		}

		public void setPrice(String price) {
			this.price = price;
		}

		public String getColor() {
			return color;
		}

		public void setColor(String color) {
			this.color = color;
		}

		public String getSize() {
			return size;
		}

		public void setSize(String size) {
			this.size = size;
		}

		public String getStyle() {
			return style;
		}

		public void setStyle(String style) {
			this.style = style;
		}

		public String getPicture() {
			return picture;
		}

		public void setPicture(String picture) {
			this.picture = picture;
		}

		public String getBuyTime() {
			return buyTime;
		}

		public void setBuyTime(String buyTime) {
			this.buyTime = buyTime;
		}

	}
	
	public static class ShoesUnbund{
		
		/**
		 * 球鞋绑定ID
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
	
	public static class ShoesPage{
		
		/**
	     * 款号
	     */
	    private String styleNumber;

	    /**
	     * 上市时间(e_shoes_dict: shoes_market)
	     */
	    private String marketTime;

	    /**
	     * 颜色(e_shoes_dict: shoes_color)
	     */
	    private String color;

	    /**
	     * 尺码(e_shoes_dict: shoes_size)
	     */
	    private String size;

	    /**
	     * 适用人群(e_shoes_dict: shoes_people)
	     */
	    private String forPeople;

	    /**
	     * 适用场地(e_shoes: shoes_space)
	     */
	    private String forSpace;
	    
	    /**
	     * 适用位置(e_shoes: shoes_position)
	     */
	    private String forPosition;

	    /**
	     * 款式(e_shoes_dict: shoes_style)
	     */
	    private String style;

	    /**
	     * 功能(e_shoes_dict: shoes_function)
	     */
	    private String function;

	    /**
	     * 价格区间 最小值
	     */
	    private String minPrice;
	    
	    /**
	     * 价格区间 最大值
	     */
	    private String maxprice;
	    
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

		public String getStyleNumber() {
			return styleNumber;
		}

		public void setStyleNumber(String styleNumber) {
			this.styleNumber = styleNumber;
		}

		public String getMarketTime() {
			return marketTime;
		}

		public void setMarketTime(String marketTime) {
			this.marketTime = marketTime;
		}

		public String getColor() {
			return color;
		}

		public void setColor(String color) {
			this.color = color;
		}

		public String getSize() {
			return size;
		}

		public void setSize(String size) {
			this.size = size;
		}

		public String getForPeople() {
			return forPeople;
		}

		public void setForPeople(String forPeople) {
			this.forPeople = forPeople;
		}

		public String getForSpace() {
			return forSpace;
		}

		public void setForSpace(String forSpace) {
			this.forSpace = forSpace;
		}

		public String getForPosition() {
			return forPosition;
		}

		public void setForPosition(String forPosition) {
			this.forPosition = forPosition;
		}

		public String getStyle() {
			return style;
		}

		public void setStyle(String style) {
			this.style = style;
		}

		public String getFunction() {
			return function;
		}

		public void setFunction(String function) {
			this.function = function;
		}

		public String getMinPrice() {
			return minPrice;
		}

		public void setMinPrice(String minPrice) {
			this.minPrice = minPrice;
		}

		public String getMaxprice() {
			return maxprice;
		}

		public void setMaxprice(String maxprice) {
			this.maxprice = maxprice;
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
	
	public static class ShoesDetail{
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
