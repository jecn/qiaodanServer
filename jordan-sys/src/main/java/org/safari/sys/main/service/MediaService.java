package org.safari.sys.main.service;

import java.util.List;

import org.safari.sys.main.entity.Media;

public interface MediaService {

	public void insert(Media media);

	public void insertBatch(List<Media> medias);
	
	public Media queryById(String id);

	public List<Media> queryByIds(String ids);


}
