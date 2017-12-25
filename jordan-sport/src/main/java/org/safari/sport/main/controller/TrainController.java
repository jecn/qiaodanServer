package org.safari.sport.main.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.safari.pub.platform.aop.annotation.SafariMethod;
import org.safari.pub.platform.enums.DeviceEnum;
import org.safari.pub.platform.global.Constants;
import org.safari.pub.platform.global.RespCode;
import org.safari.pub.platform.protocol.ReqProtocol;
import org.safari.pub.platform.protocol.SafariResp;
import org.safari.pub.platform.validator.JSONValidator;
import org.safari.pub.platform.web.entity.ReqDomain;
import org.safari.pub.utils.TokenUtil;
import org.safari.pub.utils.UUIDUtil;
import org.safari.pub.utils.page.PageUtil;
import org.safari.sport.main.entity.Train;
import org.safari.sport.main.entity.TrainDict;
import org.safari.sport.main.entity.TrainSave;
import org.safari.sport.main.protocol.TrainPro.Read;
import org.safari.sport.main.protocol.TrainPro.Reads;
import org.safari.sport.main.protocol.TrainPro.TrainDel;
import org.safari.sport.main.protocol.TrainPro.TrainDetail;
import org.safari.sport.main.protocol.TrainPro.TrainPage;
import org.safari.sport.main.protocol.TrainPro.TrainSavePage;
import org.safari.sport.main.protocol.TrainPro.TrainUplaod;
import org.safari.sport.main.service.TrainService;
import org.safari.user.main.entity.Vip;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

@Controller
@RequestMapping("v0/sport/train")
public class TrainController {

	protected Logger LOG = LoggerFactory.getLogger(TrainController.class);
	
	@Autowired
	private TrainService trainService;
	
	@ResponseBody
	@RequestMapping("dict")
	@SafariMethod(checkToken=true, desc="训练字典",submit="POST")
	public Object dict(ReqProtocol reqProtocol) {
		SafariResp<?> safariResp = null;
		
		try {
				List<TrainDict> list = trainService.findDict();
				
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("dict", list);
				safariResp = SafariResp.getInstance(map);
		} catch (Exception e) {
			safariResp = SafariResp.getInstance(RespCode.$1000, RespCode
					.getRspCode(RespCode.$1000).desc());
			LOG.error("dict>>>", e);
			e.printStackTrace();
		}
		
		return safariResp;
	}
	
	@ResponseBody
	@RequestMapping("list")
	@SafariMethod(checkToken=true, desc="训练列表",submit="POST")
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
			TrainPage pro = (TrainPage) JSONObject
					.toBean(reqDomain.getMain(), TrainPage.class);
			Object obj = new JSONValidator().valid(pro);
			if (null == obj) {
				int pageNo = pro.getPageNo();
				int pageSize = pro.getPageSize();
				int pageIndex = (pageNo -1 ) * pageSize;
				
				Train train = new Train();
				train.setTdId(pro.getTdId());
				train.setType(pro.getType());
				train.setPosition(pro.getPosition());
				train.setPageIndex(pageIndex);
				train.setPageSize(pageSize);
				
				PageUtil<Train> pager = trainService.findTrainPage(train);
				
				safariResp = SafariResp.getInstance(pager);
			} else {
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
	@SafariMethod(checkToken=true, desc="训练详情",submit="POST")
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
			TrainDetail pro = (TrainDetail) JSONObject
					.toBean(reqDomain.getMain(), TrainDetail.class);
			Object obj = new JSONValidator().valid(pro);
			if (null == obj) {
				Train train = trainService.findById(pro.getId());
				
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("train", train);
				
				safariResp = SafariResp.getInstance(map);
			} else {
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
	
	@ResponseBody
	@RequestMapping("upload")
	@SafariMethod(checkToken=true, desc="训练保存",submit="POST")
	public Object upload(ReqProtocol reqProtocol) {

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
			LOG.error("upload>>>", e);
			e.printStackTrace();
		}

		try {
			TrainUplaod pro = (TrainUplaod) JSONObject
					.toBean(reqDomain.getMain(), TrainUplaod.class);
			Object obj = new JSONValidator().valid(pro);
			if (null == obj) {
				String token = reqDomain.getBiz().getToken();
				String subject = TokenUtil.parse(token, Constants.TOKEN_KEY);
				Vip info = (Vip) JSON.parseObject(subject, Vip.class);
				
				TrainSave trainSave = new TrainSave();
				trainSave.setId(UUIDUtil.generate());
				trainSave.setVipId(info.getId());
				trainSave.setMoveId(pro.getMoveId());
				trainSave.setType(pro.getType());
				trainSave.setTerminal(DeviceEnum.getValue(reqDomain.getBiz().getDeviceType()));
				trainSave.setPlatform(pro.getPlatform());
				trainSave.setSource(pro.getSource());
				trainSave.setSay(pro.getSay());
				trainSave.setImg(pro.getImg());
				trainSave.setUrl(pro.getUrl());
				
				trainService.upload(trainSave);
				
				safariResp = SafariResp.getInstance();
			} else {
				return obj;
			}
		} catch (Exception e) {
			safariResp = SafariResp.getInstance(RespCode.$1000, RespCode
					.getRspCode(RespCode.$1000).desc());
			LOG.error("upload>>>", e);
			e.printStackTrace();
		}
		
		return safariResp;
	}
	
	@ResponseBody
	@RequestMapping("saveList")
	@SafariMethod(checkToken=true, desc="训练保存列表",submit="POST")
	public Object saveList(ReqProtocol reqProtocol) {

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
			LOG.error("saveList>>>", e);
			e.printStackTrace();
		}

		try {
			TrainSavePage pro = (TrainSavePage) JSONObject
					.toBean(reqDomain.getMain(), TrainSavePage.class);
			Object obj = new JSONValidator().valid(pro);
			if (null == obj) {
				int pageNo = pro.getPageNo();
				int pageSize = pro.getPageSize();
				int pageIndex = (pageNo -1 ) * pageSize;
				
				TrainSave trainSave = new TrainSave();
				trainSave.setType(pro.getType());
				trainSave.setPageIndex(pageIndex);
				trainSave.setPageSize(pageSize);
				
				PageUtil<TrainSave> pager = trainService.findTrainSavePage(trainSave);
				
				safariResp = SafariResp.getInstance(pager);
			} else {
				return obj;
			}
		} catch (Exception e) {
			safariResp = SafariResp.getInstance(RespCode.$1000, RespCode
					.getRspCode(RespCode.$1000).desc());
			LOG.error("saveList>>>", e);
			e.printStackTrace();
		}
		
		return safariResp;
	}
	
	@ResponseBody
	@RequestMapping("saveDel")
	@SafariMethod(checkToken=true, desc="删除保存",submit="POST")
	public Object saveDel(ReqProtocol reqProtocol) {

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
			LOG.error("saveDel>>>", e);
			e.printStackTrace();
		}

		try {
			TrainDel pro = (TrainDel) JSONObject
					.toBean(reqDomain.getMain(), TrainDel.class);
			Object obj = new JSONValidator().valid(pro);
			if (null == obj) {
				trainService.deleteTrainSave(pro.getIds());
				
				safariResp = SafariResp.getInstance();
			} else {
				return obj;
			}
		} catch (Exception e) {
			safariResp = SafariResp.getInstance(RespCode.$1000, RespCode
					.getRspCode(RespCode.$1000).desc());
			LOG.error("saveDel>>>", e);
			e.printStackTrace();
		}
		
		return safariResp;
	}
	
	@ResponseBody
	@RequestMapping("count")
	@SafariMethod(checkToken=true, desc="统计阅读量",submit="POST")
	public Object count(ReqProtocol reqProtocol) {
		
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
			LOG.error("count>>>", e);
			e.printStackTrace();
		}
		
		try {
			@SuppressWarnings("rawtypes")
			Map<String, Class> classMap = new HashMap<String, Class>();
			classMap.put("reads", Read.class);
			Reads pro = (Reads) JSONObject.toBean(reqDomain.getMain(),
					Reads.class,classMap);
			List<Read> list = pro.getReads();
			
			if(list.size() > 0){
				List<Train> trains = new ArrayList<Train>();
				for (Read read : list) {
					Train train = new Train();
					train.setId(read.getId());
					train.setCount(read.getCount());
					
					trains.add(train);
				}
				
				trainService.batchCount(trains);
			}
				
			safariResp = SafariResp.getInstance();
		} catch (Exception e) {
			safariResp = SafariResp.getInstance(RespCode.$1000, RespCode
					.getRspCode(RespCode.$1000).desc());
			LOG.error("count>>>", e);
			e.printStackTrace();
		}
		
		return safariResp;
	}
}

