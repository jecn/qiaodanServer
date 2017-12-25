package org.safari.emall.main.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.safari.emall.main.entity.Shoes;
import org.safari.emall.main.entity.ShoesDict;
import org.safari.emall.main.entity.ShoesPicture;
import org.safari.emall.main.entity.ShoesRecm;
import org.safari.emall.main.entity.VipShoes;
import org.safari.emall.main.mapper.ShoesDictMapper;
import org.safari.emall.main.mapper.ShoesMapper;
import org.safari.emall.main.mapper.ShoesPictureMapper;
import org.safari.emall.main.mapper.ShoesRecmMapper;
import org.safari.emall.main.mapper.VipShoesMapper;
import org.safari.emall.main.service.ShoesService;
import org.safari.pub.platform.global.Constants;
import org.safari.pub.utils.StringUtil;
import org.safari.pub.utils.page.PageUtil;
import org.safari.user.main.entity.Vip;
import org.safari.user.main.mapper.VipMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ShoesServiceImpl implements ShoesService {

	@Autowired
	private VipShoesMapper vipShoesMapper;
	
	@Autowired
	private VipMapper vipMapper;
	
	@Autowired
	private ShoesMapper shoesMapper;
	
	@Autowired
	private ShoesDictMapper shoesDictMapper;
	
	@Autowired
	private ShoesRecmMapper shoesRecmMapper;
	
	@Autowired
	private ShoesPictureMapper shoesPictureMapper;
	
	
	@Override
	public boolean findByVipIdAndCode(String vipId, String code) {
		int count = vipShoesMapper.findByVipIdAndCode(vipId,code,Constants.FLAG_NO);
		if(count > 0 ){
			return true;
		}
		return false;
	}

	@Override
	@Transactional
	public void bind(VipShoes vipShoes) {
		vipShoesMapper.insert(vipShoes);
	}

	@Override
	public List<VipShoes> findBox(String vipId) {
		return vipShoesMapper.findByVipId(vipId, Constants.download, Constants.FLAG_NO);
	}

	@Override
	public List<Shoes> findReco(String vipId) {
		List<Shoes> shoeses = new ArrayList<Shoes>();
		
		Vip info = vipMapper.findById(vipId, Constants.download);
		String position = info.getPosition();
		
		if(!StringUtil.isEmpty(position)){
			shoeses = shoesMapper.findByPosition(position, Constants.download, Constants.FLAG_NO,Constants.FLAG_YES);
		}else{
			//若没有具体的位置则每个位置都推荐一条
			shoeses = shoesMapper.findGroupPostion(Constants.download,Constants.FLAG_NO,Constants.FLAG_YES);
		}
		return shoeses;
	}

	@Override
	public PageUtil<Shoes> findPage(Shoes shoes, int pageNo, int pageSize) {
		int pageIndex = (pageNo -1 ) * pageSize;
		
		shoes.setPageIndex(pageIndex);
		shoes.setPageSize(pageSize);
		
		int count = shoesMapper.findCount(shoes);
		List<Shoes> shoeses = shoesMapper.findPage(shoes);
		
		int isNext = 1;
		if(count <= (pageIndex + pageSize)){
			isNext = 0;
		}
		
		PageUtil<Shoes> page = new PageUtil<Shoes>();
		page.setCount(count);
		page.setIsNext(isNext);
		page.setResults(shoeses);
		
		return page;
	}

	@Override
	public Shoes findById(String id) {
		return shoesMapper.findById(id, Constants.download);
	}

	@Override
	public List<ShoesDict> findDict() {
		return shoesDictMapper.findAllType(Constants.FLAG_NO,Constants.FLAG_YES);
	}
	
	@Override
	public List<ShoesDict> findDictByType(String type) {
		return shoesDictMapper.findByType(type, Constants.FLAG_NO,Constants.FLAG_YES);
	}

	@Override
	public List<ShoesPicture> findShoesPictureByShoesId(String shoesId) {
		return shoesPictureMapper.findByShoesId(shoesId);
	}

	@Override
	@Transactional
	public void unbund(String ids) {
		vipShoesMapper.unbund(ids.split(","),Constants.FLAG_YES);
	}

	@Override
	public List<ShoesRecm> findReco(ShoesRecm shoesRecm) {
		return shoesRecmMapper.findAll(shoesRecm);
	}

}
