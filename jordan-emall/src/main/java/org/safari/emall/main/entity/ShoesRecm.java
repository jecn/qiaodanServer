package org.safari.emall.main.entity;

import java.io.Serializable;

import org.safari.pub.platform.global.Constants;
import org.safari.pub.platform.web.entity.Base;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *<p>Title:球鞋推荐</p>
 *<p>Description: </p>
 *<p>Company: 深圳市萨法瑞科技有限公司</p>
 *@author Alitalk
 *@date 2017-02-10
 */
public class ShoesRecm extends Base implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;

    /**
     * 名称
     */
    private String name;
    
    private String code;
    
    private String styleNumber;
    
    /**
     * 图文说明
     */
    private String picDesc;

    /**
     * 文本说明
     */
    private String textDesc;
    
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
     * 链接
     */
    private String link;


    @JsonIgnore
    private String useable = Constants.FLAG_YES;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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

    public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getUseable() {
        return useable;
    }

    public void setUseable(String useable) {
        this.useable = useable == null ? null : useable.trim();
    }

	public String getCode() {
		return code == null ? null : code.trim();
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getStyleNumber() {
		return styleNumber;
	}

	public void setStyleNumber(String styleNumber) {
		this.styleNumber = styleNumber;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
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

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}

	public String getPicDesc() {
		return picDesc;
	}

	public void setPicDesc(String picDesc) {
		this.picDesc = picDesc;
	}

	public String getTextDesc() {
		return textDesc;
	}

	public void setTextDesc(String textDesc) {
		this.textDesc = textDesc;
	}
    
}