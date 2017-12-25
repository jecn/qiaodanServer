package org.safari.sys.main.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.safari.sys.main.entity.Media;

public interface MediaMapper {

    public int insert(Media media);
    
    public void insertBatch(@Param("medias")List<Media> medias);

    public Media findById(String id);

	public List<Media> queryByIds(@Param("idList")String[] idList);

	
}