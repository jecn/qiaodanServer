package org.safari.sys.main.mapper;

import org.apache.ibatis.annotations.Param;
import org.safari.sys.main.entity.Version;

public interface VersionMapper {

	public Version findByTypeAndAble(@Param("type")String type, @Param("ableYes")String ableYes, 
			@Param("deleteNo")String deleteNo);

}
