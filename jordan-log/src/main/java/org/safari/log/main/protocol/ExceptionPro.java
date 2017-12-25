package org.safari.log.main.protocol;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotEmpty;

public class ExceptionPro implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 异常发生的类
	 */
	@NotEmpty
	private String exClass;
	
	/**
	 * 异常发生的方法
	 */
	@NotEmpty
	private String exMethod;
	
	/**
	 * 异常描述
	 */
	@NotEmpty
	private String exDesc;
	
	/**
	 * 异常数据
	 */
	@NotEmpty
	private String exData;

	public String getExClass() {
		return exClass;
	}

	public void setExClass(String exClass) {
		this.exClass = exClass;
	}

	public String getExMethod() {
		return exMethod;
	}

	public void setExMethod(String exMethod) {
		this.exMethod = exMethod;
	}

	public String getExDesc() {
		return exDesc;
	}

	public void setExDesc(String exDesc) {
		this.exDesc = exDesc;
	}

	public String getExData() {
		return exData;
	}

	public void setExData(String exData) {
		this.exData = exData;
	}
	
}
