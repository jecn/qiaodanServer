package org.safari.emall.main.entity;

import java.io.Serializable;

import org.safari.pub.platform.global.Constants;
import org.safari.pub.platform.web.entity.Base;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *<p>Title:球鞋</p>
 *<p>Description: </p>
 *<p>Company: 深圳市萨法瑞科技有限公司</p>
 *@author Alitalk
 *@date 2017-02-10
 */
public class Shoes extends Base implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;

    /**
     * 编号
     */
    private String code;

    /**
     * 款号
     */
    private String styleNumber;

    /**
     * 名称
     */
    private String name;

    /**
     * 价格
     */
    private String price;

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
     * 状态(e_shoes_dict: shoes_stat)
     */
    private String stat;
    
    /**
     * 简介
     */
    private String intro;

    /**
     * 缩略图
     */
    private String thumb;

    /**
     * 图文说明
     */
    private String picDesc;

    /**
     * 文本说明
     */
    private String textDesc;

    /**
     * 是否可用(1 是 0 否  默认为1)
     */
    @JsonIgnore
    private String useable = Constants.FLAG_YES;
    
    /**
     * 价格区间 最小值
     */
    @JsonIgnore
    private String minPrice;
    
    /**
     * 价格区间 最大值
     */
    @JsonIgnore
    private String maxprice;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getStyleNumber() {
        return styleNumber;
    }

    public void setStyleNumber(String styleNumber) {
        this.styleNumber = styleNumber == null ? null : styleNumber.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price == null ? null : price.trim();
    }

    public String getMarketTime() {
        return marketTime;
    }

    public void setMarketTime(String marketTime) {
        this.marketTime = marketTime == null ? null : marketTime.trim();
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color == null ? null : color.trim();
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size == null ? null : size.trim();
    }

    public String getForPeople() {
        return forPeople;
    }

    public void setForPeople(String forPeople) {
        this.forPeople = forPeople == null ? null : forPeople.trim();
    }

    public String getForSpace() {
        return forSpace;
    }

    public void setForSpace(String forSpace) {
        this.forSpace = forSpace == null ? null : forSpace.trim();
    }
    
    public String getForPosition() {
		return forPosition;
	}

	public void setForPosition(String forPosition) {
		this.forPosition = forPosition == null ? null : forPosition.trim();
	}

	public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style == null ? null : style.trim();
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function == null ? null : function.trim();
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat == null ? null : stat.trim();
    }

    public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro == null ? null : intro.trim();
	}

	public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb == null ? null : thumb.trim();
    }

    public String getPicDesc() {
        return picDesc;
    }

    public void setPicDesc(String picDesc) {
        this.picDesc = picDesc == null ? null : picDesc.trim();
    }

    public String getTextDesc() {
        return textDesc;
    }

    public void setTextDesc(String textDesc) {
        this.textDesc = textDesc == null ? null : textDesc.trim();
    }

    public String getUseable() {
        return useable;
    }

    public void setUseable(String useable) {
        this.useable = useable == null ? null : useable.trim();
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
    
}