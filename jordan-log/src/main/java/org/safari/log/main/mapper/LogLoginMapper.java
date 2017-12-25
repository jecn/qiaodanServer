package org.safari.log.main.mapper;

import org.safari.log.main.entity.LogLogin;

public interface LogLoginMapper {

	public LogLogin findById(String id);
	
    public int insert(LogLogin logLogin);

}