package org.safari.emall.main.service;

import java.util.List;

import org.safari.emall.main.entity.Shoes;
import org.safari.emall.main.entity.ShoesDict;
import org.safari.emall.main.entity.ShoesPicture;
import org.safari.emall.main.entity.ShoesRecm;
import org.safari.emall.main.entity.VipShoes;
import org.safari.pub.utils.page.PageUtil;

public interface ShoesService {

	/**
	 * 
	 * @param id
	 * @param code
	 * @return boolean
	 */
	public boolean findByVipIdAndCode(String vipId, String code);
	
	/**
	 * 绑定球鞋
	 * @param vipShoes
	 */
	public void bind(VipShoes vipShoes);

	/**
	 * 会员鞋柜
	 * @param id
	 * @return List<VipShoes>
	 */
	public List<VipShoes> findBox(String vipId);

	/**
	 * 球鞋推荐
	 * @param vipId
	 * @return
	 */
	public List<Shoes> findReco(String vipId);

	/**
	 * 分页查询
	 * @param shoes
	 * @param pageSize 
	 * @param pageNo 
	 * @return PageUtil<Shoes>
	 */
	public PageUtil<Shoes> findPage(Shoes shoes, int pageNo, int pageSize);

	/**
	 * @param id
	 * @return Shoes
	 */
	public Shoes findById(String id);

	/**
	 * @return List<ShoesDict>
	 */
	public List<ShoesDict> findDict( );

	/**
	 * 球鞋照片
	 * @param id
	 * @return List<ShoesPicture>
	 */
	public List<ShoesPicture> findShoesPictureByShoesId(String shoesId);

	/**
	 * @param type
	 * @return List<ShoesDict>
	 */
	public List<ShoesDict> findDictByType(String type);

	/**
	 * 解绑球鞋
	 * @param ids
	 */
	public void unbund(String ids);

	/**
	 * 球鞋推荐
	 * @return
	 */
	public List<ShoesRecm> findReco(ShoesRecm shoesRecm);


}
