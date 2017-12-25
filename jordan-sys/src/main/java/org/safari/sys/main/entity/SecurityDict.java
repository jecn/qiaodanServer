package org.safari.sys.main.entity;

import java.io.Serializable;

import org.safari.pub.platform.web.entity.Base;

/**
 *<p>Title:密保</p>
 *<p>Description: </p>
 *<p>Company: 深圳市萨法瑞科技有限公司</p>
 *@author Alitalk
 *@date 2016-09-28
 */
public class SecurityDict  extends Base implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;

    /**
     * 问题
     */
    private String question;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question == null ? null : question.trim();
    }
}