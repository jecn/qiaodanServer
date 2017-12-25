package org.safari.log.main.service.impl;

import org.safari.log.main.entity.LogRegister;
import org.safari.log.main.mapper.LogRegisterMapper;
import org.safari.log.main.service.LogRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LogRegisterServiceImpl implements LogRegisterService {
	
	@Autowired
	private LogRegisterMapper registerMapper;

	@Override
	@Transactional
	public void insert(LogRegister logRegister) {
		registerMapper.insert(logRegister);
	}

}
