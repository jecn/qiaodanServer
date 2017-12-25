package org.safari.sys.main.entity;

import java.io.Serializable;
import java.util.Date;

/**
 *<p>Title:反馈</p>
 *<p>Description: </p>
 *<p>Company: 深圳市萨法瑞科技有限公司</p>
 *@author Alitalk
 *@date 2016-09-14
 */
public class Feedback implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;

    /**
     * 创新时间
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
     * 设备系统版本号
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
     * 网络类型 (0:未知 1:WIFI,2:2G, 3:3G, 4:4G, 5:5G)
     */
    private String netType;

    /**
     * 反馈内容
     */
    private String content;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}