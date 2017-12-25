package org.safari.sport.main.service.impl;

import java.util.List;

import org.safari.pub.platform.global.Constants;
import org.safari.pub.utils.PropertiesUtil;
import org.safari.pub.utils.page.PageUtil;
import org.safari.sport.main.entity.Court;
import org.safari.sport.main.mapper.CourtMapper;
import org.safari.sport.main.service.CourtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CourtServiceImpl implements CourtService {

	@Autowired
	private CourtMapper courtMapper;
	
	private double radii = PropertiesUtil.getDouble("earth.radii"); //地球半径
	private double offset = PropertiesUtil.getDouble("offset.dist"); //偏差

	@Override
	@Transactional
	public void upload(Court court) {
		String $lng = court.getLongitude();   //经度
		String $lat = court.getLatitude();  //纬度
		
		boolean flag = findPosition($lng,$lat);
		if(!flag){
			courtMapper.insert(court);
		}
	}
	
	@Override
	public PageUtil<Court> findPage(Court court) {
		int count = courtMapper.findCount(court);
		List<Court> trains = courtMapper.findPage(court);
		
		int isNext = 1;
		if(count <= (court.getPageIndex() + court.getPageSize())){
			isNext = 0;
		}
		
		PageUtil<Court> page = new PageUtil<Court>();
		page.setCount(count);
		page.setIsNext(isNext);
		page.setResults(trains);
		
		return page;
	}
	
	@Override
	public Court findById(String id) {
		return courtMapper.findById(id,Constants.download);
	}

	/**
	 * 查找指定经度附件是否存在误差允许的点
	 * @param $lng 经度
	 * @param $lat 纬度
	 * @return boolean
	 */
	private boolean findPosition(String $lng, String $lat) {
		double longitude = Double.parseDouble($lng);
		double latitude = Double.parseDouble($lat);
		
        double dlng =  2*Math.asin(Math.sin(offset/(2*radii))/Math.cos(latitude*Math.PI/180));  
        dlng = dlng*180/Math.PI;//角度转为弧度  
        
        double dlat = offset/radii;  
        dlat = dlat*180/Math.PI;  
        
       
        double minlng = longitude -dlng;  //最小经度
        double maxlng = longitude + dlng; //最大经度
        
        double minlat =latitude-dlat;    //最小纬度
        double maxlat = latitude+dlat;   //最大纬度
        
        int count = courtMapper.findPosition(minlng,maxlng,minlat,maxlat);
        if(count > 0){
        	return true;
        }
		return false;
	}

}
