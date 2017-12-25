package org.safari.sport.main.entity;

import java.io.Serializable;
import java.util.Date;

import org.safari.pub.platform.web.entity.Base;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *<p>Title:球场</p>
 *<p>Description: </p>
 *<p>Company: 深圳市萨法瑞科技有限公司</p>
 *@author Alitalk
 *@date 2017-02-09
 */
public class Court extends Base implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private String id;

    /**
     * 创建时间
     */
    @JsonIgnore
    private Date createTime;

    /**
     * 会员ID
     */
    @JsonIgnore
    private String vipId;

    /**
     * 名称
     */
    private String name;

    /**
     * 经度
     */
    private String longitude;

    /**
     * 纬度
     */
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
    private String address;
    
    /**
     * 图片
     */
    private String picture;
    
    /**
     * 距离限制
     */
    @JsonIgnore
    private String limited;
    
    /**
     * 距离
     */
    private String distance;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getVipId() {
        return vipId;
    }

    public void setVipId(String vipId) {
        this.vipId = vipId == null ? null : vipId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude == null ? null : longitude.trim();
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude == null ? null : latitude.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district == null ? null : district.trim();
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street == null ? null : street.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture == null ? null : picture.trim();
	}

	public String getLimited() {
		return limited;
	}

	public void setLimited(String limited) {
		this.limited = limited == null ? null : limited.trim();
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance == null ? null : distance.trim();
	}
    
}