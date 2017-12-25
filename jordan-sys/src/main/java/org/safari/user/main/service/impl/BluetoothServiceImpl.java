package org.safari.user.main.service.impl;

import java.util.List;

import org.safari.user.main.entity.Bluetooth;
import org.safari.user.main.mapper.BluetoothMapper;
import org.safari.user.main.service.BluetoothService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BluetoothServiceImpl implements BluetoothService {

	@Autowired
	private BluetoothMapper  bluetoothMapper;

	@Override
	@Transactional
	public void bind(Bluetooth bluetooth) {
		bluetoothMapper.bind(bluetooth);
	}

	@Override
	@Transactional
	public void unbund(String ids) {
		bluetoothMapper.unbund(ids.split(","));
	}

	@Override
	public Bluetooth findById(String id) {
		return bluetoothMapper.findById(id);
	}

	@Override
	public List<Bluetooth> findByVipId(String vipId) {
		return bluetoothMapper.findByVipId(vipId);
	}

	@Override
	public Bluetooth findByVipIdAndMac(String vipId, String mac) {
		return bluetoothMapper.findByVipIdAndMac(vipId, mac) ;
	}

	@Override
	@Transactional
	public void update(String id, String name) {
		bluetoothMapper.update(id,name);
	}
	
}
