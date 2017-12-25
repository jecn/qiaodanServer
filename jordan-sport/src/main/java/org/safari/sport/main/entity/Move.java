package org.safari.sport.main.entity;

import java.io.Serializable;

import org.safari.pub.platform.web.entity.Base;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *<p>Title:运动数据</p>
 *<p>Description: </p>
 *<p>Company: 深圳市萨法瑞科技有限公司</p>
 *@author Alitalk
 *@date 2017-02-06
 */
public class Move extends Base implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    private String id;

    /**
     * 会员ID
     */
    @JsonIgnore
    private String vipId;
    
    /**
     * 蓝牙SN
     */
    private String sn;
    
    /**
     * 左右脚 R 右脚 L 左脚
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
     * 纵跳次数
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
     * 球场类型 1 全场 2 半场
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
    
    
    /**
	 * 最大纵跳平均高度
	 */
    @JsonIgnore
	private String maxVerJumpAvgHigh;
	
	/**
	 * 最小纵跳平均高度
	 */
    @JsonIgnore
	private String minVerJumpAvgHigh;
	
	/**
	 * 平均纵跳平均高度
	 */
    @JsonIgnore
	private String avgVerJumpAvgHigh;
	
	/**
	 * 最大纵跳次数
	 */
    @JsonIgnore
	private String maxVerJumpCount;
	
	/**
	 * 最小纵跳次数
	 */
    @JsonIgnore
	private String minVerJumpCount;
	
	/**
	 * 平均纵跳次数
	 */
    @JsonIgnore
	private String avgVerJumpCount;
	
	/**
	 * 最大变向平均触地时间
	 */
    @JsonIgnore
	private String maxBreakinAvgTime;
	
	/**
	 * 最小变向平均触地时间
	 */
    @JsonIgnore
	private String minBreakinAvgTime;
	
	/**
	 * 平均变向平均触地时间
	 */
    @JsonIgnore
	private String avgBreakinAvgTime;
	
	/**
	 * 最大冲刺次数
	 */
    @JsonIgnore
	private String maxSpurtCount;
	
	/**
	 * 最小冲刺次数
	 */
    @JsonIgnore
	private String minSpurtCount;
	
	/**
	 * 平均冲刺次数
	 */
    @JsonIgnore
	private String avgSpurtCount;
	
	/**
	 * 最大纵跳最大高度
	 */
    @JsonIgnore
	private String maxVerJumpMaxHigh;
	
	/**
	 * 最小纵跳最大高度
	 */
    @JsonIgnore
	private String minVerJumpMaxHigh;
	
	/**
	 * 平均纵跳最大高度
	 */
    @JsonIgnore
	private String avgVerJumpMaxHigh;
	
	/**
	 * 最大最大移动速度
	 */
    @JsonIgnore
	private String maxMaxSpeed;
	
	/**
	 * 最小最大移动速度
	 */
    @JsonIgnore
	private String minMaxSpeed;
	
	/**
	 * 平均最大移动速度
	 */
    @JsonIgnore
	private String avgMaxSpeed;
	
	/**
	 * 最大活跃时间占比
	 */
    @JsonIgnore
	private String maxActiveRate;
	
	/**
	 * 最小活跃时间占比
	 */
    @JsonIgnore
	private String minActiveRate;
	
	/**
	 * 平均活跃时间占比
	 */
    @JsonIgnore
	private String avgActiveRate;
	
	/**
	 * 最大变向次数
	 */
    @JsonIgnore
	private String maxBreakinCount;
	
	/**
	 * 最小变向次数
	 */
    @JsonIgnore
	private String minBreakinCount;
	
	/**
	 * 平均变向次数
	 */
    @JsonIgnore
	private String avgBreakinCount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getVipId() {
        return vipId;
    }

    public void setVipId(String vipId) {
        this.vipId = vipId == null ? null : vipId.trim();
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
		this.footer = footer == null ? null : footer.trim();
	}

	public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude == null ? null : longitude.trim();
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude == null ? null : latitude.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
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
        this.spend = spend == null ? null : spend.trim();
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture == null ? null : picture.trim();
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
        this.totalDist = totalDist == null ? null : totalDist.trim();
    }

    public String getTotalStep() {
        return totalStep;
    }

    public void setTotalStep(String totalStep) {
        this.totalStep = totalStep == null ? null : totalStep.trim();
    }

    public String getTotalHorDist() {
        return totalHorDist;
    }

    public void setTotalHorDist(String totalHorDist) {
        this.totalHorDist = totalHorDist == null ? null : totalHorDist.trim();
    }

    public String getTotalVerDist() {
        return totalVerDist;
    }

    public void setTotalVerDist(String totalVerDist) {
        this.totalVerDist = totalVerDist == null ? null : totalVerDist.trim();
    }

    public String getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(String totalTime) {
        this.totalTime = totalTime == null ? null : totalTime.trim();
    }

    public String getTotalActiveTime() {
        return totalActiveTime;
    }

    public void setTotalActiveTime(String totalActiveTime) {
        this.totalActiveTime = totalActiveTime == null ? null : totalActiveTime.trim();
    }

    public String getActiveRate() {
        return activeRate;
    }

    public void setActiveRate(String activeRate) {
        this.activeRate = activeRate == null ? null : activeRate.trim();
    }

    public String getAvgSpeed() {
        return avgSpeed;
    }

    public void setAvgSpeed(String avgSpeed) {
        this.avgSpeed = avgSpeed == null ? null : avgSpeed.trim();
    }

    public String getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(String maxSpeed) {
        this.maxSpeed = maxSpeed == null ? null : maxSpeed.trim();
    }

    public String getSpurtCount() {
        return spurtCount;
    }

    public void setSpurtCount(String spurtCount) {
        this.spurtCount = spurtCount == null ? null : spurtCount.trim();
    }

    public String getBreakinCount() {
        return breakinCount;
    }

    public void setBreakinCount(String breakinCount) {
        this.breakinCount = breakinCount == null ? null : breakinCount.trim();
    }

    public String getBreakinAvgTime() {
        return breakinAvgTime;
    }

    public void setBreakinAvgTime(String breakinAvgTime) {
        this.breakinAvgTime = breakinAvgTime == null ? null : breakinAvgTime.trim();
    }
    
    public String getVerJumpPoint() {
		return verJumpPoint;
	}

	public void setVerJumpPoint(String verJumpPoint) {
		this.verJumpPoint = verJumpPoint == null ? null : verJumpPoint.trim();
	}

	public String getVerJumpCount() {
        return verJumpCount;
    }

    public void setVerJumpCount(String verJumpCount) {
        this.verJumpCount = verJumpCount == null ? null : verJumpCount.trim();
    }

    public String getVerJumpAvgHigh() {
        return verJumpAvgHigh;
    }

    public void setVerJumpAvgHigh(String verJumpAvgHigh) {
        this.verJumpAvgHigh = verJumpAvgHigh == null ? null : verJumpAvgHigh.trim();
    }
    
    public String getVerJumpMaxHigh() {
		return verJumpMaxHigh;
	}

	public void setVerJumpMaxHigh(String verJumpMaxHigh) {
		this.verJumpMaxHigh = verJumpMaxHigh == null ? null : verJumpMaxHigh.trim();
	}

	public String getAvgHoverTime() {
        return avgHoverTime;
    }

    public void setAvgHoverTime(String avgHoverTime) {
        this.avgHoverTime = avgHoverTime == null ? null : avgHoverTime.trim();
    }

    public String getAvgTouchAngle() {
        return avgTouchAngle;
    }

    public void setAvgTouchAngle(String avgTouchAngle) {
        this.avgTouchAngle = avgTouchAngle == null ? null : avgTouchAngle.trim();
    }

    public String getTouchType() {
        return touchType;
    }

    public void setTouchType(String touchType) {
        this.touchType = touchType == null ? null : touchType.trim();
    }

    public String getPerfRank() {
        return perfRank;
    }

    public void setPerfRank(String perfRank) {
        this.perfRank = perfRank == null ? null : perfRank.trim();
    }

    public String getRunRank() {
        return runRank;
    }

    public void setRunRank(String runRank) {
        this.runRank = runRank == null ? null : runRank.trim();
    }

    public String getBreakRank() {
        return breakRank;
    }

    public void setBreakRank(String breakRank) {
        this.breakRank = breakRank == null ? null : breakRank.trim();
    }

    public String getBounceRank() {
        return bounceRank;
    }

    public void setBounceRank(String bounceRank) {
        this.bounceRank = bounceRank == null ? null : bounceRank.trim();
    }

    public String getAvgShotDist() {
        return avgShotDist;
    }

    public void setAvgShotDist(String avgShotDist) {
        this.avgShotDist = avgShotDist == null ? null : avgShotDist.trim();
    }

    public String getMaxShotDist() {
        return maxShotDist;
    }

    public void setMaxShotDist(String maxShotDist) {
        this.maxShotDist = maxShotDist == null ? null : maxShotDist.trim();
    }
    
    public String getMaxWallup() {
		return maxWallup;
	}

	public void setMaxWallup(String maxWallup) {
		this.maxWallup = maxWallup == null ? null : maxWallup.trim();
	}

	public String getAvgWallup() {
		return avgWallup;
	}

	public void setAvgWallup(String avgWallup) {
		this.avgWallup = avgWallup == null ? null : avgWallup.trim();
	}

	public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle == null ? null : handle.trim();
    }

    public String getCalorie() {
        return calorie;
    }

    public void setCalorie(String calorie) {
        this.calorie = calorie == null ? null : calorie.trim();
    }

    public String getTrail() {
        return trail;
    }

    public void setTrail(String trail) {
        this.trail = trail == null ? null : trail.trim();
    }
    
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type == null ? null : type.trim();
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
		this.header = header == null ? null : header.trim();
	}

	public String getMaxVerJumpAvgHigh() {
		return maxVerJumpAvgHigh;
	}

	public void setMaxVerJumpAvgHigh(String maxVerJumpAvgHigh) {
		this.maxVerJumpAvgHigh = maxVerJumpAvgHigh;
	}

	public String getMinVerJumpAvgHigh() {
		return minVerJumpAvgHigh;
	}

	public void setMinVerJumpAvgHigh(String minVerJumpAvgHigh) {
		this.minVerJumpAvgHigh = minVerJumpAvgHigh;
	}

	public String getAvgVerJumpAvgHigh() {
		return avgVerJumpAvgHigh;
	}

	public void setAvgVerJumpAvgHigh(String avgVerJumpAvgHigh) {
		this.avgVerJumpAvgHigh = avgVerJumpAvgHigh;
	}

	public String getMaxVerJumpCount() {
		return maxVerJumpCount;
	}

	public void setMaxVerJumpCount(String maxVerJumpCount) {
		this.maxVerJumpCount = maxVerJumpCount;
	}

	public String getMinVerJumpCount() {
		return minVerJumpCount;
	}

	public void setMinVerJumpCount(String minVerJumpCount) {
		this.minVerJumpCount = minVerJumpCount;
	}

	public String getAvgVerJumpCount() {
		return avgVerJumpCount;
	}

	public void setAvgVerJumpCount(String avgVerJumpCount) {
		this.avgVerJumpCount = avgVerJumpCount;
	}

	public String getMaxBreakinAvgTime() {
		return maxBreakinAvgTime;
	}

	public void setMaxBreakinAvgTime(String maxBreakinAvgTime) {
		this.maxBreakinAvgTime = maxBreakinAvgTime;
	}

	public String getMinBreakinAvgTime() {
		return minBreakinAvgTime;
	}

	public void setMinBreakinAvgTime(String minBreakinAvgTime) {
		this.minBreakinAvgTime = minBreakinAvgTime;
	}

	public String getAvgBreakinAvgTime() {
		return avgBreakinAvgTime;
	}

	public void setAvgBreakinAvgTime(String avgBreakinAvgTime) {
		this.avgBreakinAvgTime = avgBreakinAvgTime;
	}

	public String getMaxSpurtCount() {
		return maxSpurtCount;
	}

	public void setMaxSpurtCount(String maxSpurtCount) {
		this.maxSpurtCount = maxSpurtCount;
	}

	public String getMinSpurtCount() {
		return minSpurtCount;
	}

	public void setMinSpurtCount(String minSpurtCount) {
		this.minSpurtCount = minSpurtCount;
	}

	public String getAvgSpurtCount() {
		return avgSpurtCount;
	}

	public void setAvgSpurtCount(String avgSpurtCount) {
		this.avgSpurtCount = avgSpurtCount;
	}

	public String getMaxVerJumpMaxHigh() {
		return maxVerJumpMaxHigh;
	}

	public void setMaxVerJumpMaxHigh(String maxVerJumpMaxHigh) {
		this.maxVerJumpMaxHigh = maxVerJumpMaxHigh;
	}

	public String getMinVerJumpMaxHigh() {
		return minVerJumpMaxHigh;
	}

	public void setMinVerJumpMaxHigh(String minVerJumpMaxHigh) {
		this.minVerJumpMaxHigh = minVerJumpMaxHigh;
	}

	public String getAvgVerJumpMaxHigh() {
		return avgVerJumpMaxHigh;
	}

	public void setAvgVerJumpMaxHigh(String avgVerJumpMaxHigh) {
		this.avgVerJumpMaxHigh = avgVerJumpMaxHigh;
	}

	public String getMaxMaxSpeed() {
		return maxMaxSpeed;
	}

	public void setMaxMaxSpeed(String maxMaxSpeed) {
		this.maxMaxSpeed = maxMaxSpeed;
	}

	public String getMinMaxSpeed() {
		return minMaxSpeed;
	}

	public void setMinMaxSpeed(String minMaxSpeed) {
		this.minMaxSpeed = minMaxSpeed;
	}

	public String getAvgMaxSpeed() {
		return avgMaxSpeed;
	}

	public void setAvgMaxSpeed(String avgMaxSpeed) {
		this.avgMaxSpeed = avgMaxSpeed;
	}

	public String getMaxActiveRate() {
		return maxActiveRate;
	}

	public void setMaxActiveRate(String maxActiveRate) {
		this.maxActiveRate = maxActiveRate;
	}

	public String getMinActiveRate() {
		return minActiveRate;
	}

	public void setMinActiveRate(String minActiveRate) {
		this.minActiveRate = minActiveRate;
	}

	public String getAvgActiveRate() {
		return avgActiveRate;
	}

	public void setAvgActiveRate(String avgActiveRate) {
		this.avgActiveRate = avgActiveRate;
	}

	public String getMaxBreakinCount() {
		return maxBreakinCount;
	}

	public void setMaxBreakinCount(String maxBreakinCount) {
		this.maxBreakinCount = maxBreakinCount;
	}

	public String getMinBreakinCount() {
		return minBreakinCount;
	}

	public void setMinBreakinCount(String minBreakinCount) {
		this.minBreakinCount = minBreakinCount;
	}

	public String getAvgBreakinCount() {
		return avgBreakinCount;
	}

	public void setAvgBreakinCount(String avgBreakinCount) {
		this.avgBreakinCount = avgBreakinCount;
	}
    
}