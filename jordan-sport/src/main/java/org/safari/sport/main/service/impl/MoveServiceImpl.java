package org.safari.sport.main.service.impl;

import java.util.List;

import org.safari.pub.platform.global.Constants;
import org.safari.pub.utils.page.PageUtil;
import org.safari.sport.main.entity.Move;
import org.safari.sport.main.mapper.MoveMapper;
import org.safari.sport.main.service.MoveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MoveServiceImpl implements MoveService {

	@Autowired
	private MoveMapper moveMapper;
	
	@Override
	public Move findById(String id) {
		return moveMapper.findById(id, Constants.download);
	}

	@Override
	@Transactional
	public void upload(Move move) {
		moveMapper.insert(move);
	}
	
	@Override
	@Transactional
	public void insertBatch(List<Move> moves) {
		moveMapper.insertBatch(moves);
	}

	@Override
	public PageUtil<Move> findPageByVipAndCycle(String vipId, String beginTime,
			String endTime, int pageNo, int pageSize) {
		int pageIndex = (pageNo -1 ) * pageSize;
		
		Move move = new Move();
		
		move.setVipId(vipId);
		move.setBeginTime(beginTime);
		move.setEndTime(endTime);
		move.setPageIndex(pageIndex);
		move.setPageSize(pageSize);
		
		int count = moveMapper.findCountByVipIdAndCycle(move);
		List<Move> moves = moveMapper.findListByVipIdAndCycle(move);
		
		int isNext = 1;
		if(count <= (pageIndex + pageSize)){
			isNext = 0;
		}
		
		PageUtil<Move> page = new PageUtil<Move>();
		page.setCount(count);
		page.setIsNext(isNext);
		page.setResults(moves);
		
		return page;
	}

	@Override
	public Move findRadar() {
		return moveMapper.findRadar();
	}

	@Override
	public Move findRadarByVipId(String vipId) {
		return moveMapper.findRadarByVipId(vipId);
	}

	@Override
	public Move findByVipIdAndBeginTime(String vipId, String beginTime) {
		Move move = new Move();
		move.setVipId(vipId);
		move.setBeginTime(beginTime);
		return moveMapper.findByVipIdAndBeginTime(move);
	}

	@Override
	public int findAllByVipId(String vipId) {
		return moveMapper.findAllByVipId(vipId);
	}

	
	
	
}
