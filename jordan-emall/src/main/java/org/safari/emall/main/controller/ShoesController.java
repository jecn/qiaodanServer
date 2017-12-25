package org.safari.emall.main.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.safari.emall.main.entity.Shoes;
import org.safari.emall.main.entity.ShoesDict;
import org.safari.emall.main.entity.ShoesPicture;
import org.safari.emall.main.entity.ShoesRecm;
import org.safari.emall.main.entity.VipShoes;
import org.safari.emall.main.protocol.ShoesPro.ShoesBind;
import org.safari.emall.main.protocol.ShoesPro.ShoesDetail;
import org.safari.emall.main.protocol.ShoesPro.ShoesPage;
import org.safari.emall.main.protocol.ShoesPro.ShoesUnbund;
import org.safari.emall.main.service.ShoesService;
import org.safari.pub.platform.aop.annotation.SafariMethod;
import org.safari.pub.platform.global.Constants;
import org.safari.pub.platform.global.RespCode;
import org.safari.pub.platform.protocol.ReqProtocol;
import org.safari.pub.platform.protocol.SafariResp;
import org.safari.pub.platform.validator.JSONValidator;
import org.safari.pub.platform.web.entity.ReqDomain;
import org.safari.pub.utils.StringUtil;
import org.safari.pub.utils.TokenUtil;
import org.safari.pub.utils.UUIDUtil;
import org.safari.pub.utils.page.PageUtil;
import org.safari.user.main.entity.Vip;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

@Controller
@RequestMapping("v0/emall/shoes")
public class ShoesController {
	
	protected Logger LOG = LoggerFactory.getLogger(ShoesController.class);
	
	@Autowired
	private ShoesService shoesService;
	
	@ResponseBody
	@RequestMapping("bind")
	@SafariMethod(checkToken=true, desc="绑定球鞋",submit="POST")
	public Object bind(ReqProtocol reqProtocol) {

		String request = null;
		JSONObject reqJson = null;
		ReqDomain reqDomain = null;
		SafariResp<?> safariResp = null;
		try {
			request = reqProtocol.getInfoJson();
			reqJson = JSONObject.fromObject(request);
			reqDomain = (ReqDomain) JSONObject.toBean(reqJson, ReqDomain.class);
		} catch (Exception e) {
			safariResp = SafariResp.getInstance(RespCode.$1001, RespCode
					.getRspCode(RespCode.$1001).desc());
			LOG.error("bind>>>", e);
			e.printStackTrace();
		}

		try {
			ShoesBind pro = (ShoesBind) JSONObject
					.toBean(reqDomain.getMain(), ShoesBind.class);
			Object obj = new JSONValidator().valid(pro);
			if (null == obj) {
				String token = reqDomain.getBiz().getToken();
				String subject = TokenUtil.parse(token, Constants.TOKEN_KEY);
				Vip info = (Vip) JSON.parseObject(subject, Vip.class);
				
				String type = pro.getType();  //绑定方式  1 定向绑定 2 自定义绑定
 				if("1".equals(type)){
 					Shoes shoes = shoesService.findById(pro.getShoesId());
 					if(null == shoes){
 						safariResp = SafariResp.getInstance(RespCode.$1006, RespCode
 								.getRspCode(RespCode.$1006).desc());
 						return safariResp;
 					}
 					pro.setCode(shoes.getCode());
 					pro.setColor(shoes.getColor());
 					pro.setStyle(shoes.getStyle());
 					pro.setPrice(shoes.getPrice());
 					if(StringUtil.isEmpty(pro.getName())){
 						pro.setName(shoes.getName());
 					}
 				}
				
				//查询是否绑定过相同产品
				if(shoesService.findByVipIdAndCode(info.getId(),pro.getCode())){
					safariResp = SafariResp.getInstance(RespCode.$1020, RespCode
							.getRspCode(RespCode.$1020).desc());
					return safariResp;
				}
				
				VipShoes vipShoes = new VipShoes();
				vipShoes.setId(UUIDUtil.generate());
				vipShoes.setVipId(info.getId());
				
				convert(pro,vipShoes);
				
				shoesService.bind(vipShoes);
				
				safariResp = SafariResp.getInstance();
			} else {
				return obj;
			}
		} catch (Exception e) {
			safariResp = SafariResp.getInstance(RespCode.$1000, RespCode
					.getRspCode(RespCode.$1000).desc());
			LOG.error("bind>>>", e);
			e.printStackTrace();
		}
		
		return safariResp;
	}
	
	@ResponseBody
	@RequestMapping("box")
	@SafariMethod(checkToken=true, desc="会员鞋柜",submit="POST")
	public Object box(ReqProtocol reqProtocol) {

		String request = null;
		JSONObject reqJson = null;
		ReqDomain reqDomain = null;
		SafariResp<?> safariResp = null;
		try {
			request = reqProtocol.getInfoJson();
			reqJson = JSONObject.fromObject(request);
			reqDomain = (ReqDomain) JSONObject.toBean(reqJson, ReqDomain.class);
		} catch (Exception e) {
			safariResp = SafariResp.getInstance(RespCode.$1001, RespCode
					.getRspCode(RespCode.$1001).desc());
			LOG.error("box>>>", e);
			e.printStackTrace();
		}

		try {
			String token = reqDomain.getBiz().getToken();
			String subject = TokenUtil.parse(token, Constants.TOKEN_KEY);
			Vip info = (Vip) JSON.parseObject(subject, Vip.class);
			
			List<VipShoes> box = shoesService.findBox(info.getId());
			
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("box", box);
			
			safariResp = SafariResp.getInstance(map);
		} catch (Exception e) {
			safariResp = SafariResp.getInstance(RespCode.$1000, RespCode
					.getRspCode(RespCode.$1000).desc());
			LOG.error("box>>>", e);
			e.printStackTrace();
		}
		
		return safariResp;
	}
	
	@ResponseBody
	@RequestMapping("unbund")
	@SafariMethod(checkToken=true, desc="解绑球鞋",submit="POST")
	public Object unbund(ReqProtocol reqProtocol) {

		String request = null;
		JSONObject reqJson = null;
		ReqDomain reqDomain = null;
		SafariResp<?> safariResp = null;
		try {
			request = reqProtocol.getInfoJson();
			reqJson = JSONObject.fromObject(request);
			reqDomain = (ReqDomain) JSONObject.toBean(reqJson, ReqDomain.class);
		} catch (Exception e) {
			safariResp = SafariResp.getInstance(RespCode.$1001, RespCode
					.getRspCode(RespCode.$1001).desc());
			LOG.error("unbund>>>", e);
			e.printStackTrace();
		}

		try {
			ShoesUnbund pro = (ShoesUnbund) JSONObject
					.toBean(reqDomain.getMain(), ShoesUnbund.class);
			Object obj = new JSONValidator().valid(pro);
			if (null == obj) {
				shoesService.unbund(pro.getIds());
				
				safariResp = SafariResp.getInstance();
			} else {
				return obj;
			}
		} catch (Exception e) {
			safariResp = SafariResp.getInstance(RespCode.$1000, RespCode
					.getRspCode(RespCode.$1000).desc());
			LOG.error("unbund>>>", e);
			e.printStackTrace();
		}
		
		return safariResp;
	}
	
	@ResponseBody
	@RequestMapping("reco")
	@SafariMethod(checkToken=true, desc="球鞋推荐",submit="POST")
	public Object reco(ReqProtocol reqProtocol) {

		String request = null;
		JSONObject reqJson = null;
		ReqDomain reqDomain = null;
		SafariResp<?> safariResp = null;
		try {
			request = reqProtocol.getInfoJson();
			reqJson = JSONObject.fromObject(request);
			reqDomain = (ReqDomain) JSONObject.toBean(reqJson, ReqDomain.class);
		} catch (Exception e) {
			safariResp = SafariResp.getInstance(RespCode.$1001, RespCode
					.getRspCode(RespCode.$1001).desc());
			LOG.error("reco>>>", e);
			e.printStackTrace();
		}

		try {
			/*String token = reqDomain.getBiz().getToken();
			String subject = TokenUtil.parse(token, Constants.TOKEN_KEY);
			Vip info = (Vip) JSON.parseObject(subject, Vip.class);
			
			List<Shoes> recos = shoesService.findReco(info.getId());*/
			
			List<ShoesRecm> recos = shoesService.findReco(new ShoesRecm());
			
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("recos", recos);
			
			safariResp = SafariResp.getInstance(map);
		} catch (Exception e) {
			safariResp = SafariResp.getInstance(RespCode.$1000, RespCode
					.getRspCode(RespCode.$1000).desc());
			LOG.error("reco>>>", e);
			e.printStackTrace();
		}
		
		return safariResp;
	}
	
	@ResponseBody
	@RequestMapping("filter")
	@SafariMethod(checkToken=true, desc="球鞋帅选条件",submit="POST")
	public Object filter(ReqProtocol reqProtocol) {
		SafariResp<?> safariResp = null;

		try {
			Map<String, Object> map = new HashMap<String, Object>();
			List<ShoesDict> shoesDicts = shoesService.findDict();
			
			for (ShoesDict shoesDict : shoesDicts) {
				String type = shoesDict.getType();
				map.put(type, shoesService.findDictByType(type));
			}
			
			safariResp = SafariResp.getInstance(map);
			
		} catch (Exception e) {
			safariResp = SafariResp.getInstance(RespCode.$1000, RespCode
					.getRspCode(RespCode.$1000).desc());
			LOG.error("filter>>>", e);
			e.printStackTrace();
		}
		
		return safariResp;
	}
	
	@ResponseBody
	@RequestMapping("list")
	@SafariMethod(checkToken=true, desc="球鞋列表",submit="POST")
	public Object list(ReqProtocol reqProtocol) {

		String request = null;
		JSONObject reqJson = null;
		ReqDomain reqDomain = null;
		SafariResp<?> safariResp = null;
		try {
			request = reqProtocol.getInfoJson();
			reqJson = JSONObject.fromObject(request);
			reqDomain = (ReqDomain) JSONObject.toBean(reqJson, ReqDomain.class);
		} catch (Exception e) {
			safariResp = SafariResp.getInstance(RespCode.$1001, RespCode
					.getRspCode(RespCode.$1001).desc());
			LOG.error("list>>>", e);
			e.printStackTrace();
		}

		try {
			ShoesPage pro = (ShoesPage) JSONObject
					.toBean(reqDomain.getMain(), ShoesPage.class);
			Object obj = new JSONValidator().valid(pro);
			if (null == obj) {
				Shoes shoes = new Shoes();
				convert(pro,shoes);
				
				PageUtil<Shoes> pager = shoesService.findPage(shoes,pro.getPageNo(),pro.getPageSize());
				
				safariResp = SafariResp.getInstance(pager);
			}else{
				return obj;
			}
		} catch (Exception e) {
			safariResp = SafariResp.getInstance(RespCode.$1000, RespCode
					.getRspCode(RespCode.$1000).desc());
			LOG.error("list>>>", e);
			e.printStackTrace();
		}
		
		return safariResp;
	}
	
	@ResponseBody
	@RequestMapping("detail")
	@SafariMethod(checkToken=true, desc="球鞋详情",submit="POST")
	public Object detail(ReqProtocol reqProtocol) {

		String request = null;
		JSONObject reqJson = null;
		ReqDomain reqDomain = null;
		SafariResp<?> safariResp = null;
		try {
			request = reqProtocol.getInfoJson();
			reqJson = JSONObject.fromObject(request);
			reqDomain = (ReqDomain) JSONObject.toBean(reqJson, ReqDomain.class);
		} catch (Exception e) {
			safariResp = SafariResp.getInstance(RespCode.$1001, RespCode
					.getRspCode(RespCode.$1001).desc());
			LOG.error("detail>>>", e);
			e.printStackTrace();
		}

		try {
			ShoesDetail pro = (ShoesDetail) JSONObject
					.toBean(reqDomain.getMain(), ShoesDetail.class);
			Object obj = new JSONValidator().valid(pro);
			if (null == obj) {
				Shoes shoes = shoesService.findById(pro.getId());
				
				List<ShoesPicture> pictures = shoesService.findShoesPictureByShoesId(pro.getId());
				
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("shoes", shoes);
				map.put("pictures", pictures);
				
				safariResp = SafariResp.getInstance(map);
			}else{
				return obj;
			}
		} catch (Exception e) {
			safariResp = SafariResp.getInstance(RespCode.$1000, RespCode
					.getRspCode(RespCode.$1000).desc());
			LOG.error("detail>>>", e);
			e.printStackTrace();
		}
		
		return safariResp;
	}
	
	private void convert(ShoesBind pro, VipShoes vipShoes) {
		vipShoes.setShoesId(pro.getShoesId());
		vipShoes.setBuyTime(pro.getBuyTime());
		vipShoes.setCode(pro.getCode());
		vipShoes.setColor(pro.getColor());
		vipShoes.setName(pro.getName());
		vipShoes.setPicture(pro.getPicture());
		vipShoes.setPrice(pro.getPrice());
		vipShoes.setSize(pro.getSize());
		vipShoes.setStyle(pro.getStyle());
	}


	private void convert(ShoesPage pro, Shoes shoes) {
		shoes.setColor(pro.getColor());
		shoes.setForPeople(pro.getForPeople());
		shoes.setForPosition(pro.getForPosition());
		shoes.setForSpace(pro.getForSpace());
		shoes.setFunction(pro.getFunction());
		shoes.setMarketTime(pro.getMarketTime());
		shoes.setMinPrice(pro.getMinPrice());
		shoes.setMaxprice(pro.getMaxprice());
		shoes.setSize(pro.getSize());
		shoes.setStyle(pro.getStyle());
		shoes.setStyleNumber(pro.getStyleNumber());
	}

}
