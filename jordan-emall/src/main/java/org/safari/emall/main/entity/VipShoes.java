package org.safari.emall.main.entity;

import java.io.Serializable;

import org.safari.pub.platform.web.entity.Base;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *<p>Title:鞋柜</p>
 *<p>Description: </p>
 *<p>Company: 深圳市萨法瑞科技有限公司</p>
 *@author Alitalk
 *@date 2017-02-10
 */
public class VipShoes extends Base implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;
    
    /**
     * 球鞋ID
     */
    private String shoesId;
    
    /**
     * 球鞋信息
     */
    private Shoes shoes;

    /**
     * 会员ID
     */
    @JsonIgnore
    private String vipId;

    /**
     * 球鞋编号
     */
    private String code;
    
    /**
     * 名称
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
     * 尺码
     */
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
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }
    
    public String getShoesId() {
		return shoesId;
	}

	public void setShoesId(String shoesId) {
		this.shoesId = shoesId == null ? null : shoesId.trim();
	}
	
	public Shoes getShoes() {
		return shoes;
	}

	public void setShoes(Shoes shoes) {
		this.shoes = shoes;
	}

	public String getVipId() {
        return vipId;
    }

    public void setVipId(String vipId) {
        this.vipId = vipId == null ? null : vipId.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
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

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style == null ? null : style.trim();
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture == null ? null : picture.trim();
    }

    public String getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(String buyTime) {
        this.buyTime = buyTime;
    }

}