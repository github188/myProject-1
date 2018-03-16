package com.ssm.trace;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ssm.action.BaseAction;
import com.ssm.beans.ApiDomain;
import com.ssm.beans.CameraInfoBean;
import com.ssm.beans.DeviceInfoBean;
import com.ssm.beans.MassifProductbean;
import com.ssm.service.ICameraInfoService;
import com.ssm.service.IDeviceInfoService;
import com.ssm.service.IMassIfProductService;
import com.ssm.util.HttpClientUtil;
import com.ssm.util.PropertiesUtil;

@Controller("apiAlQeadaAction")
@Scope("prototype")
public class AlQeadaAction extends BaseAction {
	@Resource
	private IMassIfProductService massIfProductService;
	@Resource
	private IDeviceInfoService deviceInfoService;
	@Resource
	private ICameraInfoService cameraInfoService;
	
	/**
	 * 新增基地不保存到（iot_massif_product）只是调用溯源系统的接口
	 */
	public void addAlQaeda(){
		try {
			ApiDomain apiDomain=new ApiDomain();
			String parameter = getRequest().getParameter("json");
			JSONObject json = JSONObject.fromObject(parameter);
			String baseName = json.optString("baseName");
			String baseId = json.optString("baseId");
			String lon = json.optString("lon");
			String lat = json.optString("lat");
			String enterpriseId=json.optString("enterpriseId");
			String comments=json.optString("comments");
			if (null==baseName||baseName.equals("")||null==enterpriseId||enterpriseId.equals("")) {
				apiDomain.setCode("001");
				apiDomain.setData("参数不全");
			}
			//修改基地名称同步修改绑定数据
			if (baseId!=null && !"".equals(baseId)) {
				Map<String, String> map=new HashMap<>();
				map.put("baseId", baseId);
				map.put("baseName", baseName);
				massIfProductService.updateMassifProductByBaseId(map);
			}
			HttpClientUtil httpClientUtil=new HttpClientUtil();
			JSONObject paramarm=new JSONObject();
			paramarm.put("baseName", baseName);
			paramarm.put("baseId", baseId);
			paramarm.put("enterpriseId", enterpriseId);
			paramarm.put("comments", comments);
			paramarm.put("lon", lon);
			paramarm.put("lat", lat);
			System.out.println(paramarm);
			JSONObject post = HttpClientUtil.httpPost(PropertiesUtil.getAppConfig("myMongo3")+"apiAlQaedaInBackAction_operateAlQaeda.do", paramarm, false);
			if (post.optString("code").equals("001")) {//说明操作成功
				apiDomain.setCode("001");
				apiDomain.setData("操作成功");
			}else {
				apiDomain.setCode(post.optString("code"));
				apiDomain.setData(post.optString("msg"));
			}
			this.printObjectNoJsonp(apiDomain);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	//更新基地
	public void  updateMassIfProductBase() {
		
		//这里对获取数据的方式进行判断  如果是前端调用(json)  就需要要保存的同时调用接口   ;如果只是 溯源系统调用过来的（inputstream） 那么只需要保存就可以了
		ApiDomain domain=new ApiDomain();
		domain.setCode("001");
		domain.setMsg("操作成功");
		try {
		String json = getRequest().getParameter("json");
		MassifProductbean productbean=new MassifProductbean();
		
		if (null==json||StringUtils.isEmpty(json)) {
			//说明是溯源系统过来调接口
			String strMessage = "";
			String strResponse = "";
			InputStream  inputStream = getRequest().getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream,"utf-8"));
			while ((strMessage = reader.readLine()) != null) {
				strResponse += strMessage;
			}
			reader.close();
			inputStream.close();
			/*验证参数*/
			if(null==strResponse||strResponse.equals("")){
				domain.setCode("002");
				domain.setMsg("没有参数");
				return;
			}
			//赋值
			JSONObject fromObject = JSONObject.fromObject(strResponse);
			if (null==fromObject.optString("baseId")||fromObject.optString("baseId").equals("")) {
				domain.setCode("003");
				domain.setMsg("基地id为空");
				return;
			}
			productbean.setBaseId(fromObject.optString("baseId"));
			productbean.setBaseName(fromObject.optString("baseName"));
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			productbean.setUploadTime(sdf.format(new Date()));
		}else {
			//说明是前端来保存数据  需要调用接口    接口只能改基地名称
			JSONObject jsonObject = JSONObject.fromObject(json);
			
			productbean.setBaseId(jsonObject.optString("baseId"));
			productbean.setBaseName(jsonObject.optString("baseName"));
			//调用溯源项目接口
			HttpClientUtil httpClientUtil=new HttpClientUtil();
			JSONObject jsonData=new JSONObject();
			jsonData.put("baseId", jsonObject.optString("baseId"));
			jsonData.put("baseName", jsonObject.optString("baseName"));
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			productbean.setUploadTime(sdf.format(new Date()));
			JSONObject post = HttpClientUtil.httpPost(PropertiesUtil.getAppConfig("myMongo3")+"apiAlQaedaInBackAction_operateAlQaeda.do", jsonData, false);
			if (!post.optString("code").equals("001")) {
				domain.setCode(post.optString("code"));
				domain.setMsg(post.optString("msg"));
				return;
			}
		}
		massIfProductService.updataMassifProduct(productbean);		
		} catch (Exception e) {
			domain.setCode("-1");
			domain.setData("系统异常");
			e.printStackTrace();
		} finally{
			try {
				this.printObjectNoJsonp(domain);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	/**
	 * 新增区块不保存到（iot_massif_product）只是调用溯源系统的接口
	 */
	public void addMassIf(){
		try {
			ApiDomain apiDomain=new ApiDomain();
			String parameter = getRequest().getParameter("json");
			JSONObject json = JSONObject.fromObject(parameter);
			String maddIfName = json.optString("massifName");
			String massifId = json.optString("massifId");
			String baseId=json.optString("baseId");
			String comments=json.optString("comments");
			if (null==maddIfName||maddIfName.equals("")||null==baseId||baseId.equals("")) {
				apiDomain.setCode("001");
				apiDomain.setData("参数不全");
			}
			//修改地块名称同步修改绑定数据
			if (massifId!=null && !"".equals(massifId)) {
				Map<String, String> map=new HashMap<>();
				map.put("massifId", massifId);
				map.put("massifName", maddIfName);
				massIfProductService.updateMassifProductByMassifId(map);
			}
			HttpClientUtil httpClientUtil=new HttpClientUtil();
			JSONObject paramarm=new JSONObject();
			paramarm.put("massIfName", maddIfName);
			paramarm.put("massifId", massifId);
			paramarm.put("baseId", baseId);
			paramarm.put("comments", comments);
			paramarm.put("massIdId", "");
			JSONObject post = HttpClientUtil.httpPost(PropertiesUtil.getAppConfig("myMongo3")+"apiAlQaedaInBackAction_operateMassif.do", paramarm, false);
			if (post.optString("code").equals("001")) {//说明操作成功
				apiDomain.setCode("001");
				apiDomain.setData("操作成功");
			}else {
				apiDomain.setCode(post.optString("code"));
				apiDomain.setData(post.optString("msg"));
			}
			this.printObjectNoJsonp(apiDomain);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 更新区块
	 */
	
	public void  updateMassIfProductMass() {
		//这里对获取数据的方式进行判断  如果是前端调用(json)  就需要要保存的同时调用接口   ;如果只是 溯源系统调用过来的（inputstream） 那么只需要保存就可以了
			ApiDomain domain=new ApiDomain();
			domain.setCode("001");
			domain.setMsg("操作成功");
			try {
				String json = getRequest().getParameter("json");
				MassifProductbean productbean=new MassifProductbean();
				
				if (null==json||StringUtils.isEmpty(json)) {
					//说明是溯源系统过来调接口
					String strMessage = "";
					String strResponse = "";
					InputStream  inputStream = getRequest().getInputStream();
					BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream,"utf-8"));
					while ((strMessage = reader.readLine()) != null) {
						strResponse += strMessage;
					}
					reader.close();
					inputStream.close();
					/*验证参数*/
					if(null==strResponse||strResponse.equals("")){
						domain.setCode("002");
						domain.setMsg("没有参数");
						return;
					}
					//赋值
					JSONObject fromObject = JSONObject.fromObject(strResponse);
					if (null==fromObject.optString("massIfId")||fromObject.optString("massIfId").equals("")) {
						domain.setCode("003");
						domain.setMsg("地块id为空");
						return;
					}
					productbean.setMassifId(fromObject.optString("massIfId"));
					productbean.setMassIfName(fromObject.optString("massIfName"));
					SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
					productbean.setUploadTime(sdf.format(new Date()));
				}else {
					//说明是前端来保存数据  需要调用接口    接口只能改区块名称
					JSONObject jsonObject = JSONObject.fromObject(json);
					
					productbean.setMassifId(jsonObject.optString("massIfId"));
					productbean.setMassIfName(jsonObject.optString("massIfName"));
					
					//调用溯源项目接口
					HttpClientUtil httpClientUtil=new HttpClientUtil();
					JSONObject jsonData=new JSONObject();
					jsonData.put("massIfId", jsonObject.optString("massIfId"));
					jsonData.put("massIfName", jsonObject.optString("massIfName"));
					SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
					productbean.setUploadTime(sdf.format(new Date()));
					JSONObject post = HttpClientUtil.httpPost(PropertiesUtil.getAppConfig("myMongo3")+"apiAlQaedaInBackAction_operateMassif.do", jsonData, false);
					if (!post.optString("code").equals("001")) {
						domain.setCode(post.optString("code"));
						domain.setMsg(post.optString("msg"));
						return;
					}
				}
				massIfProductService.updateMassifMass(productbean);		
			} catch (Exception e) {
					domain.setCode("-001");
					domain.setData("系统异常");
					e.printStackTrace();
			} finally{
					try {
						this.printObjectNoJsonp(domain);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
	}
	
	/**
	 * 修改 iot_massif_product中的企业信息
	 * 这个方法也是可以物联网 调用也可以  前端调   但是不会互相掉
	 */
	public void updateMassIdEnter(){
		ApiDomain domain=new ApiDomain();
		domain.setCode("001");
		domain.setMsg("操作成功");
		try {
			String json = getRequest().getParameter("json");
			String enterpriseId;
			String enterpriseName;
				//溯源系统传过来的 只需要保存就可以了
				String strMessage = "";
				String strResponse = "";
				InputStream  inputStream = getRequest().getInputStream();
				BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream,"utf-8"));
				while ((strMessage = reader.readLine()) != null) {
					strResponse += strMessage;
				}
				reader.close();
				inputStream.close();
				/*验证参数*/
				if(null==strResponse||strResponse.equals("")){
					domain.setCode("002");
					domain.setMsg("没有参数");
					return;
				}
				JSONObject jsonObject = JSONObject.fromObject(strResponse);
				enterpriseId=jsonObject.optString("enterpriseId");
				enterpriseName=jsonObject.optString("enterpriseName");
			
				/*//前端调用  需要调溯源接口进行修改
				JSONObject jsonObject = JSONObject.fromObject(json);
				enterpriseId=jsonObject.optString("enterpriseId");
				enterpriseName=jsonObject.optString("enterpriseName");
				HttpClientUtil httpClientUtil=new HttpClientUtil();
				JSONObject jsonData=new JSONObject();
				jsonData.put("enterpriseId", enterpriseId);
				jsonData.put("enterpriseName",enterpriseName);
				JSONObject post = HttpClientUtil.httpPost(PropertiesUtil.getAppConfig("myMongo3")+"apiAlQaedaInBackAction_operateAlQaedaEnter.do", jsonData, false);
				if (!post.optString("code").equals("001")) {
					domain.setCode(post.optString("code"));
					domain.setMsg(post.optString("msg"));
					return;
				}*/
			
			Map<String, String> map=new HashMap<>();
			map.put("enterpriseId", enterpriseId);
			map.put("enterpriseName", enterpriseName);
			massIfProductService.updateMassifBaseEnter(map);
		}catch (Exception e) {
			domain.setCode("-001");
			domain.setMsg("系统异常");
			e.printStackTrace();
		}finally{
			try {
				this.printObjectNoJsonp(domain);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
/****************************************物联网基地相关方法zm**********************************************************/
	/**
	 * 访问mogo项目 获取基地
	 * @throws Exception 
	 */
	public void findAlAndDevice() throws Exception {
		ApiDomain domain=new ApiDomain();
		domain.setCode("000");
		domain.setMsg("操作成功");
		try {
			String enterpriseId = getRequest().getParameter("enterpriseId");
			JSONObject jsonObject = new JSONObject().element("enterpriseId", enterpriseId);
			JSONObject json = HttpClientUtil.httpPost(PropertiesUtil.getAppConfig("myMongo3")+"apiAlQaedaAction_pullAlQaedas.do?enterpriseId="+enterpriseId, jsonObject, false);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("alQeada", json);
			domain.setData(map);
		} catch (Exception e) {
			domain.setCode("001");
			domain.setMsg("系统异常");
			e.printStackTrace();
		}finally{
			this.printObjectNoJsonp(domain);
		}
	}
	/**
	 * 访问mogo项目 获取地块
	 * @throws Exception 
	 */
	public void findMassifByalQeadaId() throws Exception {
		ApiDomain domain=new ApiDomain();
		domain.setCode("000");
		domain.setMsg("操作成功");
		try {
			String alQeadaId = getRequest().getParameter("alQeadaId");
			JSONObject jsonObject = new JSONObject().element("alQaedaId", alQeadaId);
			JSONObject json = HttpClientUtil.httpPost(PropertiesUtil.getAppConfig("myMongo3")+"apiAlQaedaAction_pullMassifs.do?alQaedaId="+alQeadaId, jsonObject, false);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("massif", json);
			domain.setData(map);
		} catch (Exception e) {
			domain.setCode("001");
			domain.setMsg("系统异常");
			e.printStackTrace();
		}finally{
			this.printObjectNoJsonp(domain);
		}
	}
	/**
	 * 根据地块获取相关设备
	 * @throws Exception 
	 */
	public void findInfoByMassifId() throws Exception {
		ApiDomain domain=new ApiDomain();
		domain.setCode("000");
		domain.setMsg("操作成功");
		try {
			String massifId = getRequest().getParameter("massifId");
			String enterpriseId = getRequest().getParameter("enterpriseId");
			Map<String, Object> mapParam = new HashMap<String, Object>();
			mapParam.put("massifId", massifId);
			List<MassifProductbean> mList = massIfProductService.getMassifAndDeviceList(mapParam);
			
			Map<String, Object> mapParam2 = new HashMap<String, Object>();
			mapParam2.put("enterpriseId", enterpriseId);
			List<MassifProductbean> allList = massIfProductService.getMassifAndDeviceList(mapParam2);
			
			Map<String, Object> mapParam1 = new HashMap<String, Object>();
			mapParam1.put("enterpriseId", enterpriseId);
			List<DeviceInfoBean> dBeans = this.deviceInfoService.findByEnterpriseIdAndName(mapParam1);
			
			List<CameraInfoBean> cameras = cameraInfoService.findByEnterprId(enterpriseId);
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("mList", mList);
			map.put("allList", allList);
			map.put("dBeans", dBeans);
			map.put("cameras", cameras);
			
			domain.setData(map);
		} catch (Exception e) {
			domain.setCode("001");
			domain.setMsg("系统异常");
			e.printStackTrace();
		}finally{
			this.printObjectNoJsonp(domain);
		}
	}
	/**
	 * 根据地块获取相关设备
	 * @throws Exception 
	 */
	public void deleteAlQeada() throws Exception {
		ApiDomain domain=new ApiDomain();
		domain.setCode("000");
		domain.setMsg("操作成功");
		try {
			String alQeadaId = getRequest().getParameter("alQeadaId");
			JSONObject jsonObject = new JSONObject().element("alQaedaId", alQeadaId);
			JSONObject json = HttpClientUtil.httpPost(PropertiesUtil.getAppConfig("myMongo3")+"apiAlQaedaAction_deleteAlQaeda.do?alQaedaId="+alQeadaId, jsonObject, false);
			domain.setData(json);
		} catch (Exception e) {
			domain.setCode("001");
			domain.setMsg("系统异常");
			e.printStackTrace();
		}finally{
			this.printObjectNoJsonp(domain);
		}
	}
	/**
	 * 删除地块
	 * @throws Exception 
	 */
	public void deleteMassif() throws Exception {
		ApiDomain domain=new ApiDomain();
		domain.setCode("000");
		domain.setMsg("操作成功");
		try {
			String massifId = getRequest().getParameter("massifId");
			JSONObject jsonObject = new JSONObject().element("massifId", massifId);
			JSONObject json = HttpClientUtil.httpPost(PropertiesUtil.getAppConfig("myMongo3")+"apiAlQaedaAction_deleteMassif.do?massifId="+massifId, jsonObject, false);
			domain.setData(json);
		} catch (Exception e) {
			domain.setCode("001");
			domain.setMsg("系统异常");
			e.printStackTrace();
		}finally{
			this.printObjectNoJsonp(domain);
		}
	}
	
}
