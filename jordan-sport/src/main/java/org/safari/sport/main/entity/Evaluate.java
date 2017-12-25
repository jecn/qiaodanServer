package org.safari.sport.main.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *<p>Title:评估</p>
 *<p>Description: </p>
 *<p>Company: 深圳市萨法瑞科技有限公司</p>
 *@author Alitalk
 *@date 2017-02-08
 */
public class Evaluate implements Serializable {

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
     * 更新时间
     */
    @JsonIgnore
    private Date modifyTime;

    /**
     * 会员ID
     */
    private String vipId;

    /**
     * 篮球位置评估
     */
    private String positionEval;

    /**
     * 运动能力评估
     */
    private String sportEval;

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

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getVipId() {
        return vipId;
    }

    public void setVipId(String vipId) {
        this.vipId = vipId == null ? null : vipId.trim();
    }

    public String getPositionEval() {
        return positionEval;
    }

    public void setPositionEval(String positionEval) {
        this.positionEval = positionEval == null ? null : positionEval.trim();
    }

    public String getSportEval() {
        return sportEval;
    }

    public void setSportEval(String sportEval) {
        this.sportEval = sportEval == null ? null : sportEval.trim();
    }
}