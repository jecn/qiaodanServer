package org.safari.sport.main.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.safari.sport.main.entity.TrainSave;

public interface TrainSaveMapper {

    public int insert(TrainSave trainSave);

    public int deleteById(@Param("id")String[] id,@Param("deleteYes")String deleteYes);

    public int deleteByIds(@Param("idList")String[] idList,@Param("deleteYes")String deleteYes);

    public TrainSave findById(@Param("id")String id, @Param("download")String download);

    public int findCount(TrainSave trainSave);

    public List<TrainSave> findPage(TrainSave trainSave);

}