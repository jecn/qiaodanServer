package org.safari.sport.main.service.impl;

import java.util.List;

import org.safari.pub.platform.global.Constants;
import org.safari.pub.utils.page.PageUtil;
import org.safari.sport.main.entity.Train;
import org.safari.sport.main.entity.TrainDict;
import org.safari.sport.main.entity.TrainSave;
import org.safari.sport.main.mapper.TrainDictMapper;
import org.safari.sport.main.mapper.TrainMapper;
import org.safari.sport.main.mapper.TrainSaveMapper;
import org.safari.sport.main.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TrainServiceImpl implements TrainService {
	
	@Autowired
	private TrainDictMapper trainDictMapper;
	
	@Autowired
	private TrainMapper trainMapper;
	
	@Autowired
	private TrainSaveMapper trainSaveMapper;
	
	@Override
	public List<TrainDict> findDict() {
		return trainDictMapper.findAll(Constants.FLAG_NO);
	}


	@Override
	public Train findById(String id) {
		return trainMapper.findById(id);
	}

	@Override
	public PageUtil<Train> findTrainPage(Train train) {
		
		int count = trainMapper.findCount(train);
		List<Train> trains = trainMapper.findPage(train);
		
		int isNext = 1;
		if(count <= (train.getPageIndex() + train.getPageSize())){
			isNext = 0;
		}
		
		PageUtil<Train> page = new PageUtil<Train>();
		page.setCount(count);
		page.setIsNext(isNext);
		page.setResults(trains);
		
		return page;
	}


	@Override
	@Transactional
	public void upload(TrainSave trainSave) {
		trainSaveMapper.insert(trainSave);
	}

	@Override
	public PageUtil<TrainSave> findTrainSavePage(TrainSave trainSave) {
		
		int count = trainSaveMapper.findCount(trainSave);
		List<TrainSave> trains = trainSaveMapper.findPage(trainSave);
		
		int isNext = 1;
		if(count <= (trainSave.getPageIndex() + trainSave.getPageSize())){
			isNext = 0;
		}
		
		PageUtil<TrainSave> page = new PageUtil<TrainSave>();
		page.setCount(count);
		page.setIsNext(isNext);
		page.setResults(trains);
		
		return page;
	}


	@Override
	public void deleteTrainSave(String ids) {
		trainSaveMapper.deleteByIds(ids.split(","), Constants.FLAG_YES);
	}


	@Override
	public Train findNewByTitle(String title) {
		Train train = new Train();
		train.setTitle(title);
		return trainMapper.findNewByTitle(train);
	}


	@Override
	public void batchCount(List<Train> trains) {
		trainMapper.batchCount(trains);
	}
}
