package org.safari.sys.main.entity;

import java.io.Serializable;

import org.safari.pub.platform.web.entity.Base;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *<p>Title:客户端版本</p>
 *<p>Description: </p>
 *<p>Company: 深圳市萨法瑞科技有限公司</p>
 *@author Alitalk
 *@date 2016-09-14
 */
public class Version  extends Base implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /**
     * 主键ID
     */
    @JsonIgnore
    private String id;

    /**
     * 类型 1:Android Phone, 2:Android Pad, 3:iPhone,4:iPad, 5:PC
     */
    @JsonIgnore
    private String type;

    /**
     * 最低版本
     */
    private String lowestVer;

    /**
     * 最新版本
     */
    private String newVer;

    /**
     * 链接地址
     */
    private String link;

    /**
     * 是否可用 1 是 0 否 默认1
     */
    @JsonIgnore
    private String isAble;

    /**
     * 更新说明
     */
    @JsonIgnore
    private String desp;
    
    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getLowestVer() {
        return lowestVer;
    }

    public void setLowestVer(String lowestVer) {
        this.lowestVer = lowestVer == null ? null : lowestVer.trim();
    }

    public String getNewVer() {
        return newVer;
    }

    public void setNewVer(String newVer) {
        this.newVer = newVer == null ? null : newVer.trim();
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link == null ? null : link.trim();
    }

    public String getIsAble() {
        return isAble;
    }

    public void setIsAble(String isAble) {
        this.isAble = isAble == null ? null : isAble.trim();
    }

    public String getDesp() {
        return desp;
    }

    public void setDesp(String desp) {
        this.desp = desp == null ? null : desp.trim();
    }
}