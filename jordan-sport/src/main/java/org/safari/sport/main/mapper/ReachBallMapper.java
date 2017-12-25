package org.safari.sport.main.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.safari.sport.main.entity.ReachBall;

public interface ReachBallMapper {

    public int insert(ReachBall reachBall);

    public ReachBall findById(@Param("id")String id , @Param("download")String download);

    public int findCount(ReachBall reachBall);

    public List<ReachBall> findPage(ReachBall reachBall);
    
    public List<ReachBall> findPageDistance(ReachBall reachBall);

}