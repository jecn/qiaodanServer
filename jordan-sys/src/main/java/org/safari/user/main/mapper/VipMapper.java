package org.safari.user.main.mapper;

import org.apache.ibatis.annotations.Param;
import org.safari.user.main.entity.Vip;

public interface VipMapper{

	public Vip findById(@Param("id")String id,@Param("download") String download);

	public void register(Vip vip);
	
	public Vip findByUnique(@Param("key")String key, @Param("value")String value,
			@Param("download") String download);

	public Vip findByOpenIdAndType(@Param("openId")String openId, @Param("type")String type,
			@Param("download") String download);

	public Vip findByUsernameAndPassword(@Param("username")String username, @Param("password")String password,
			@Param("download") String download);

	public Vip findByMobileAndPassword(@Param("mobile")String mobile, @Param("password")String password,
			@Param("download") String download);

	public Vip findByEmailAndPassword(@Param("email")String email, @Param("password")String password,
			@Param("download") String download);

	public void updateSingle(@Param("key")String key, @Param("value")String value, @Param("id")String id);

	public void updateBind(@Param("key")String key, @Param("value")String value, @Param("id")String id);
	
	public void update(Vip vip);
	
	public void modify(Vip vip);

	public void updatePassword(@Param("password")String password,@Param("id") String id);


}
