package com.ssm.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ssm.beans.ApiDomain;
import com.ssm.beans.CameraInfoBean;
import com.ssm.service.ICameraInfoService;
import com.ssm.util.BaseUtil;

@Controller("cameraInfoAction")
@Scope("prototype")
public class CameraInfoAction extends BaseAction{
	@Resource
	private ICameraInfoService cameraInfoService;
	
	private Logger log = Logger.getLogger(this.getClass());		
	/**
	 * 增加摄像头  修改摄像头
	 */
	public void  addCamera() {
		log.info("-----增加摄像头  修改摄像头---【cameraInfoAction_addCamera.do】");
		ApiDomain domain=new ApiDomain();
		domain.setCode("001");
		domain.setMsg("操作ok");
		try {
		String channel = getRequest().getParameter("channel");
		String pkDeviceId = getRequest().getParameter("pkDeviceId");
		String cameraId = getRequest().getParameter("id");
		String cameraName = getRequest().getParameter("cameraName");
		String pkEId = getRequest().getParameter("pkEId");
		String serialNumber = getRequest().getParameter("serialNumber");
		String unitType = getRequest().getParameter("unitType");
		String loginId = getRequest().getParameter("loginId");
		String password = getRequest().getParameter("password1");
		String control1 = getRequest().getParameter("control1");
		String control2 = getRequest().getParameter("control2");
		String comments = getRequest().getParameter("comments");
		String imgText1 = getRequest().getParameter("imgText1");
		String flog = getRequest().getParameter("flog");
		CameraInfoBean camera=new CameraInfoBean();
		if (flog.equals("0")) {
//			验证通道唯一性
			CameraInfoBean cameraV = cameraInfoService.findByChannelAndpk(channel,pkDeviceId);
			if (cameraV!=null) {
				domain.setCode("002");
				domain.setMsg("通道号重复请重新填写");
				return;
			}
//			验证序列号唯一性
			Map<String, String> map = new HashMap<String, String>();
			map.put("deviceno", serialNumber);
			List<CameraInfoBean> cameraV1 = cameraInfoService.findBySer(map);
			if (cameraV1!=null && cameraV1.size()>0) {
				domain.setCode("002");
				domain.setMsg("摄像头序列号重复");
				return;
			}
			//添加操作
			camera.setId(BaseUtil.getUUID());
			camera.setCameraName(cameraName);
			camera.setCameraChannnel(channel);
			camera.setSerialNumber(serialNumber);
			camera.setCameraPassword(password);
//			camera.setCameraPtzControl(Integer.parseInt(cameraJson.optString("ptzControl")));
//			camera.setCameraZoomControl(Integer.parseInt(cameraJson.optString("zoomControl")));
			camera.setCameraUnitType(unitType);
			camera.setCaneraLoginId(loginId);
			camera.setComments(comments);
			camera.setPkDeviceId(pkDeviceId);
			camera.setPkEnterpriseId(pkEId.split("ˇ")[0]);
			camera.setEnterpriseName(pkEId.split("ˇ")[1]);
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			camera.setUpdateTime(sdf.format(new Date()));
			camera.setCameraImg(imgText1);
			cameraInfoService.addCamera(camera);
		}
		if(flog.equals("1")){
//			验证通道唯一性
			CameraInfoBean cameraV = cameraInfoService.findByChannelAndpk(channel,pkDeviceId);
			if (cameraV!=null && !cameraV.getId().equals(cameraId)) {
				domain.setCode("002");
				domain.setMsg("通道号重复请重新填写");
				return;
			}
//			验证序列号唯一性
			Map<String, String> map = new HashMap<String, String>();
			map.put("deviceno", serialNumber);
			List<CameraInfoBean> cameraV1 = cameraInfoService.findBySer(map);
			if (cameraV1!=null && cameraV1.size()>0 && !cameraV1.get(0).getId().equals(cameraId)) {
				domain.setCode("002");
				domain.setMsg("摄像头序列号重复");
				return;
			}
			//更新操作
			camera.setCameraName(cameraName);
			camera.setCameraChannnel(channel);
			camera.setSerialNumber(serialNumber);
			camera.setCameraPassword(password);
//			camera.setCameraPtzControl(Integer.parseInt(cameraJson.optString("ptzControl")));
//			camera.setCameraZoomControl(Integer.parseInt(cameraJson.optString("zoomControl")));
			camera.setCameraUnitType(unitType);
			camera.setCaneraLoginId(loginId);
			camera.setComments(comments);
			camera.setPkDeviceId(pkDeviceId);
			camera.setPkEnterpriseId(pkEId.split("ˇ")[0]);
			camera.setEnterpriseName(pkEId.split("ˇ")[1]);
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			camera.setUpdateTime(sdf.format(new Date()));
			camera.setId(cameraId);
			camera.setCameraImg(imgText1);
			cameraInfoService.updateCamera(camera);
		}
		} catch (Exception e) {
			domain.setCode("-001");
			domain.setMsg("系统异常");
			log.error("增加摄像头  修改摄像头发生异常："+e.getMessage(),e);
			e.printStackTrace();
		}finally{
			try {
				this.printObjectNoJsonp(domain);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		log.info("-----增加摄像头  修改摄像头结束----");
	}
	
	/**
	 * 企业用户修改摄像头
	 */
	public void updateCamera(){
		log.info("-----企业用户修改摄像头---【cameraInfoAction_updateCamera.do】");
		ApiDomain domain=new ApiDomain();
		domain.setCode("001");
		domain.setMsg("操作ok");
		try {
			String cameraId = getRequest().getParameter("cameraId");
			String cameraName = getRequest().getParameter("cameraName");
			String comments = getRequest().getParameter("comments");
			String imgname= getRequest().getParameter("imgname");
			CameraInfoBean bean = cameraInfoService.getById(cameraId);
			bean.setUpdateTime(BaseUtil.getCurrentTimestr());
			bean.setCameraName(cameraName);
			bean.setComments(comments);
			bean.setCameraImg(imgname);
			cameraInfoService.updateCamera(bean);
		} catch (Exception e) {
			domain.setCode("-001");
			domain.setMsg("系统异常");
			log.error("企业用户修改摄像头发生异常："+e.getMessage(),e);
			e.printStackTrace();
		}finally{
			try {
				this.printObjectNoJsonp(domain);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		log.info("-----企业用户修改摄像头结束----");
	}
	/**
	 * 删除摄像头
	 */
	public void deletCamera(){
		log.info("-----删除摄像头---【cameraInfoAction_deletCamera.do】");
		ApiDomain domain=new ApiDomain();
		domain.setCode("001");
		domain.setMsg("操作ok");
		try {
			String cameraId = getRequest().getParameter("cameraId");
			cameraInfoService.deleteCaneraById(cameraId);
		} catch (Exception e) {
			domain.setCode("-001");
			domain.setMsg("系统异常");
			log.error("删除摄像头发生异常："+e.getMessage(),e);
			e.printStackTrace();
		}finally{
			try {
				this.printObjectNoJsonp(domain);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		log.info("-----删除摄像头结束----");
	}
	/**
	 * 找出所有的摄像头   包括企业名字
	 */
	public void findAllCamera(){
		log.info("-----找出所有的摄像头   包括企业名字---【cameraInfoAction_findAllCamera.do】");
		try {
			String enterpriseId = getRequest().getParameter("enterpriseId");
			String aORb = getRequest().getParameter("aORb");
			String alQeada = getRequest().getParameter("alQeada");
			String massif = getRequest().getParameter("massif");
			Map<String, String> map = new HashMap<String, String>();
			map.put("enterpriseId", enterpriseId);
			map.put("aORb", aORb);
			map.put("alQeada", alQeada);
			map.put("massif", massif);
			List<CameraInfoBean> list = cameraInfoService.selectAll(map);
			if (list!=null) {
				for (int i = 0; i < list.size(); i++) {
					if(list.get(i).getSerialNumber()!=null && getRedisTemplate().hasKey(list.get(i).getSerialNumber())){
						list.get(i).setPkEnterpriseId("正常");
					}else{
						list.get(i).setPkEnterpriseId("不在线");
					}
				}
			}else {
				list = new ArrayList<CameraInfoBean>();
			}
			this.printList(list);
		} catch (Exception e) {
			log.error("找出所有的摄像头   包括企业名字发生异常："+e.getMessage(),e);
			e.printStackTrace();
		}
		log.info("-----找出所有的摄像头   包括企业名字结束----");
	}
	
	/**
	 * 根据主键查询摄像头
	 */
	public void getCameraById(){
		log.info("-----根据主键查询摄像头---【cameraInfoAction_getCameraById.do】");
		ApiDomain domain=new ApiDomain();
		String string = getRequest().getParameter("id");
		if (null==string||string.equals("")) {
			domain.setCode("002");
			domain.setMsg("参数为空");
			return;
		}
		try {
			CameraInfoBean bean = cameraInfoService.getById(string);
			if (null!=bean) {
				if(bean.getMassifProductbean()!=null && bean.getMassifProductbean().getDeviceSerialNumber()!=null && getRedisTemplate().hasKey(bean.getMassifProductbean().getDeviceSerialNumber())){
					domain.setMsg("正常");
				}else{
					domain.setMsg("不在线");
				}
				domain.setCode("001");
				domain.setData(bean);
			}else {
				domain.setCode("000");
				domain.setMsg("没有数据");
			}
		} catch (Exception e) {
			domain.setCode("-001");
			domain.setMsg("系统异常");
			log.error("根据主键查询摄像头发生异常："+e.getMessage(),e);
			e.printStackTrace();
		}finally{
			try {
				this.printObjectNoJsonp(domain);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		log.info("-----根据主键查询摄像头结束----");
	}
}
