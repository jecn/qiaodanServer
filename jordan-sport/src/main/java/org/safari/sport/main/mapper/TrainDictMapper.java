package org.safari.sport.main.mapper;

import java.util.List;
import org.safari.sport.main.entity.TrainDict;

public interface TrainDictMapper {

    public int insert(TrainDict trainDict);

    public int deleteById(String id);

    public int deleteByIds(List<String> idList);

    public int modify(TrainDict trainDict);

    public TrainDict findById(String id);

    public int findCount(TrainDict trainDict);

    public List<TrainDict> findPage(TrainDict trainDict);

	public List<TrainDict> findAll(String delFlag);
}