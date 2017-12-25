package org.safari.sport.main.entity;

import java.io.Serializable;
import java.util.Date;

import org.safari.pub.platform.web.entity.Base;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *<p>Title:约球</p>
 *<p>Description: </p>
 *<p>Company: 深圳市萨法瑞科技有限公司</p>
 *@author Alitalk
 *@date 2017-02-13
 */
public class ReachBall extends Base implements Serializable {

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
     * 发布时间
     */
    public String publish;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 球场
     */
    private String courtId;

    /**
     * 会员ID
     */
    private String vipId;
    
    /**
     * 会员头像
     */
    private String vipImg;
    
    /**
     * 口号
     */
    private String slogan;

    /**
     * 比赛时间
     */
    private String time;
    
    /**
     * 比赛时长 (单位小时)
     */
    private String duration;

    /**
     * 参与人数
     */
    private String people;

    /**
     * 类型 (1 全场 2 半场)
     */
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
    private String address;
    
    /**
     * 联系电话
     */
    private String mobile;
    
    /**
     * 联系人
     */
    private String contact;
    
    /**
     * 参与人数
     */
    private String join;
    
    /**
     * 距离
     */
    private String distance;
    
    /**
     * 限制距离
     */
    @JsonIgnore
    private String limited;
    
    /**
     * 开始时间
     */
    @JsonIgnore
    private String beginTime;
    
    /**
     * 结束时间
     */
    @JsonIgnore
    private String endTime;

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
    
    public String getPublish() {
		return publish;
	}

	public void setPublish(String publish) {
		this.publish = publish == null ? null : publish.trim();
	}

	public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public String getCourtId() {
        return courtId;
    }

    public void setCourtId(String courtId) {
        this.courtId = courtId == null ? null : courtId.trim();
    }

    public String getVipId() {
        return vipId;
    }

    public void setVipId(String vipId) {
        this.vipId = vipId == null ? null : vipId.trim();
    }

    public String getVipImg() {
		return vipImg;
	}

	public void setVipImg(String vipImg) {
		this.vipImg = vipImg == null ? null : vipImg.trim();
	}
	
	public String getSlogan() {
		return slogan;
	}

	public void setSlogan(String slogan) {
		this.slogan = slogan == null ? null : slogan.trim();
	}

	public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time == null ? null : time.trim();
    }

    public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration == null ? null : duration.trim();
	}

	public String getPeople() {
        return people;
    }

    public void setPeople(String people) {
        this.people = people == null ? null : people.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture == null ? null : picture.trim();
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

	public String getMobile() {
		return mobile == null ? null : mobile.trim();
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getContact() {
		return contact == null ? null : contact.trim();
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getJoin() {
		return join;
	}

	public void setJoin(String join) {
		this.join = join;
	}

	public String getDistance() {
		return distance == null ? null : distance.trim();
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}
	
	public String getLimited() {
		return limited == null ? null : limited.trim();
	}

	public void setLimited(String limited) {
		this.limited = limited;
	}

	public String getBeginTime() {
		return beginTime == null ? null : beginTime.trim();
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime == null ? null : endTime.trim();
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
}