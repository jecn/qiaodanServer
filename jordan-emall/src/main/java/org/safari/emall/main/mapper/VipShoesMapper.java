package org.safari.emall.main.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.safari.emall.main.entity.VipShoes;

public interface VipShoesMapper {

    public int insert(VipShoes vipShoes);

    public int deleteById(String id);

    public int deleteByIds(String [] idList);

    public VipShoes findById(@Param("id")String id,@Param("download")String download);

    public List<VipShoes> findByVipId(@Param("vipId")String vipId, @Param("download")String download, 
    		@Param("deleteNo")String deleteNo);

	public int findByVipIdAndCode(@Param("vipId")String vipId, @Param("code")String code, 
			@Param("deleteNo")String deleteNo);

	public void unbund(@Param("idList")String[] idList,@Param("deleteYes")String deleteYes);

}