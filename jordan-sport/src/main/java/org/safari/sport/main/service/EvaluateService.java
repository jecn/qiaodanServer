package org.safari.sport.main.service;

import org.safari.sport.main.entity.Evaluate;

public interface EvaluateService {

	public Evaluate findByVipId(String vipId);
	
	public void saveOrUpdate(Evaluate evaluate);

}
