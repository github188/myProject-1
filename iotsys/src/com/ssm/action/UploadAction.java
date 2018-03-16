package com.ssm.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.ssm.beans.ApiDomain;
import com.ssm.util.BaseUtil;


@Controller("apiUploadAction")
public class UploadAction extends BaseAction{
	
	private Logger log = Logger.getLogger(this.getClass());		// Logger
	
	private File uploadify;
	private String uploadifyFileName;
	
	public void UploadFile() throws Exception{
		//获取系统真实路径
		Map<String, Object> map=new HashMap<String, Object>();
		ApiDomain domain=new ApiDomain();
		try{
			String savePath = ServletActionContext.getServletContext().getRealPath("");
			String newsuffix = "";
			if((uploadifyFileName != null)&&(uploadifyFileName.length()>0)){
				int dot = uploadifyFileName.lastIndexOf(".");
				if((dot >-1) && (dot < (uploadifyFileName.length() - 1))){
					newsuffix = uploadifyFileName.substring(dot + 1);
				}
			}
			FileInputStream fis = null;
			FileOutputStream fos = null;
			
			//格式为：feh8483438.jpg
			String fileName=BaseUtil.getUUID();
				savePath = savePath+"/upload/camera";
				File temp = new File(savePath);
				//如果不存在这个路径就创建文件夹
				if(!temp.exists() && !temp.isDirectory()){
					temp.mkdirs();
				}
				fis = new FileInputStream(uploadify);
				fos = new FileOutputStream(savePath+"/"+fileName+"."+newsuffix);
				IOUtils.copy(fis, fos);
				fos.flush();
				String imgUrl="upload/camera/"+fileName+"."+newsuffix;
				String filenames=fileName+"."+newsuffix;
				map.put("filenames", filenames);
				map.put("imgUrl", imgUrl);
				domain.setCode("001");
				domain.setMsg("upload/camera/"+filenames);
				domain.setData(map);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			this.printObjectNoJsonp(domain);
		}
		log.info("上传结束");
	}

	public File getUploadify() {
		return uploadify;
	}

	public void setUploadify(File uploadify) {
		this.uploadify = uploadify;
	}

	public String getUploadifyFileName() {
		return uploadifyFileName;
	}

	public void setUploadifyFileName(String uploadifyFileName) {
		this.uploadifyFileName = uploadifyFileName;
	}

	
}
