package org.safari.sport.main.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.safari.sport.main.entity.JoinReach;

public interface JoinReachMapper {

    public int insert(JoinReach joinReach);

    public JoinReach findById(@Param("id")String id,@Param("download")String download);

    public int findCount(JoinReach joinReach);

    public List<JoinReach> findPage(JoinReach joinReach);

	public List<JoinReach> findJoinReachByRdId(@Param("rbId")String rbId, @Param("download")String download);

}