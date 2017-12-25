package org.safari.sport.main.protocol;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class MovePro implements Serializable{
		
		private static final long serialVersionUID = 1L;
		
		public static class MoveUpload{
			
			/**
			 * 用户ID
			 */
			@NotEmpty(message="用户ID不能为空")
			private String vipId;
			
			/**
			 * 蓝牙SN
			 */
			private String sn;
			
			/**
			 *左右脚 R 右脚 L 左脚 
			 */
			private String footer;
			
			 /**
		     * 经度
		     */
		    private String longitude;

		    /**
		     * 纬度
		     */
		    private String latitude;

		    /**
		     * 位置
		     */
		    private String address;

		    /**
		     * 开始时间
		     */
		    private String beginTime;

		    /**
		     * 运动时长
		     */
		    private String spend;

		    /**
		     * 图片
		     */
		    private String picture;

		    /**
		     * 结束时间
		     */
		    private String endTime;

		    /**
		     * 总距离
		     */
		    private String totalDist;

		    /**
		     * 总步数
		     */
		    private String totalStep;

		    /**
		     * 横向总距离
		     */
		    private String totalHorDist;

		    /**
		     * 纵向总距离
		     */
		    private String totalVerDist;

		    /**
		     * 总时间
		     */
		    private String totalTime;

		    /**
		     * 活跃总时间
		     */
		    private String totalActiveTime;

		    /**
		     * 活跃占比
		     */
		    private String activeRate;

		    /**
		     * 平均移动速度
		     */
		    private String avgSpeed;

		    /**
		     * 最大移动速度
		     */
		    private String maxSpeed;

		    /**
		     * 冲向次数
		     */
		    private String spurtCount;

		    /**
		     * 变向次数
		     */
		    private String breakinCount;

		    /**
		     * 变向平均触地时间
		     */
		    private String breakinAvgTime;
		    
		    /**
		     * 纵跳点
		     */
		    private String verJumpPoint;

		    /**
		     * 纵跳次数
		     */
		    private String verJumpCount;

		    /**
		     * 纵跳平均高度
		     */
		    private String verJumpAvgHigh;
		    
		    /**
		     * 纵跳最大高度
		     */
		    private String verJumpMaxHigh;
		    
		    /**
		     * 平均滞空时间
		     */
		    private String avgHoverTime;

		    /**
		     * 平均着地旋转角
		     */
		    private String avgTouchAngle;

		    /**
		     * 着地类型
		     */
		    private String touchType;
		    
		    /**
		     * 本场表现
		     */
		    private String perfRank;

		    /**
		     * 跑动等级
		     */
		    private String runRank;

		    /**
		     * 突破等级
		     */
		    private String breakRank;

		    /**
		     * 弹跳等级
		     */
		    private String bounceRank;

		    /**
		     * 平均出手距离
		     */
		    private String avgShotDist;

		    /**
		     * 最大出手距离
		     */
		    private String maxShotDist;
		    
		    /**
		     * 最大冲刺力
		     */
		    private String maxWallup;
		    
		    /**
		     * 平均冲刺力
		     */
		    private String avgWallup;

		    /**
		     * 手感
		     */
		    private String handle;

		    /**
		     * 消耗卡路里
		     */
		    private String calorie;

		    /**
		     * 轨迹
		     */
		    private String trail;
		    
		    /**
		     * 球场类型(1全场 2 半场)
		     */
		    private String type;
		    
		    /**
		     * 场地类型(1:水泥 2:塑胶 3:木地板)
		     */
		    private String fieldType;
		    
		    /**
		     * 头
		     */
		    private String header;

			public String getVipId() {
				return vipId;
			}

			public void setVipId(String vipId) {
				this.vipId = vipId;
			}

			public String getSn() {
				return sn;
			}

			public void setSn(String sn) {
				this.sn = sn;
			}

			public String getFooter() {
				return footer;
			}

			public void setFooter(String footer) {
				this.footer = footer;
			}

			public String getLongitude() {
				return longitude;
			}

			public void setLongitude(String longitude) {
				this.longitude = longitude;
			}

			public String getLatitude() {
				return latitude;
			}

			public void setLatitude(String latitude) {
				this.latitude = latitude;
			}

			public String getAddress() {
				return address;
			}

			public void setAddress(String address) {
				this.address = address;
			}

			public String getBeginTime() {
				return beginTime;
			}

			public void setBeginTime(String beginTime) {
				this.beginTime = beginTime;
			}

			public String getSpend() {
				return spend;
			}

			public void setSpend(String spend) {
				this.spend = spend;
			}

			public String getPicture() {
				return picture;
			}

			public void setPicture(String picture) {
				this.picture = picture;
			}

			public String getEndTime() {
				return endTime;
			}

			public void setEndTime(String endTime) {
				this.endTime = endTime;
			}

			public String getTotalDist() {
				return totalDist;
			}

			public void setTotalDist(String totalDist) {
				this.totalDist = totalDist;
			}

			public String getTotalStep() {
				return totalStep;
			}

			public void setTotalStep(String totalStep) {
				this.totalStep = totalStep;
			}

			public String getTotalHorDist() {
				return totalHorDist;
			}

			public void setTotalHorDist(String totalHorDist) {
				this.totalHorDist = totalHorDist;
			}

			public String getTotalVerDist() {
				return totalVerDist;
			}

			public void setTotalVerDist(String totalVerDist) {
				this.totalVerDist = totalVerDist;
			}

			public String getTotalTime() {
				return totalTime;
			}

			public void setTotalTime(String totalTime) {
				this.totalTime = totalTime;
			}

			public String getTotalActiveTime() {
				return totalActiveTime;
			}

			public void setTotalActiveTime(String totalActiveTime) {
				this.totalActiveTime = totalActiveTime;
			}

			public String getActiveRate() {
				return activeRate;
			}

			public void setActiveRate(String activeRate) {
				this.activeRate = activeRate;
			}

			public String getAvgSpeed() {
				return avgSpeed;
			}

			public void setAvgSpeed(String avgSpeed) {
				this.avgSpeed = avgSpeed;
			}

			public String getMaxSpeed() {
				return maxSpeed;
			}

			public void setMaxSpeed(String maxSpeed) {
				this.maxSpeed = maxSpeed;
			}

			public String getSpurtCount() {
				return spurtCount;
			}

			public void setSpurtCount(String spurtCount) {
				this.spurtCount = spurtCount;
			}

			public String getBreakinCount() {
				return breakinCount;
			}

			public void setBreakinCount(String breakinCount) {
				this.breakinCount = breakinCount;
			}

			public String getBreakinAvgTime() {
				return breakinAvgTime;
			}

			public void setBreakinAvgTime(String breakinAvgTime) {
				this.breakinAvgTime = breakinAvgTime;
			}

			public String getVerJumpPoint() {
				return verJumpPoint;
			}

			public void setVerJumpPoint(String verJumpPoint) {
				this.verJumpPoint = verJumpPoint;
			}

			public String getVerJumpCount() {
				return verJumpCount;
			}

			public void setVerJumpCount(String verJumpCount) {
				this.verJumpCount = verJumpCount;
			}

			public String getVerJumpAvgHigh() {
				return verJumpAvgHigh;
			}

			public void setVerJumpAvgHigh(String verJumpAvgHigh) {
				this.verJumpAvgHigh = verJumpAvgHigh;
			}

			public String getVerJumpMaxHigh() {
				return verJumpMaxHigh;
			}

			public void setVerJumpMaxHigh(String verJumpMaxHigh) {
				this.verJumpMaxHigh = verJumpMaxHigh;
			}

			public String getAvgHoverTime() {
				return avgHoverTime;
			}

			public void setAvgHoverTime(String avgHoverTime) {
				this.avgHoverTime = avgHoverTime;
			}

			public String getAvgTouchAngle() {
				return avgTouchAngle;
			}

			public void setAvgTouchAngle(String avgTouchAngle) {
				this.avgTouchAngle = avgTouchAngle;
			}

			public String getTouchType() {
				return touchType;
			}

			public void setTouchType(String touchType) {
				this.touchType = touchType;
			}

			public String getPerfRank() {
				return perfRank;
			}

			public void setPerfRank(String perfRank) {
				this.perfRank = perfRank;
			}

			public String getRunRank() {
				return runRank;
			}

			public void setRunRank(String runRank) {
				this.runRank = runRank;
			}

			public String getBreakRank() {
				return breakRank;
			}

			public void setBreakRank(String breakRank) {
				this.breakRank = breakRank;
			}

			public String getBounceRank() {
				return bounceRank;
			}

			public void setBounceRank(String bounceRank) {
				this.bounceRank = bounceRank;
			}

			public String getAvgShotDist() {
				return avgShotDist;
			}

			public void setAvgShotDist(String avgShotDist) {
				this.avgShotDist = avgShotDist;
			}

			public String getMaxShotDist() {
				return maxShotDist;
			}

			public void setMaxShotDist(String maxShotDist) {
				this.maxShotDist = maxShotDist;
			}
			
			public String getMaxWallup() {
				return maxWallup;
			}

			public void setMaxWallup(String maxWallup) {
				this.maxWallup = maxWallup;
			}

			public String getAvgWallup() {
				return avgWallup;
			}

			public void setAvgWallup(String avgWallup) {
				this.avgWallup = avgWallup;
			}

			public String getHandle() {
				return handle;
			}

			public void setHandle(String handle) {
				this.handle = handle;
			}

			public String getCalorie() {
				return calorie;
			}

			public void setCalorie(String calorie) {
				this.calorie = calorie;
			}

			public String getTrail() {
				return trail;
			}

			public void setTrail(String trail) {
				this.trail = trail;
			}

			public String getType() {
				return type;
			}

			public void setType(String type) {
				this.type = type;
			}
			
			public String getFieldType() {
				return fieldType;
			}

			public void setFieldType(String fieldType) {
				this.fieldType = fieldType;
			}

			public String getHeader() {
				return header;
			}

			public void setHeader(String header) {
				this.header = header;
			}
		}
		
		public static class MoveUploads{
			
			/**
			 * 上传信息
			 */
			@NotNull
			private List<MoveUpload> sports;

			public List<MoveUpload> getSports() {
				return sports;
			}

			public void setSports(List<MoveUpload> sports) {
				this.sports = sports;
			}
			
			
		}
		
		public static class MovePageVip{
			/**
			 * 开始时间 无开始时间为-1
			 */
			private String beginTime;
			
			/**
			 * 结束时间 无结束时间为-1
			 */
			private String endTime;
			
			/**
			 * 第几页
			 */
			@NotNull
			@Min(value=1,message="分页必须从第一页开始")
			private int pageNo;
			
			/**
			 * 分页 每页记录数
			 */
			@NotNull
			@Min(value=1,message="分页每页记录数不得少于1")
			private int pageSize;

			public String getBeginTime() {
				return beginTime;
			}

			public void setBeginTime(String beginTime) {
				this.beginTime = beginTime;
			}

			public String getEndTime() {
				return endTime;
			}

			public void setEndTime(String endTime) {
				this.endTime = endTime;
			}

			public int getPageNo() {
				return pageNo;
			}

			public void setPageNo(int pageNo) {
				this.pageNo = pageNo;
			}

			public int getPageSize() {
				return pageSize;
			}

			public void setPageSize(int pageSize) {
				this.pageSize = pageSize;
			}
			
		}
		
		public static class  MoveDetailVip{
			/**
			 * 主键
			 */
			@NotEmpty
			private String id;

			public String getId() {
				return id;
			}

			public void setId(String id) {
				this.id = id;
			}
			
		}
		
		public static class MoveEval{
			/**
			 * 会员ID
			 */
			private String vipId;

			public String getVipId() {
				return vipId;
			}

			public void setVipId(String vipId) {
				this.vipId = vipId;
			}
			
		}
}
