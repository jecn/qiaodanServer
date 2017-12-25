package org.safari.log.main.entity;

import java.io.Serializable;
import java.util.Date;

/**
 *<p>Title:异常日志</p>
 *<p>Description: </p>
 *<p>Company: 深圳市萨法瑞科技有限公司</p>
 *@author Alitalk
 *@date 2016-09-15
 */
public class LogExcption implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private String id;

    /**
     * 发生者
     */
    private String createBy;

    /**
     * 创建时间
     */
    private Date createTime;

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
     * 设备操作系统
     */
    private String deviceOs;

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
     * 网络类型 (0 未知 1WIFI 2 2G 3 3G 4 4G 5 5G)
     */
    private String netType;

    /**
     * 异常类
     */
    private String excpClass;

    /**
     * 异常方法
     */
    private String excpMethod;

    /**
     * 异常描述
     */
    private String excpDesc;

    /**
     * 异常详情
     */
    private String excpData;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

    public String getDeviceOs() {
        return deviceOs;
    }

    public void setDeviceOs(String deviceOs) {
        this.deviceOs = deviceOs == null ? null : deviceOs.trim();
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

    public String getExcpClass() {
        return excpClass;
    }

    public void setExcpClass(String excpClass) {
        this.excpClass = excpClass == null ? null : excpClass.trim();
    }

    public String getExcpMethod() {
        return excpMethod;
    }

    public void setExcpMethod(String excpMethod) {
        this.excpMethod = excpMethod == null ? null : excpMethod.trim();
    }

    public String getExcpDesc() {
        return excpDesc;
    }

    public void setExcpDesc(String excpDesc) {
        this.excpDesc = excpDesc == null ? null : excpDesc.trim();
    }

    public String getExcpData() {
        return excpData;
    }

    public void setExcpData(String excpData) {
        this.excpData = excpData == null ? null : excpData.trim();
    }
}