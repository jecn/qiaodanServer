package org.safari.sport.main.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONObject;

import org.safari.pub.platform.aop.annotation.SafariMethod;
import org.safari.pub.platform.global.Constants;
import org.safari.pub.platform.global.RespCode;
import org.safari.pub.platform.protocol.ReqProtocol;
import org.safari.pub.platform.protocol.SafariResp;
import org.safari.pub.platform.validator.JSONValidator;
import org.safari.pub.platform.web.entity.ReqDomain;
import org.safari.pub.utils.MathUtil;
import org.safari.pub.utils.StringUtil;
import org.safari.pub.utils.TokenUtil;
import org.safari.pub.utils.UUIDUtil;
import org.safari.pub.utils.page.PageUtil;
import org.safari.sport.main.entity.Evaluate;
import org.safari.sport.main.entity.Move;
import org.safari.sport.main.entity.Train;
import org.safari.sport.main.protocol.MovePro.MoveDetailVip;
import org.safari.sport.main.protocol.MovePro.MoveEval;
import org.safari.sport.main.protocol.MovePro.MovePageVip;
import org.safari.sport.main.protocol.MovePro.MoveUpload;
import org.safari.sport.main.protocol.MovePro.MoveUploads;
import org.safari.sport.main.service.EvaluateService;
import org.safari.sport.main.service.MoveService;
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
@RequestMapping("v0/sport/move")
public class MoveController {

	protected Logger LOG = LoggerFactory.getLogger(MoveController.class);
	
	@Autowired
	private MoveService moveService;
	
	@Autowired
	private EvaluateService evaluateService; 
	
	@Autowired
	private TrainService trainService;
	
	@ResponseBody
	@RequestMapping("upload")
	@SafariMethod(checkToken=true, desc="上传运动记录(单条记录)",submit="POST")
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
			MoveUpload pro = (MoveUpload) JSONObject
					.toBean(reqDomain.getMain(), MoveUpload.class);
			Object obj = new JSONValidator().valid(pro);
			if (null == obj) {
				Move temp = moveService.findByVipIdAndBeginTime(pro.getVipId(),pro.getBeginTime());
				if(null != temp){
					return SafariResp.getInstance(RespCode.$1026, RespCode
							.getRspCode(RespCode.$1026).desc());
				}
				
				Move move = new Move();
				convert(pro,move);
				
				moveService.upload(move);
				
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
	@RequestMapping("uploads")
	@SafariMethod(checkToken=true, desc="上传运动记录(多条记录)",submit="POST")
	public Object uploads(ReqProtocol reqProtocol) {

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
			@SuppressWarnings("rawtypes")
			Map<String, Class> classMap = new HashMap<String, Class>();
			classMap.put("sports", MoveUpload.class);
			MoveUploads pro = (MoveUploads) JSONObject.toBean(reqDomain.getMain(),
					MoveUploads.class,classMap);
			Object obj = new JSONValidator().valid(pro);
			
			if (null == obj) {
				int num = 0;
				List<Move> moves = new ArrayList<Move>();
				
				List<MoveUpload> uploads = pro.getSports();
				if(uploads.size() < 1){
					return SafariResp.getInstance(RespCode.$1026, RespCode
							.getRspCode(RespCode.$1026).desc());
				}
				
				for (MoveUpload moveUpload : uploads) {
					Move temp = moveService.findByVipIdAndBeginTime(moveUpload.getVipId(),moveUpload.getBeginTime());
					if(null == temp){
						Move move = new Move();
						convert(moveUpload,move);
						
						moves.add(move);
						
						try {
							moveService.upload(move);
						} catch (Exception e) {
							num ++;
							LOG.error("uploads>>>",e);
						}
					}
				}
				String token = reqDomain.getBiz().getToken();
				String subject = TokenUtil.parse(token, Constants.TOKEN_KEY);
				Vip info = (Vip) JSON.parseObject(subject, Vip.class);
				LOG.info("用户【"+ info.getId() + "】本次总共上传了【" + uploads.size() + "】条运动数据，其中正常数据" +
						"有【" + (moves.size() - num) + "】条，重复数据有【" + (uploads.size() - moves.size()) + "】条以及【"
						+ num + "】条异常数据。");
				
				if(moves.size() < 1){
					return SafariResp.getInstance(RespCode.$1026, RespCode
							.getRspCode(RespCode.$1026).desc());
				}
				
//				moveService.insertBatch(moves);
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
	@RequestMapping("list")
	@SafariMethod(checkToken=true, desc="获取用户周期间运动列表数据",submit="POST")
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
			MovePageVip pro = (MovePageVip) JSONObject
					.toBean(reqDomain.getMain(), MovePageVip.class);
			Object obj = new JSONValidator().valid(pro);
			if (null == obj) {
				String token = reqDomain.getBiz().getToken();
				String subject = TokenUtil.parse(token, Constants.TOKEN_KEY);
				Vip info = (Vip) JSON.parseObject(subject, Vip.class);
				
				int pageNo = pro.getPageNo();
				int pageSize = pro.getPageSize();
				String beginTime = pro.getBeginTime();
				String endTime = pro.getEndTime();
				
				PageUtil<Move> pager = moveService.findPageByVipAndCycle(info.getId(), beginTime, endTime, pageNo, pageSize);
				
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
	@SafariMethod(checkToken=true, desc="获取用户周期间运动详细数据",submit="POST")
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
			MoveDetailVip pro = (MoveDetailVip) JSONObject
					.toBean(reqDomain.getMain(), MoveDetailVip.class);
			Object obj = new JSONValidator().valid(pro);
			if (null == obj) {
				Move move = moveService.findById(pro.getId());
				
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("sport", move);
				
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
	@RequestMapping("eval")
	@SafariMethod(checkToken=true, desc="获取用户评估数据",submit="POST")
	public Object eval(ReqProtocol reqProtocol) {

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
			LOG.error("eval>>>", e);
			e.printStackTrace();
		}

		try {
			MoveEval pro = (MoveEval) JSONObject
					.toBean(reqDomain.getMain(), MoveEval.class);
			Object obj = new JSONValidator().valid(pro);
			if (null == obj) {
				String vipId = pro.getVipId();
				if(StringUtil.isEmpty(vipId)){
					String token = reqDomain.getBiz().getToken();
					String subject = TokenUtil.parse(token, Constants.TOKEN_KEY);
					Vip info = (Vip) JSON.parseObject(subject, Vip.class);
					vipId = info.getId();
				}
				
			
				Evaluate evaluate = evaluateService.findByVipId(vipId);
				
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("eval", evaluate);
				
				safariResp = SafariResp.getInstance(map);
			}else{
				return obj; 
			}
		} catch (Exception e) {
			safariResp = SafariResp.getInstance(RespCode.$1000, RespCode
					.getRspCode(RespCode.$1000).desc());
			LOG.error("eval>>>", e);
			e.printStackTrace();
		}
		
		return safariResp;
	}
	
	@ResponseBody
	@RequestMapping("radar")
	@SafariMethod(checkToken=true, desc="获取雷达评估数据",submit="POST")
	public Object radar(ReqProtocol reqProtocol) {

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
			LOG.error("radar>>>", e);
			e.printStackTrace();
		}

		try {
			MoveEval pro = (MoveEval) JSONObject
					.toBean(reqDomain.getMain(), MoveEval.class);
			Object obj = new JSONValidator().valid(pro);
			if (null == obj) {
				String vipId = pro.getVipId();
				if(StringUtil.isEmpty(vipId)){
					String token = reqDomain.getBiz().getToken();
					String subject = TokenUtil.parse(token, Constants.TOKEN_KEY);
					Vip info = (Vip) JSON.parseObject(subject, Vip.class);
					vipId = info.getId();
				}
				
				//所有的数据四舍五入且保留两位小数
				double verJump = 0.00;  //纵跳能力
				double lateralShearDirection = 0.00;  //侧切变向能力
				double addSpurt = 0.00;  //加速冲刺
				double explosiveForce = 0.00;  //爆发力
				double agile = 0.00;  //敏捷
				
				Move allMove = moveService.findRadar();  //所有人的平均数据
				Move singleMove = moveService.findRadarByVipId(vipId);  //个人的平均数据
				if(null != allMove && null != singleMove){
					double maxVerJumpAvgHigh = MathUtil.nullToD(allMove.getMaxVerJumpAvgHigh());; //最大纵跳平均高度
					double minVerJumpAvgHigh = MathUtil.nullToD(allMove.getMinVerJumpAvgHigh()); //最小纵跳平均高度
					double maxVerJumpCount = MathUtil.nullToD(allMove.getMaxVerJumpCount()); //最大纵跳次数
					double minVerJumpCount = MathUtil.nullToD(allMove.getMinVerJumpCount());   //最小纵跳次数
					double maxBreakinAvgTime = MathUtil.nullToD(allMove.getMaxBreakinAvgTime()); //最大变向平均触地时间
					double minBreakinAvgTime = MathUtil.nullToD(allMove.getMinBreakinAvgTime());  //最小变向平均触地时间
					double maxSpurtCount = MathUtil.nullToD(allMove.getMaxSpurtCount());  //最大冲刺次数
					double minSpurtCount = MathUtil.nullToD(allMove.getMinSpurtCount());   //最小冲刺次数
					double maxVerJumpMaxHigh = MathUtil.nullToD(allMove.getMaxVerJumpMaxHigh());   //最大纵跳最大高度
					double minVerJumpMaxHigh = MathUtil.nullToD(allMove.getMinVerJumpMaxHigh());   //最小纵跳最大高度
					double maxMaxSpeed = MathUtil.nullToD(allMove.getMaxMaxSpeed());   //最大最大移动速度
					double minMaxSpeed = MathUtil.nullToD(allMove.getMinMaxSpeed());   //最小最大移动速度
					double maxActiveRate = MathUtil.nullToD(allMove.getMaxActiveRate());  //最大活跃时间占比
					double minActiveRate = MathUtil.nullToD(allMove.getMinActiveRate());   //最小活跃时间占比
					double maxBreakinCount = MathUtil.nullToD(allMove.getMaxBreakinCount());   //最大变向次数
					double minBreakinCount= MathUtil.nullToD(allMove.getMinBreakinCount());  //最小变向次数
					
					double avgVerJumpAvgHigh = MathUtil.nullToD(singleMove.getAvgVerJumpAvgHigh());   //平均纵跳平均高度
					double avgVerJumpCount = MathUtil.nullToD(singleMove.getAvgVerJumpCount());   //平均纵跳次数
					double avgBreakinAvgTime = MathUtil.nullToD(singleMove.getAvgBreakinAvgTime());   //平均变向平均触地时间
					double avgSpurtCount = MathUtil.nullToD(singleMove.getAvgSpurtCount());   //平均冲刺次数
					double avgVerJumpMaxHigh = MathUtil.nullToD(singleMove.getAvgVerJumpMaxHigh());   //平均纵跳最大高度
					double avgMaxSpeed = MathUtil.nullToD(singleMove.getAvgMaxSpeed());   //平均最大移动速度
					double avgActiveRate = MathUtil.nullToD(singleMove.getAvgActiveRate());   //平均活跃时间占比
					double avgBreakinCount = MathUtil.nullToD(singleMove.getAvgBreakinCount());   //平均变向次数
					
					/*
					 * 纵跳能力：以纵跳平均高度和纵跳次数为指标，两项指标占比各占50%
					 * 获取到所有人中平均纵跳高度最大值maxA和平均纵跳高度最小值minA，本人的平均纵跳高度值X；
					 * 获取到所有人中平均纵跳次数最大值maxB和平均纵跳次数最小值minB，本人的平均纵跳次数值Y。
					 * 纵跳能力：Z =[ (X-minA)/(maxA-minA) * 50% + (Y-minB)/(maxB-minB) * 50%] * 100
					 * Z = [ (平均纵跳平均高度-平均最小纵跳平均高度)/(平均最大纵跳平均高度-平均最小纵跳平均高度) * 50% + 
					 * 		    (平均纵跳次数-平均最小纵跳次数)/(平均最大纵跳次数-平均最小纵跳次数) * 50%] * 100
					 */
					if(MathUtil.subDouble(maxVerJumpAvgHigh,minVerJumpAvgHigh) != 0 &&
						MathUtil.subDouble(maxVerJumpCount,minVerJumpCount) != 0){
						verJump = MathUtil.changeDouble(
							MathUtil.mulDouble(
								MathUtil.addDouble(
									MathUtil.divDouble(
											MathUtil.subDouble(avgVerJumpAvgHigh,minVerJumpAvgHigh),
											MathUtil.subDouble(maxVerJumpAvgHigh,minVerJumpAvgHigh)),
								    MathUtil.divDouble(
								    		MathUtil.subDouble(avgVerJumpCount,minVerJumpCount),
								    		MathUtil.subDouble(maxVerJumpCount,minVerJumpCount))
								 ),
							50), 2, BigDecimal.ROUND_HALF_UP);
					}
					
					/*
					 * 侧切变向：以变向平均触地时间为指标
					 * 获取所有人中平均变向平均触地时间最大值max 和平均变向平均触地时间最小值max，本人的平均变向平均触地时间X
					 * 侧切变向能力： Z = (X-min)/(max-min) * 100
					 * Z = (平均变向平均触地时间-平均最小变向平均触地时间)/(平均最大变向平均触地时间-平均最小变向平均触地时间) * 100
					 */
					if(MathUtil.subDouble(maxBreakinAvgTime,minBreakinAvgTime) != 0){
						lateralShearDirection = MathUtil.changeDouble(
							MathUtil.mulDouble(
								MathUtil.divDouble(
									MathUtil.subDouble(avgBreakinAvgTime,minBreakinAvgTime),
									MathUtil.subDouble(maxBreakinAvgTime,minBreakinAvgTime)
								),
							100), 2, BigDecimal.ROUND_HALF_UP);
					}
					
					/*
					 * 加速冲刺：以冲刺次数为指标
					 * 获取所有人中平均冲刺次数最大值max 和平均冲刺次数最小值max，本人的平均冲刺次数X
					 * 加速冲刺： Z = (X-min)/(max-min) * 100
					 * Z = (平均冲刺次数-平均最小冲刺次数)/(平均最大冲刺次数-平均最小冲刺次数) * 100
					 */
					if(MathUtil.subDouble(maxSpurtCount,minSpurtCount) != 0){
						addSpurt = MathUtil.changeDouble(
							MathUtil.mulDouble(
								MathUtil.divDouble(
									MathUtil.subDouble(avgSpurtCount,minSpurtCount),
									MathUtil.subDouble(maxSpurtCount,minSpurtCount)
								),
							100), 2, BigDecimal.ROUND_HALF_UP);
					}
					
					/*
					 * 爆发力：以纵跳最大高度和最大移动速度为指标，两项指标占比各占50%
					 * 获取到所有人中平均纵跳最大高度最大值maxA和平均纵跳最大高度最小值minA，本人的平均纵跳最大高度值X；
					 * 获取到所有人中平均最大移动速度最大值maxB和平均最大移动速度最小值minB，本人的平均最大移动速度值Y。
					 * 爆发力能力：Z =[ (X-minA)/(maxA-minA) * 50% + (Y-minB)/(maxB-minB) * 50%] * 100
					 * Z = [ (平均纵跳最大高度-平均最小纵跳最大高度)/(平均最大纵跳最大高度-平均最小纵跳最大高度) * 50% + 
					 * 		    (平均最大移动速度-平均最小最大移动速度)/(平均最大最大移动速度-平均最小最大移动速度) * 50%] * 100
					 */
					if(MathUtil.subDouble(maxVerJumpMaxHigh,minVerJumpMaxHigh) != 0 &&
							MathUtil.subDouble(maxMaxSpeed,minMaxSpeed) != 0){
						explosiveForce = MathUtil.changeDouble(
							MathUtil.mulDouble(
								MathUtil.addDouble(
									MathUtil.divDouble(
											MathUtil.subDouble(avgVerJumpMaxHigh,minVerJumpMaxHigh),
											MathUtil.subDouble(maxVerJumpMaxHigh,minVerJumpMaxHigh)),
								    MathUtil.divDouble(
								    		MathUtil.subDouble(avgMaxSpeed,minMaxSpeed),
								    		MathUtil.subDouble(maxMaxSpeed,minMaxSpeed))
								 ),
							50), 2, BigDecimal.ROUND_HALF_UP);
					}
					
					/*
					 * 敏捷：以活跃时间占比和变向次数为指标，两项指标占比各占50%
					 * 获取到所有人中平均活跃时间占比最大值maxA和平均活跃时间占比最小值minA，本人的平均活跃时间占比值X；
					 * 获取到所有人中平均变向次数最大值maxB和平均变向次数最小值minB，本人的平均变向次数值Y。
					 * 敏捷能力：Z =[ (X-minA)/(maxA-minA) * 50% + (Y-minB)/(maxB-minB) * 50%] * 100
					 * Z = [ (平均活跃时间占比-平均最小活跃时间占比)/(平均最大活跃时间占比-平均最小活跃时间占比) * 50% + 
					 * 		    (平均变向次数-平均最小变向次数)/(平均最大变向次数-平均最小变向次数) * 50%] * 100
					 */
					if(MathUtil.subDouble(maxActiveRate,minActiveRate) != 0 &&
							MathUtil.subDouble(maxBreakinCount,minBreakinCount) != 0){
						agile = MathUtil.changeDouble(
							MathUtil.mulDouble(
								MathUtil.addDouble(
									MathUtil.divDouble(
											MathUtil.subDouble(avgActiveRate,minActiveRate),
											MathUtil.subDouble(maxActiveRate,minActiveRate)),
								    MathUtil.divDouble(
								    		MathUtil.subDouble(avgBreakinCount,minBreakinCount),
								    		MathUtil.subDouble(maxBreakinCount,minBreakinCount))
								 ),
							50), 2, BigDecimal.ROUND_HALF_UP);
					}
					
				}
				
				
				Map<String,Double> map = new HashMap<String,Double>();
				map.put("verJump", verJump);
				map.put("lateralShearDirection", lateralShearDirection);
				map.put("addSpurt", addSpurt);
				map.put("explosiveForce", explosiveForce);
				map.put("agile", agile);
				
				String recm = getMinKey(map);
				if("verJump".equals(recm)){
					recm = "纵跳训练";
				}else if("lateralShearDirection".equals(recm)){
					recm = "侧切变向训练";
				}else if("addSpurt".equals(recm)){
					recm = "加速冲刺训练";
				}else if("explosiveForce".equals(recm)){
					recm = "爆发力训练";
				}else if("agile".equals(recm)){
					recm = "敏捷训练";
				}else{
					recm = "";
				}
				
				Train train = trainService.findNewByTitle(recm);
				
				Map<String,Object> resutlMap = new HashMap<String,Object>();
				resutlMap.putAll(map);
				resutlMap.put("train", train);
				safariResp = SafariResp.getInstance(resutlMap);
			}else{
				return obj; 
			}
		} catch (Exception e) {
			safariResp = SafariResp.getInstance(RespCode.$1000, RespCode
					.getRspCode(RespCode.$1000).desc());
			LOG.error("radar>>>", e);
			e.printStackTrace();
		}
		
		return safariResp;
	}

	private void convert(MoveUpload pro, Move move) {
		move.setId(UUIDUtil.generate());
		
		move.setActiveRate(pro.getActiveRate());
		move.setAddress(pro.getAddress());
		move.setAvgHoverTime(pro.getAvgHoverTime());
		move.setAvgShotDist(pro.getAvgShotDist());
		move.setAvgSpeed(pro.getAvgSpeed());
		move.setAvgTouchAngle(pro.getAvgTouchAngle());
		move.setAvgWallup(pro.getAvgWallup());
		move.setBeginTime(pro.getBeginTime());
		move.setBounceRank(pro.getBounceRank());
		move.setBreakinAvgTime(pro.getBreakinAvgTime());
		move.setBreakinCount(pro.getBreakinCount());
		move.setBreakRank(pro.getBreakRank());
		move.setCalorie(pro.getCalorie());
		move.setEndTime(pro.getEndTime());
		move.setFooter(pro.getFooter());
		move.setFieldType(pro.getFieldType());
		move.setHandle(pro.getHandle());
		move.setLatitude(pro.getLatitude());
		move.setLongitude(pro.getLongitude());
		move.setMaxShotDist(pro.getMaxShotDist());
		move.setMaxSpeed(pro.getMaxSpeed());
		move.setMaxWallup(pro.getMaxWallup());
		move.setPerfRank(pro.getPerfRank());
		move.setPicture(pro.getPicture());
		move.setRunRank(pro.getRunRank());
		move.setSn(pro.getSn());
		move.setSpend(pro.getSpend());
		move.setSpurtCount(pro.getSpurtCount());
		move.setTotalActiveTime(pro.getTotalActiveTime());
		move.setTotalDist(pro.getTotalDist());
		move.setTotalHorDist(pro.getTotalHorDist());
		move.setTotalStep(pro.getTotalStep());
		move.setTotalTime(pro.getTotalTime());
		move.setTotalVerDist(pro.getTotalVerDist());
		move.setTouchType(pro.getTouchType());
		move.setTrail(pro.getTrail());
		move.setType(pro.getType());
		move.setHeader(pro.getHeader());
		move.setVerJumpAvgHigh(pro.getVerJumpAvgHigh());
		move.setVerJumpCount(pro.getVerJumpCount());
		move.setVerJumpMaxHigh(pro.getVerJumpMaxHigh());
		move.setVerJumpPoint(pro.getVerJumpPoint());
		move.setVipId(pro.getVipId());
	}
	
	/**
	* 求Map<K,V>中Value值最小的Key
	* @param map
	* @return
	*/
	public static String getMinKey(Map<String, Double> map) {
		if (null == map){
			return null;
		}
		String min = "";
		double minLimit = 100;
		
		Set<String> set = map.keySet();
	    Iterator<String> it = set.iterator();
		while (it.hasNext()) {
			String key = (String) it.next();
			double init = (Double) map.get(key);
			if ( init < minLimit) {
				minLimit = init;
				min = key;
			}
		}
	    return min;
	}
}
