package org.safari.sys.main.service;

import org.safari.sys.main.entity.Version;

public interface VersionService {

	/**
	 * 根据类型查找可用的记录
	 * @param type String
	 * @return Version
	 */
	public Version findByType(String type);

}
