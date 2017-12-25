package org.safari.sys.main.protocol;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotEmpty;

public class VersionPro implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message="类型不能为空")
	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
