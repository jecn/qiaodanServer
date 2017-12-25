package org.safari.sport.main.entity;

import java.io.Serializable;
import java.util.Date;

import org.safari.pub.platform.web.entity.Base;
import org.safari.user.main.entity.Vip;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *<p>Title:参与约球</p>
 *<p>Description: </p>
 *<p>Company: 深圳市萨法瑞科技有限公司</p>
 *@author Alitalk
 *@date 2017-02-13
 */
public class JoinReach extends Base implements Serializable {

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
    private String vipId;

    /**
     * 约球ID
     */
    private String rbId;
    
    /**
     * 起始时间
     */
    private String beginTime;
    
    /**
     * 参与者信息
     */
    private Vip vip;
    
    /**
     * 约球信息
     */
    private ReachBall reachBall;

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

    public String getRbId() {
        return rbId;
    }

    public void setRbId(String rbId) {
        this.rbId = rbId == null ? null : rbId.trim();
    }
    
	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public Vip getVip() {
		return vip;
	}

	public void setVip(Vip vip) {
		this.vip = vip;
	}

	public ReachBall getReachBall() {
		return reachBall;
	}

	public void setReachBall(ReachBall reachBall) {
		this.reachBall = reachBall;
	}
    
}