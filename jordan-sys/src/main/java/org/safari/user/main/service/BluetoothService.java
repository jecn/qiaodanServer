package org.safari.user.main.service;

import java.util.List;

import org.safari.user.main.entity.Bluetooth;

public interface BluetoothService {

	public void bind(Bluetooth bluetooth);
	
	public void unbund(String ids);
	
	public Bluetooth findById(String id);
	
	public List<Bluetooth> findByVipId(String vipId);

	public Bluetooth findByVipIdAndMac(String vipId, String mac);

	public void update(String id, String name);
}
