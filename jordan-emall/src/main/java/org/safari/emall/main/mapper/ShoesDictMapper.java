package org.safari.emall.main.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.safari.emall.main.entity.ShoesDict;

public interface ShoesDictMapper {

    public int insert(ShoesDict shoesDict);

    public int deleteById(@Param("id")String id, @Param("deleteYes")String deleteYes);

    public int deleteByIds(@Param("idList")String[] idList, @Param("deleteYes")String deleteYes);

    public int modify(ShoesDict shoesDict);

    public ShoesDict findById(@Param("id")String id, @Param("download")String download);

    public int findCount(ShoesDict shoesDict);

    public List<ShoesDict> findPage(ShoesDict shoesDict);

    public List<ShoesDict> findByType(@Param("type")String type, @Param("deleteNo")String deleteNo,
    		@Param("ableYes")String ableYes);

	public List<ShoesDict> findAllType(@Param("deleteNo")String deleteNo,@Param("ableYes")String ableYes);
    		
}