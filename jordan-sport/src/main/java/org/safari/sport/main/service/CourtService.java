package org.safari.sport.main.service;

import org.safari.pub.utils.page.PageUtil;
import org.safari.sport.main.entity.Court;

public interface CourtService {

	public void upload(Court court);

	public PageUtil<Court> findPage(Court court);

	public Court findById(String id);

}
