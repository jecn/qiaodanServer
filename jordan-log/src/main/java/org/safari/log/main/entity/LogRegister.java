package org.safari.log.main.entity;

import java.io.Serializable;
import java.util.Date;

/**
 *<p>Title:注册日志</p>
 *<p>Description: </p>
 *<p>Company: 深圳市萨法瑞科技有限公司</p>
 *@author Alitalk
 *@date 2016-09-13
 */
public class LogRegister implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private String id;

    /**
     * 注册时间
     */
    private Date regTime;

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
     * 账号(用户名、手机号、邮箱)
     */
    private String account;

    /**
     * 用户名类型 1 用户名 2 手机号 3 电子邮箱
     */
    private String type;

    /**
     * 注册编码 是否成功 (1 是 0 否)
     */
    private String regCode;

    /**
     * 注册描述
     */
    private String regDesc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Date getRegTime() {
        return regTime;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
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

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getRegCode() {
        return regCode;
    }

    public void setRegCode(String regCode) {
        this.regCode = regCode == null ? null : regCode.trim();
    }

    public String getRegDesc() {
        return regDesc;
    }

    public void setRegDesc(String regDesc) {
        this.regDesc = regDesc == null ? null : regDesc.trim();
    }
}