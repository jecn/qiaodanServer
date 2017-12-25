package org.safari.emall.main.entity;

import java.io.Serializable;

import org.safari.pub.platform.web.entity.Base;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *<p>Title:球鞋字典</p>
 *<p>Description: </p>
 *<p>Company: 深圳市萨法瑞科技有限公司</p>
 *@author Alitalk
 *@date 2017-02-10
 */
public class ShoesDict extends Base implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;

    /**
     * 标签名
     */
    private String label;

    /**
     * 数据值
     */
    private String value;

    /**
     * 类别
     */
    private String type;

    /**
     * 排序
     */
    @JsonIgnore
    private Integer sort;

    /**
     * 描述
     */
    @JsonIgnore
    private String inst;

    /**
     * 是否可用(1 是 0 否  默认为1)
     */
    @JsonIgnore
    private String useable;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label == null ? null : label.trim();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getInst() {
        return inst;
    }

    public void setInst(String inst) {
        this.inst = inst == null ? null : inst.trim();
    }

    public String getUseable() {
        return useable;
    }

    public void setUseable(String useable) {
        this.useable = useable == null ? null : useable.trim();
    }
}