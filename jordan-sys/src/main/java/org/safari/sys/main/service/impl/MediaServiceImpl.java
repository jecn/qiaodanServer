package org.safari.sys.main.service.impl;

import java.util.List;

import org.safari.sys.main.entity.Media;
import org.safari.sys.main.mapper.MediaMapper;
import org.safari.sys.main.service.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MediaServiceImpl implements MediaService {
	
	@Autowired
	private MediaMapper mediaMapper;

	@Override
	@Transactional
	public void insert(Media media) {
		mediaMapper.insert(media);
	}
	
	@Override
	@Transactional
	public void insertBatch(List<Media> medias) {
		mediaMapper.insertBatch(medias);
	}

	@Override
	public Media queryById(String id) {
		return mediaMapper.findById(id);
	}

	@Override
	public List<Media> queryByIds(String ids) {
		return mediaMapper.queryByIds(ids.split(","));
	}


}
