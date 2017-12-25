package org.safari.sport.main.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.safari.sport.main.entity.Move;

public interface MoveMapper {

    public int insert(Move move);

    public int deleteById(String id);

    public int deleteByIds(List<String> idList);

    public Move findById(@Param("id")String id, @Param("download")String download);
    
    public int findCountByVipIdAndCycle(Move move);

    public List<Move> findListByVipIdAndCycle(Move move);
    
    public Move findRadar();
    
    public Move findRadarByVipId(String vipId);

	public void insertBatch(@Param("moves")List<Move> moves);

	public Move findByVipIdAndBeginTime(Move move);

	public int findAllByVipId(String vipId);
}