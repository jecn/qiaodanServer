package org.safari.log.main.service.impl;

import org.safari.log.main.entity.LogLogout;
import org.safari.log.main.mapper.LogLogoutMapper;
import org.safari.log.main.service.LogLogoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogLogoutServiceImpl implements LogLogoutService {

	@Autowired
	private LogLogoutMapper logoutMapper;

	@Override
	public void insert(LogLogout logLogout) {
		logoutMapper.insert(logLogout);
	}
	
	
}
