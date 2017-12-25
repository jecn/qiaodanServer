package org.safari.sport.main.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.safari.sport.main.entity.Court;

public interface CourtMapper {

    public int insert(Court court);
    
    public Court findById(@Param("id")String id, @Param("download")String download);

    public int findCount(Court court);

    public List<Court> findPage(Court court);

	public int findPosition(@Param("minlng")double minlng,@Param("maxlng") double maxlng, 
			@Param("minlat")double minlat,@Param("maxlat")double maxlat);

}