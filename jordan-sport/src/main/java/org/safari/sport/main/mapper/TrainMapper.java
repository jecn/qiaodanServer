package org.safari.sport.main.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.safari.sport.main.entity.Train;

public interface TrainMapper {

    public int insert(Train train);

    public int deleteById(String id);

    public int deleteByIds(List<String> idList);

    public int modify(Train train);

    public Train findById(String id);

    public int findCount(Train train);

    public List<Train> findPage(Train train);

	public Train findNewByTitle(Train train);

	public void batchCount(@Param("trains")List<Train> trains);

}