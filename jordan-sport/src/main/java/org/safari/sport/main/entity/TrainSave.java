package org.safari.sport.main.entity;

import java.io.Serializable;

import org.safari.pub.platform.web.entity.Base;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *<p>Title:训练分享</p>
 *<p>Description: </p>
 *<p>Company: 深圳市萨法瑞科技有限公司</p>
 *@author Alitalk
 *@date 2017-02-14
 */
public class TrainSave extends Base implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private String id;

    /**
     * 创建时间
     */
    private String createTimeStr;

    /**
     * 会员ID
     */
    @JsonIgnore
    private String vipId;

    /**
     * 运动ID
     */
    private String moveId;
    
    /**
     * 类型 1 收藏 2 分享
     */
    private String type;

    /**
     * 终端
     */
    private String terminal;

    /**
     * 分享平台(1 QQ 2 微信 3 微博)
     */
    private String platform;
    
    /**
     * 来源(1:结束运动 2:运动详情)
     */
    private String source;

    /**
     * 想法
     */
    private String say;
    
    /**
     * 链接
     */
    private String url;
    
    /**
     * 图片
     */
    private String img;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCreateTimeStr() {
        return createTimeStr;
    }

    public void setCreateTimeStr(String createTimeStr) {
        this.createTimeStr = createTimeStr;
    }

    public String getVipId() {
        return vipId;
    }

    public void setVipId(String vipId) {
        this.vipId = vipId == null ? null : vipId.trim();
    }

    public String getMoveId() {
        return moveId;
    }

    public void setMoveId(String moveId) {
        this.moveId = moveId == null ? null : moveId.trim();
    }
    
    public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type == null ? null : type.trim();
	}

	public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal == null ? null : terminal.trim();
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform == null ? null : platform.trim();
    }

    public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source == null ? null : source.trim();
	}

	public String getSay() {
		return say;
	}

	public void setSay(String say) {
		this.say = say == null ? null : say.trim();
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url == null ? null : url.trim();
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img == null ? null : img.trim();
	}
	
}