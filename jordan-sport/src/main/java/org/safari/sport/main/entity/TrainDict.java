package org.safari.sport.main.entity;

import java.io.Serializable;

import org.safari.pub.platform.web.entity.Base;

/**
 *<p>Title:训练字典</p>
 *<p>Description: </p>
 *<p>Company: 深圳市萨法瑞科技有限公司</p>
 *@author Alitalk
 *@date 2017-02-08
 */
public class TrainDict extends Base implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private String id;

    /**
     * 名称
     */
    private String name;
    
    /**
     * 状态(1 推荐训练 2 其他训练)
     */
    private String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type == null ? null : type.trim();
	}
    
    
}