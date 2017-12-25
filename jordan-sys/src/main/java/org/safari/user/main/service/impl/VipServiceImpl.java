package org.safari.user.main.service.impl;

import org.safari.pub.platform.global.Constants;
import org.safari.user.main.entity.Vip;
import org.safari.user.main.mapper.VipMapper;
import org.safari.user.main.service.VipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VipServiceImpl implements VipService {

	@Autowired
	private VipMapper vipMapper;

	@Override
	public Vip findById(String id) {
		return vipMapper.findById(id,Constants.download);
	}

	@Override
	public Vip findByUnique(String key, String value) {
		return vipMapper.findByUnique(key, value, Constants.download);
	}
	
	@Override
	public Vip findByOpenIdAndType(String openId, String type) {
		return vipMapper.findByOpenIdAndType(openId, type,Constants.download);
	}

	@Override
	@Transactional
	public void register(Vip vip) {
		vipMapper.register(vip);
	}

	@Override
	public Vip login(String account, String password) {
		Vip vip = vipMapper.findByUsernameAndPassword(account,password,Constants.download);
		if(null == vip){
			vip = vipMapper.findByMobileAndPassword(account,password,Constants.download);
			if(null == vip){
				vip = vipMapper.findByEmailAndPassword(account,password,Constants.download);
			}
		}
		return vip;
	}

	@Override
	@Transactional
	public void updateSingle(String key, String value, String id) {
		vipMapper.updateSingle(key, value, id);
	}

	@Override
	@Transactional
	public void update(Vip vip) {
		vipMapper.modify(vip);
	}

	@Override
	@Transactional
	public void updatePassword(String password, String id) {
		vipMapper.updatePassword(password,id);
	}

	@Override
	public void updateBind(String key, String value, String id) {
		vipMapper.updateBind(key, value, id);
	}
	
	
}
