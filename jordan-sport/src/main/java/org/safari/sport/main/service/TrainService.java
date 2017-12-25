package org.safari.sport.main.service;

import java.util.List;

import org.safari.pub.utils.page.PageUtil;
import org.safari.sport.main.entity.Train;
import org.safari.sport.main.entity.TrainDict;
import org.safari.sport.main.entity.TrainSave;

public interface TrainService {
	
	public List<TrainDict> findDict();

	public PageUtil<Train> findTrainPage(Train train);

	public Train findById(String id);

	public void upload(TrainSave trainSave);
	
	public PageUtil<TrainSave> findTrainSavePage(TrainSave trainSave);

	public void deleteTrainSave(String ids);

	public Train findNewByTitle(String title);

	public void batchCount(List<Train> trains);

}
