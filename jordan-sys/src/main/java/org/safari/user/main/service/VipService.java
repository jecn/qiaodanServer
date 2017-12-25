package org.safari.user.main.service;

import org.safari.user.main.entity.Vip;

public interface VipService {
	
	public Vip findById(String id);
	
	public Vip findByUnique(String key, String value);
	
	public Vip findByOpenIdAndType(String openId, String type);
	
	public void register(Vip vip);
	
	public Vip login(String account, String password);
	
	public void updateSingle(String key, String value,String id);
	
	public void update(Vip vip);

	public void updatePassword(String password, String id);

	public void updateBind(String key, String value, String id);
	
}
