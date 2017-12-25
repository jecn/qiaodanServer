package org.safari.log.main.mapper;

import org.safari.log.main.entity.LogLogout;

public interface LogLogoutMapper {

	public LogLogout findById(String id);
	
    public int insert(LogLogout logLogout);

}