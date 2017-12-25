package org.safari.sys.main.protocol;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class FeedbackPro implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@NotEmpty
	@Length(max=256,message="反馈内容不能超过256个字符")
	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}
