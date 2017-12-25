package org.safari.log.main.service.impl;

import org.safari.log.main.entity.LogExcption;
import org.safari.log.main.mapper.LogExcptionMapper;
import org.safari.log.main.service.ExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExceptionServiceImpl implements ExceptionService {
	
	@Autowired
	private LogExcptionMapper excptionMapper;

	@Override
	public void insert(LogExcption log) {
		excptionMapper.insert(log);
	}

}
