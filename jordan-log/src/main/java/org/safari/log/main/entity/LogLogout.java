package org.safari.log.main.entity;

import java.io.Serializable;
import java.util.Date;

/**
 *<p>Title:退出日志</p>
 *<p>Description: </p>
 *<p>Company: 深圳市萨法瑞科技有限公司</p>
 *@author Alitalk
 *@date 2016-09-13
 */
public class LogLogout implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private String id;
    
    /**
     * 会员ID
     */
    private String vipId;

    /**
     * 退出时间
     */
    private Date logoutTime;

    /**
     * IP地址
     */
    private String ip;

    /**
     * 国家
     */
    private String country;

    /**
     * 区域
     */
    private String area;

    /**
     * 省份
     */
    private String region;

    /**
     * 市区
     */
    private String city;

    /**
     * 地区
     */
    private String county;

    /**
     * 运营商
     */
    private String isp;

    /**
     * 终端
     */
    private String terminal;

    /**
     * 设备名称
     */
    private String deviceName;

    /**
     * 设备ID
     */
    private String deviceId;

    /**
     * 设备操作系统
     */
    private String deviceOs;

    /**
     * 设备Token
     */
    private String deviceToken;

    /**
     * 设备版本
     */
    private String deviceVer;

    /**
     * 语言编码
     */
    private String lang;

    /**
     * App版本
     */
    private String version;

    /**
     * 网络类型 (1:WIFI,2:2G, 3:3G, 4:4G, 5:5G)
     */
    private String netType;
    
    /**
     * 退出状态
     */
    private String logoutCode;
    
    /**
     * 退出状态说明
     */
    private String logoutDesc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }
    
    public String getVipId() {
		return vipId;
	}

	public void setVipId(String vipId) {
		this.vipId = vipId == null ? null : vipId.trim();
	}

    public Date getLogoutTime() {
        return logoutTime;
    }

    public void setLogoutTime(Date logoutTime) {
        this.logoutTime = logoutTime;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

	public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region == null ? null : region.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county == null ? null : county.trim();
    }

    public String getIsp() {
        return isp;
    }

    public void setIsp(String isp) {
        this.isp = isp == null ? null : isp.trim();
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal == null ? null : terminal.trim();
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName == null ? null : deviceName.trim();
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId == null ? null : deviceId.trim();
    }

    public String getDeviceOs() {
        return deviceOs;
    }

    public void setDeviceOs(String deviceOs) {
        this.deviceOs = deviceOs == null ? null : deviceOs.trim();
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken == null ? null : deviceToken.trim();
    }

    public String getDeviceVer() {
        return deviceVer;
    }

    public void setDeviceVer(String deviceVer) {
        this.deviceVer = deviceVer == null ? null : deviceVer.trim();
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang == null ? null : lang.trim();
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version == null ? null : version.trim();
    }

    public String getNetType() {
        return netType;
    }

    public void setNetType(String netType) {
        this.netType = netType == null ? null : netType.trim();
    }

	public String getLogoutCode() {
		return logoutCode;
	}

	public void setLogoutCode(String logoutCode) {
		this.logoutCode = logoutCode == null ? null : logoutCode.trim();
	}

	public String getLogoutDesc() {
		return logoutDesc;
	}

	public void setLogoutDesc(String logoutDesc) {
		this.logoutDesc = logoutDesc == null ? null : logoutDesc.trim();
	}
    
}