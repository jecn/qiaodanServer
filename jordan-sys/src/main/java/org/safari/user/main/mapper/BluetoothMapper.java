package org.safari.user.main.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.safari.user.main.entity.Bluetooth;

public interface BluetoothMapper {

    public int bind(Bluetooth bluetooth);

    public int unbund(@Param("idList")String[] idList);

    public Bluetooth findById(String id);

    public List<Bluetooth> findByVipId(String vipId);

	public Bluetooth findByVipIdAndMac(@Param("vipId")String vipId, @Param("mac")String mac);

	public void update(@Param("id")String id, @Param("name")String name);
}