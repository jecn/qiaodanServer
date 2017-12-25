package org.safari.log.main.service.impl;

import org.safari.log.main.entity.LogLogin;
import org.safari.log.main.mapper.LogLoginMapper;
import org.safari.log.main.service.LogLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LogLoginServiceImpl implements LogLoginService {
	
	@Autowired
	private LogLoginMapper loginMapper;

	@Override
	@Transactional
	public void insert(LogLogin logLogin) {
		loginMapper.insert(logLogin);
	}
	

}
