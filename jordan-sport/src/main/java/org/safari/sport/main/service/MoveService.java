package org.safari.sport.main.service;

import java.util.List;

import org.safari.pub.utils.page.PageUtil;
import org.safari.sport.main.entity.Move;

public interface MoveService {
	
	public Move findById(String id);

	public void upload(Move move);
	
	public PageUtil<Move> findPageByVipAndCycle(String vipId,String beginTime,
			String endTime, int pageNo, int pageSize);

	public Move findRadar();

	public Move findRadarByVipId(String vipId);

	public void insertBatch(List<Move> moves);

	public Move findByVipIdAndBeginTime(String vipId, String beginTime);

	public int findAllByVipId(String vipId);
}
