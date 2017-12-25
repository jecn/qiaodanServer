package org.safari.sport.main.service.impl;

import org.safari.pub.utils.UUIDUtil;
import org.safari.sport.main.entity.Evaluate;
import org.safari.sport.main.mapper.EvaluateMapper;
import org.safari.sport.main.service.EvaluateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EvaluateServiceImpl implements EvaluateService {

	@Autowired
	private EvaluateMapper evaluateMapper;

	@Override
	public Evaluate findByVipId(String vipId) {
		return evaluateMapper.findByVipId(vipId);
	}

	@Override
	@Transactional
	public void saveOrUpdate(Evaluate evaluate) {
		Evaluate eval = evaluateMapper.findByVipId(evaluate.getVipId());
		if(null != eval){
			evaluateMapper.modify(evaluate);
		}else{
			evaluate.setId(UUIDUtil.generate());
			evaluateMapper.insert(evaluate);
		}
	}
}
