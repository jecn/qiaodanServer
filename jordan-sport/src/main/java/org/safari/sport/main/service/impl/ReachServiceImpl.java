package org.safari.sport.main.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.safari.pub.platform.global.Constants;
import org.safari.pub.utils.UUIDUtil;
import org.safari.pub.utils.page.PageUtil;
import org.safari.sport.main.entity.JoinReach;
import org.safari.sport.main.entity.ReachBall;
import org.safari.sport.main.mapper.JoinReachMapper;
import org.safari.sport.main.mapper.ReachBallMapper;
import org.safari.sport.main.service.ReachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReachServiceImpl implements ReachService {
	
	@Autowired
	private ReachBallMapper reachBallMapper;
	
	@Autowired
	private JoinReachMapper joinReachMapper;

	@Override
	@Transactional
	public void create(ReachBall reachBall) {
		reachBallMapper.insert(reachBall);  //保存约球记录
		
		JoinReach joinReach = new JoinReach();
		joinReach.setId(UUIDUtil.generate());
		joinReach.setRbId(reachBall.getId());
		joinReach.setVipId(reachBall.getVipId());
		joinReachMapper.insert(joinReach);  //保存参与约球记录
	}
	
	@Override
	public PageUtil<ReachBall> createList(String vipId, int pageNo, int pageSize) {
		int pageIndex = (pageNo -1 ) * pageSize;
		
		ReachBall reachBall = new ReachBall();
		
		reachBall.setVipId(vipId);
		reachBall.setPageIndex(pageIndex);
		reachBall.setPageSize(pageSize);
		
		int count = reachBallMapper.findCount(reachBall);
		List<ReachBall> reachBalls = reachBallMapper.findPage(reachBall);
		
		int isNext = 1;
		if(count <= (pageIndex + pageSize)){
			isNext = 0;
		}
		
		PageUtil<ReachBall> page = new PageUtil<ReachBall>();
		page.setCount(count);
		page.setIsNext(isNext);
		page.setResults(reachBalls);
		
		return page;
	}
	
	@Override
	public PageUtil<ReachBall> list(ReachBall reachBall, String sort,
			int pageNo, int pageSize) {
		int pageIndex = (pageNo -1 ) * pageSize;
		
		reachBall.setPageIndex(pageIndex);
		reachBall.setPageSize(pageSize);
		
		int count = reachBallMapper.findCount(reachBall);
		
		List<ReachBall> reachBalls = new ArrayList<ReachBall>();
		if("distance".equals(sort)){
			reachBalls = reachBallMapper.findPageDistance(reachBall);
		}else{
			reachBalls = reachBallMapper.findPage(reachBall);
		}
		
		int isNext = 1;
		if(count <= (pageIndex + pageSize)){
			isNext = 0;
		}
		
		PageUtil<ReachBall> page = new PageUtil<ReachBall>();
		page.setCount(count);
		page.setIsNext(isNext);
		page.setResults(reachBalls);
		
		return page;
	}
	
	@Override
	public ReachBall findById(String id) {
		return reachBallMapper.findById(id, Constants.download);
	}
	
	@Override
	public boolean isJoin(JoinReach joinReach) {
		int count = joinReachMapper.findCount(joinReach);
		if(count > 0){
			return true;
		}
		return false;
	}
	
	@Override
	@Transactional
	public void join(JoinReach joinReach) {
		joinReachMapper.insert(joinReach);
	}
	
	@Override
	public PageUtil<JoinReach> joinList(String vipId,String beginTime, int pageNo, int pageSize) {
		int pageIndex = (pageNo -1 ) * pageSize;
		
		JoinReach joinReach = new JoinReach();
		
		joinReach.setVipId(vipId);
		joinReach.setBeginTime(beginTime);
		joinReach.setPageIndex(pageIndex);
		joinReach.setPageSize(pageSize);
		
		int count = joinReachMapper.findCount(joinReach);
		List<JoinReach> joinReachs = joinReachMapper.findPage(joinReach);
		
		int isNext = 1;
		if(count <= (pageIndex + pageSize)){
			isNext = 0;
		}
		
		PageUtil<JoinReach> page = new PageUtil<JoinReach>();
		page.setCount(count);
		page.setIsNext(isNext);
		page.setResults(joinReachs);
		
		return page;
	}

	@Override
	public List<JoinReach> findJoinReachByRdId(String rbId) {
		return joinReachMapper.findJoinReachByRdId(rbId,Constants.download);
	}

}
