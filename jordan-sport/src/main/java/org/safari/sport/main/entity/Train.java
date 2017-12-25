package org.safari.sport.main.entity;

import java.io.Serializable;

import org.safari.pub.platform.web.entity.Base;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *<p>Title:训练</p>
 *<p>Description: </p>
 *<p>Company: 深圳市萨法瑞科技有限公司</p>
 *@author Alitalk
 *@date 2017-02-08
 */
public class Train extends Base implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private String id;
    
    /**
     * 训练字典ID
     */
    private String tdId;
    
    /**
     * 标题
     */
    private String title;
    
    /**
     * 类型
     */
    private String type;
    
    /**
     * 位置(得分后卫:SG， 控球后卫:PG， 小前锋:SF， 大前锋:PF，中锋:C)
     */
    private String position;
    
    /**
     * 简介
     */
    private String intro;

    /**
     * 缩略图
     */
    private String thumb;
    
    /**
     * 链接
     */
    private String link;

    /**
     * 内容
     */
    private String content;
    
    /**
     * 阅读次数
     */
    private Integer count;
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }
    
    @JsonIgnore
    public String getTdId() {
		return tdId;
	}

	public void setTdId(String tdId) {
		this.tdId = tdId == null ? null : tdId.trim();
	}
	
	public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type == null ? null : type.trim();
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position == null ? null : position.trim();
	}

	public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro == null ? null : intro.trim();
    }

    @JsonIgnore
    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb == null ? null : thumb.trim();
    }

    @JsonIgnore
	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link == null ? null : link.trim();
	}

	@JsonIgnore
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content == null ? null : content.trim();
	}

	@JsonIgnore
	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

}