package org.safari.sys.main.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.safari.pub.platform.aop.annotation.SafariMethod;
import org.safari.pub.platform.enums.MediaEnum;
import org.safari.pub.platform.global.RespCode;
import org.safari.pub.platform.protocol.ReqProtocol;
import org.safari.pub.platform.protocol.SafariResp;
import org.safari.pub.platform.validator.JSONValidator;
import org.safari.pub.platform.web.entity.ReqDomain;
import org.safari.pub.utils.DateUtil;
import org.safari.pub.utils.FileUtil;
import org.safari.pub.utils.PropertiesUtil;
import org.safari.pub.utils.RandomUtil;
import org.safari.pub.utils.UUIDUtil;
import org.safari.sys.main.entity.Media;
import org.safari.sys.main.protocol.MediaPro.MediaMore;
import org.safari.sys.main.protocol.MediaPro.MediaSingle;
import org.safari.sys.main.protocol.MediaPro.MediaView;
import org.safari.sys.main.service.MediaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import sun.misc.BASE64Decoder;

@Controller
@RequestMapping("v0/sys/media")
public class MediaController {
	
	protected Logger LOG = LoggerFactory.getLogger(MediaController.class);
	
	private String upload = PropertiesUtil.getValue("safari.media.upload"); //媒体文件上传路径
	private String download = PropertiesUtil.getValue("safari.media.download"); //媒体文件下载路径
	
	@Autowired
	private MediaService mediaService;
	
	@ResponseBody
	@RequestMapping("upload")
	@SafariMethod(checkToken=true,desc="上传媒体文件(单个文件)",submit="POST")
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
			MediaSingle pro = (MediaSingle) JSONObject.toBean(reqDomain.getMain(), MediaSingle.class);
			Object obj = new JSONValidator().valid(pro);
			if (null == obj) {
				
				String type = pro.getType();     // 1头像 2 图片 3 音频 4 视频 5 文件
				String name = pro.getName();  //媒体名称
				String file = pro.getFile();  //媒体内容 (Base64编码)
				
				String uniqueFileName = RandomUtil.randomNInt(20); //系统重新分配的新名称
				String suffix = name.substring(name.lastIndexOf("."));  //媒体文件后缀
				String newFileName = uniqueFileName + suffix; //媒体文件新名称
				
				/*
				 * 将文件存储到磁盘中
				 */
		        String datePathStr =  DateUtil.dateToStr(new Date(),"yyyy/MM/dd"); //生成日期目录
		        String fileDirStr = upload + MediaEnum.getValue(type) + FileUtil.fileSplit + datePathStr;
		        File fileDir = new File(fileDirStr);
		        
		        //判断目录文件是否存在  不存在即创建
		        if(!fileDir.exists()) {  
		            fileDir.mkdirs();
		        }
		        
		        //文件处理
		        generateFile(file, fileDirStr + FileUtil.fileSplit + newFileName);
		        
		        Media media = new Media();
		        media.setId(UUIDUtil.generate());
		        media.setType(type);
		        media.setFolder(MediaEnum.getValue(type));
		        media.setName(name);
		        media.setSuffix(suffix);
		        media.setSource("1");
		        media.setPath(datePathStr + FileUtil.fileSplit + newFileName);
		        media.setLength(FileUtil.formatFileSize(FileUtil.fileSizes(new File(
		        		fileDirStr + FileUtil.fileSplit + newFileName))));
		        if("3".equals(type) || "4".equals(type)){  //媒体文件为音频或视频时需计算时长
		        	
		        }
		        
		        mediaService.insert(media);
		        
		        String url = datePathStr +  FileUtil.fileSplit + newFileName;
				url = download + MediaEnum.getValue(type) + FileUtil.fileSplit + url;
				
				JSONObject dataJson = new JSONObject();
		        dataJson.put("id", media.getId());
		        dataJson.put("name", name);
				dataJson.put("url", url);
				
				safariResp = SafariResp.getInstance(dataJson);
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
	@SafariMethod(checkToken=true,desc="上传媒体文件(多个文件)",submit="POST")
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
			LOG.error("uploads>>>", e);
			e.printStackTrace();
		}

		try {
			
			@SuppressWarnings("rawtypes")
			Map<String, Class> classMap = new HashMap<String, Class>();
			classMap.put("medias", MediaSingle.class);
			MediaMore pro = (MediaMore) JSONObject.toBean(reqDomain.getMain(),
					MediaMore.class,classMap);
			Object obj = new JSONValidator().valid(pro);
			if (null == obj) {
				JSONObject resultJson = new JSONObject();
				JSONArray array = new JSONArray();
				List<Media> medias =new ArrayList<Media>();
				
				List<MediaSingle> mediaPros = pro.getMedias();
				for (MediaSingle mediaPro : mediaPros) {
					String type = mediaPro.getType();     // 1头像 2 图片 3 音频 4 视频 5 文件
					String name = mediaPro.getName();  //媒体名称
					String file = mediaPro.getFile();  //媒体内容 (Base64编码)
					
					String uniqueFileName = RandomUtil.randomNInt(20); //系统重新分配的新名称
					String suffix = name.substring(name.lastIndexOf("."));  //媒体文件后缀
					String newFileName = uniqueFileName + suffix; //媒体文件新名称
					
					/*
					 * 将文件存储到磁盘中
					 */
			        String datePathStr =  DateUtil.dateToStr(new Date(),"yyyy/MM/dd"); //生成日期目录
			        String fileDirStr = upload + MediaEnum.getValue(type) + FileUtil.fileSplit + datePathStr;
			        File fileDir = new File(fileDirStr);
			        
			        //判断目录文件是否存在  不存在即创建
			        if(!fileDir.exists()) {  
			            fileDir.mkdirs();
			        }
			        
			        //文件处理
			        generateFile(file, fileDirStr + FileUtil.fileSplit + newFileName);
			        
			        Media media = new Media();
			        media.setId(UUIDUtil.generate());
			        media.setType(type);
			        media.setFolder(MediaEnum.getValue(type));
			        media.setName(name);
			        media.setSuffix(suffix);
			        media.setSource("1");
			        media.setPath(MediaEnum.getValue(type) + FileUtil.fileSplit + 
			        		datePathStr + FileUtil.fileSplit + newFileName);
			        media.setLength(FileUtil.formatFileSize(FileUtil.fileSizes(new File(
			        		fileDirStr + FileUtil.fileSplit + newFileName))));
			        if("3".equals(type) || "4".equals(type)){  //媒体文件为音频或视频时需计算时长
			        	
			        }
			        
			        medias.add(media);
			        
			        String url = datePathStr +  FileUtil.fileSplit + newFileName;
					url = download + MediaEnum.getValue(type) + FileUtil.fileSplit + url;
					
					JSONObject dataJson = new JSONObject();
			        dataJson.put("id", media.getId());
			        dataJson.put("name", name);
					dataJson.put("url", url);
					
					array.add(dataJson);
				}
				
				mediaService.insertBatch(medias);
				
				resultJson.put("medias", array);
				
				safariResp = SafariResp.getInstance(resultJson);
			} else {
				return obj;
			}
		} catch (Exception e) {
			safariResp = SafariResp.getInstance(RespCode.$1000, RespCode
					.getRspCode(RespCode.$1000).desc());
			LOG.error("uploads>>>", e);
			e.printStackTrace();
		}

		return safariResp;
	}
	
	@ResponseBody
	@RequestMapping("view")
	@SafariMethod(checkToken=true,desc="查看媒体文件")
	public Object view(ReqProtocol reqProtocol){
		
		String request = null;
		JSONObject reqJson = null;
		ReqDomain reqDomain = null;
		SafariResp<?> safariResp = null;
		
		try {
			request = reqProtocol.getInfoJson();
			reqJson = JSONObject.fromObject(request);
			reqDomain = (ReqDomain) JSONObject.toBean(reqJson,ReqDomain.class);
		} catch (Exception e) {
			safariResp = SafariResp.getInstance(RespCode.$1001, RespCode
					.getRspCode(RespCode.$1001).desc());
			
			LOG.error("view>>>",e);
			e.printStackTrace();
		}
		
		try {
			MediaView pro = (MediaView) JSONObject.toBean(reqDomain.getMain(),
					MediaView.class);
			Object obj = new JSONValidator().valid(pro);
			if (null == obj) {
				List<Media> medias = mediaService.queryByIds(pro.getIds());
				
				JSONObject resultJson = new JSONObject();
				JSONArray array = new JSONArray();
				for (Media media : medias) {
					JSONObject json = new JSONObject();
					String type = media.getType();   // 1头像 2 图片 3 音频 4 视频 5 文件
					String source = media.getSource(); //来源 1 内部 2 外部
					String path = media.getPath();  //相对路径
					String url = media.getUrl();  //绝对路径
					
					if("1".equals(source)){
						url = download + MediaEnum.getValue(type) + FileUtil.fileSplit + path;
					}
					
					json.put("id", media.getId());
					json.put("name", media.getName());
					json.put("url", url);
					
					array.add(json);
				}
				resultJson.put("medias", array);
				
				safariResp = SafariResp.getInstance(resultJson);
			} else {
				return obj;
			}
		} catch (Exception e) {
			safariResp = SafariResp.getInstance(RespCode.$1001, RespCode
					.getRspCode(RespCode.$1001).desc());
			
			LOG.error("view>>>",e);
			e.printStackTrace();
		}
		
		return safariResp;
	}

	
	// base64字符串转化成文件
	private void generateFile(String base64File, String filePath)
			throws Exception { // 对字节数组字符串进行Base64解码并生成图片
		BASE64Decoder decoder = new BASE64Decoder();
		// Base64解码
		byte[] bytes = decoder.decodeBuffer(base64File);
		for (int i = 0; i < bytes.length; ++i) {
			if (bytes[i] < 0) {// 调整异常数据
				bytes[i] += 256;
			}
		}
		// 生成文件
		OutputStream out = new FileOutputStream(filePath);
		out.write(bytes);
		out.flush();
		out.close();
	}
}
