package org.safari.sys.main.service.impl;

import org.safari.pub.platform.global.Constants;
import org.safari.sys.main.entity.Version;
import org.safari.sys.main.mapper.VersionMapper;
import org.safari.sys.main.service.VersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VersionServiceImpl implements VersionService {
	
	@Autowired
	private VersionMapper versionMapper;

	@Override
	public Version findByType(String type) {
		return versionMapper.findByTypeAndAble(type,Constants.FLAG_YES,Constants.FLAG_NO);
	}

}
