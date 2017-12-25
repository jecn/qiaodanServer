package org.safari.emall.main.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.safari.emall.main.entity.Shoes;

public interface ShoesMapper {

	public int insert(Shoes shoes);

    public int deleteById(@Param("id")String id, @Param("deleteYes")String deleteYes);

    public int deleteByIds(@Param("idList")String[] idList, @Param("deleteYes")String deleteYes);

    public int modify(Shoes shoes);

    public Shoes findById(@Param("id")String id, @Param("download")String download);

    public int findCount(Shoes shoes);

    public List<Shoes> findPage(Shoes shoes);
    
    public List<Shoes> findAllType(@Param("deleteNo")String deleteNo,@Param("ableYes")String ableYes );

	public List<Shoes> findByPosition(@Param("position")String position,@Param("download")String download,
			@Param("deleteNo")String deleteNo,@Param("ableYes")String ableYes);
			

	public List<Shoes> findGroupPostion(@Param("download")String download,@Param("deleteNo")String deleteNo,
			@Param("ableYes")String ableYes);

}