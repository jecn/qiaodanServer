package org.safari.emall.main.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *<p>Title:球鞋图片</p>
 *<p>Description: </p>
 *<p>Company: 深圳市萨法瑞科技有限公司</p>
 *@author Alitalk
 *@date 2017-02-10
 */
public class ShoesPicture implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;

    /**
     * 创新时间
     */
    @JsonIgnore
    private Date createTime;

    /**
     * 备注
     */
    @JsonIgnore
    private String remarks;

    /**
     * 球鞋ID
     */
    @JsonIgnore
    private String shoesId;

    /**
     * 图片
     */
    private String picture;

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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public String getShoesId() {
        return shoesId;
    }

    public void setShoesId(String shoesId) {
        this.shoesId = shoesId == null ? null : shoesId.trim();
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture == null ? null : picture.trim();
    }
}