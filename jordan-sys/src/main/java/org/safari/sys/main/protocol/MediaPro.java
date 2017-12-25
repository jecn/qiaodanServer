package org.safari.sys.main.protocol;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class MediaPro implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static class MediaSingle{
		
		/**
		 * 类型 1头像 2 图片 3 音频 4 视频 5 文件
		 */
		@NotEmpty(message="类型不能为空")
		private String type;
		
		/**
		 * 媒体名称
		 */
		@NotEmpty(message="名称不能为空")
		private String name;
		
		/**
		 *  媒体内容(Base64编码)
		 */
		@NotEmpty(message="文件不能为空")
		private String file;

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getFile() {
			return file;
		}

		public void setFile(String file) {
			this.file = file;
		}
		
	}

	public static class MediaMore{
		
		/**
		 * 多媒体文件
		 */
		@NotNull
		private List<MediaSingle> medias;

		public List<MediaSingle> getMedias() {
			return medias;
		}

		public void setMedias(List<MediaSingle> medias) {
			this.medias = medias;
		}
	}
	
	public static class MediaView{
		
		/**
		 * 多媒体文件
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
}
