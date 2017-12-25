package org.safari.log.main.mapper;

import org.safari.log.main.entity.LogRegister;

public interface LogRegisterMapper {
	
	public LogRegister findById(String id);

    public int insert(LogRegister logRegister);

}