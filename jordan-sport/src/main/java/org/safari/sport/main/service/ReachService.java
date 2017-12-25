package org.safari.sport.main.service;

import java.util.List;

import org.safari.pub.utils.page.PageUtil;
import org.safari.sport.main.entity.JoinReach;
import org.safari.sport.main.entity.ReachBall;

public interface ReachService {

	public void create(ReachBall reachBall);
	
	public PageUtil<ReachBall> createList(String vipId, int pageNo, int pageSize);

	public PageUtil<ReachBall> list(ReachBall reachBall, String sort,
			int pageNo, int pageSize);

	public ReachBall findById(String id);
	
	public boolean isJoin(JoinReach joinReach);
	
	public void join(JoinReach joinReach);

	public PageUtil<JoinReach> joinList(String vipId, String beginTime, int pageNo, int pageSize);

	public List<JoinReach> findJoinReachByRdId(String rbId);


	

}
