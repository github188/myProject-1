package com.ssm.action;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;

import com.ssm.beans.TimerControllerInfoBean;
import com.ssm.service.ITimerControllerService;
import com.ssm.service.ITimerTaskService;


@Controller("spyControllerStatusServiceAction")
public class SpyControllerStatusServiceAction extends BaseAction{
	
	
	private Logger log=Logger.getLogger(this.getClass());
	
	@Resource
	private ITimerControllerService timerControllerService;
	
	private ITimerTaskService timerTaskService;
	public void timerControllerService()
	{
		log.info("-----修改时间控制数据---【spyControllerStatusServiceAction_timerControllerService.do】");
		//修改控制器状态，如果状态为开，则开启定时服务，状态为关则关闭定时服务
		
		try {
			//获取Ajax提交的更改后的控制器开关状态
			String id=getRequest().getParameter("id");
			boolean controllerStatus=Boolean.parseBoolean(getRequest().getParameter("timerControllerStatus"));
			TimerControllerInfoBean timerControllerInfoBean=new TimerControllerInfoBean();

			//修改时间控制服务状态
			timerControllerInfoBean=timerControllerService.getTimerControllerInfoById(id);
			timerControllerInfoBean.setControllerStatus(controllerStatus);
			timerControllerService.updateTimerControllerInfo(timerControllerInfoBean);
			
			if (controllerStatus==true) {
				//执行该bean的时间控制任务
				timerTaskService.doTask(timerControllerInfoBean);
				log.info("时间控制定时任务正在执行中");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		getRequest().setAttribute(arg0, arg1);
		
	}
}
