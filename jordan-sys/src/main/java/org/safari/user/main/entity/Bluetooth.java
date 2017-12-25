package org.safari.user.main.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *<p>Title:蓝牙绑定</p>
 *<p>Description: </p>
 *<p>Company: 深圳市萨法瑞科技有限公司</p>
 *@author Alitalk
 *@date 2017-02-06
 */
public class Bluetooth implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private String id;

    /**
     * 绑定时间
     */
    private String createTime;

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
     * MAC地址
     */
    private String mac;
    
    /**
     * SN号
     */
    private String sn;

    /**
     * 解绑时间
     */
    @JsonIgnore
    private Date unbundTime;

    /**
     * 状态 0 解绑 1 绑定(默认为1)
     */
    @JsonIgnore
    private String stat;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
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

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac == null ? null : mac.trim();
    }
    
    public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn == null ? null : sn.trim();
	}

	public Date getUnbundTime() {
        return unbundTime;
    }

    public void setUnbundTime(Date unbundTime) {
        this.unbundTime = unbundTime;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat == null ? null : stat.trim();
    }
}