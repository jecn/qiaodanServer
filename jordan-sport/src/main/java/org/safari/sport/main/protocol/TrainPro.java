package org.safari.sport.main.protocol;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class TrainPro implements Serializable{
		
		private static final long serialVersionUID = 1L;
		
		public static class TrainPage{
			
			/**
			 * 训练字典ID
			 */
			private String tdId;
			
			/**
			 * 类型
			 */
			private String type;
			
			/**
			 * 位置
			 */
			private String position;
			
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
			
			public String getTdId() {
				return tdId;
			}
			
			public String getType() {
				return type;
			}

			public void setType(String type) {
				this.type = type;
			}

			public String getPosition() {
				return position;
			}

			public void setPosition(String position) {
				this.position = position;
			}

			public void setTdId(String tdId) {
				this.tdId = tdId;
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
		
		public static class TrainDel{
			/**
			 * 主键 多个以“,”分隔
			 */
			@NotEmpty
			private String ids;

			public String getIds() {
				return ids;
			}

			public void setIds(String ids) {
				this.ids = ids;
			}
			
		}
		
		public static class  TrainDetail{
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
		
		public static class TrainUplaod{
			/**
			 * 训练ID
			 */
			private String moveId;
			
			/**
			 * 类型 1 收藏 2 分享
			 */
			private String type;
			
			/**
			 * 分享平台
			 */
			@NotEmpty(message="分享平台不能为空")
			private String platform;
			
			/**
			 * 来源(1:结束运动 2:运动详情)
			 */
			private String source;
			
			/**
			 * 想法
			 */
			private String say;
			
			/**
			 * 图片
			 */
			private String img;
			
			/**
			 * 链接
			 */
			private String url;
			
			
			
			public String getMoveId() {
				return moveId;
			}

			public void setMoveId(String moveId) {
				this.moveId = moveId;
			}
			
			public String getType() {
				return type;
			}

			public void setType(String type) {
				this.type = type;
			}

			public String getPlatform() {
				return platform;
			}

			public void setPlatform(String platform) {
				this.platform = platform;
			}
			
			public String getSource() {
				return source;
			}

			public void setSource(String source) {
				this.source = source;
			}

			public String getSay() {
				return say;
			}

			public void setSay(String say) {
				this.say = say;
			}

			public String getImg() {
				return img;
			}

			public void setImg(String img) {
				this.img = img;
			}

			public String getUrl() {
				return url;
			}

			public void setUrl(String url) {
				this.url = url;
			}

		}
		
		public static class TrainSavePage{
			
			/**
			 * 类型 1 收藏 2 分享
			 */
			private String type;
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
			
			public String getType() {
				return type;
			}

			public void setType(String type) {
				this.type = type;
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
		
		public static class Read{
			/**
			 * 主键ID
			 */
			private String id;
			
			/**
			 * 数量
			 */
			private Integer count;

			public String getId() {
				return id;
			}

			public void setId(String id) {
				this.id = id;
			}

			public Integer getCount() {
				return count;
			}

			public void setCount(Integer count) {
				this.count = count;
			}
			
		}
		
		public static class Reads{
			
			private List<Read> reads;

			public List<Read> getReads() {
				return reads;
			}

			public void setReads(List<Read> reads) {
				this.reads = reads;
			}
			
		}
}
