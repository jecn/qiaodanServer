package org.safari.sys.main.service.impl;

import java.util.List;

import org.safari.pub.platform.global.Constants;
import org.safari.sys.main.entity.SecurityDict;
import org.safari.sys.main.mapper.SecurityDictMapper;
import org.safari.sys.main.service.SecurityDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SecurityDictImpl implements SecurityDictService {
	
	@Autowired
	private SecurityDictMapper securityDictMapper;

	@Override
	public List<SecurityDict> findAll() {
		return securityDictMapper.findAll(Constants.FLAG_NO);
	}

}
