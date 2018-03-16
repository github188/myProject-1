/**
 * com.dz.base.util.BaseUtil
 * 创建人： sy@东哲科技
 * 创建时间： 2016-01-22
 */
package com.ssm.util;

import java.io.File;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.util.DigestUtils;



/**
 * 公用方法
 * 1.所有类型转jsonString 方法名：objectToJson
 * 2.字符串MD5加密  方法名：getMD5Str
 * 3.字符串转时间戳  方法名：stringToTimestamp
 * 4.获取uuid  方法名： getUUID
 * 5.计算两个日期相差的月数  方法名：monthsBetween
 * 6.计算两个日期相差的天数  方法名：daysBetween
 * 7.如果字符串为null或“”就返回false  方法名：stringIsNotNull
 * 8.如果集合为null或长度<0就返回false  方法名：listIsNotNull
 * 9.获取东哲id
 * @author sy
 * @data 2016-01-22
 */
public class BaseUtil {
	/**
	 * 把对象转成jsonString（数组类型）
	 * @param obj 各种对象、list等
	 * @return
	 * @throws Exception
	 * @author sy
	 */
	@SuppressWarnings("static-access")
	public static String objectToJsonArray(Object obj) throws Exception {
		JsonConfig jsonConfig = new JsonConfig();
		//注册字段处理器来处理Date类型的字段
		jsonConfig.registerJsonValueProcessor(java.sql.Date.class,new JsonValueProcessor() {
		  private SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		  @Override
		public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {
		    return  value == null ?"" : sd.format(value);
		  }
		  @Override
		public Object processArrayValue(Object value, JsonConfig jsonConfig) {
		    return null;
		  }
		});
		//注册字段处理器来处理Timestamp类型的字段
		jsonConfig.registerJsonValueProcessor(Timestamp.class, new JsonDateValueProcessor());
		
		JSONArray str=JSONArray.fromObject(obj,jsonConfig);
		return str.toString();
	}
	
	/**
	 * 将list中的有码值key的字段转换成对应的码值value，回写到原来的list中
	 * 
	 * @param list 
	 * @param fields 需要码值转换的字段数组  例如 AdvSystemUser中有isActive字段 0代表启用， 1 代表 不启用
	 * 那么需要转换此字段换 fields的值为{"isActive"}
	 * @param clazz list中对象类型的class
	 * @return str 输出转换后的list并将其转换成json字符串
	 * @throws Exception
	 * @author sy
	 */
	public static String listToJsonStr(List list,String fields[],Class clazz) throws Exception{
		JsonConfig cfg = new JsonConfig();
		cfg.registerJsonValueProcessor(Timestamp.class, new JsonDateValueProcessor());
//		Map jsonMap = new HashMap();
		//转换list中的码值，并回写到list中
		for(int i=0; i < list.size();i++){
			for(int j=0; j < fields.length;j++){
				Method getMethod = clazz.getMethod("get"+StringUtils.capitalize(fields[j]), null);
				Method setMethod = clazz.getMethod("set"+StringUtils.capitalize(fields[j]), String.class);
				setMethod.invoke(list.get(i), CodeMap.getValue(fields[j], (String)getMethod.invoke(list.get(i),null)));
			}
		}
//		jsonMap.put("data", list);
		//将list转换成json字符串。
		String str = JSONArray.fromObject(list,cfg).toString();
		return str;
	}
	
	public static Object objToJsonStr(Object obj,String fields[],Class clazz) throws Exception{
		JsonConfig cfg = new JsonConfig();
		cfg.registerJsonValueProcessor(Timestamp.class, new JsonDateValueProcessor());
//		Map jsonMap = new HashMap();
		//转换list中的码值，并回写到list中
		for(int j=0; j < fields.length;j++){
			Method getMethod = clazz.getMethod("get"+StringUtils.capitalize(fields[j]), null);
			Method setMethod = clazz.getMethod("set"+StringUtils.capitalize(fields[j]), String.class);
			setMethod.invoke(obj, CodeMap.getValue(fields[j], (String)getMethod.invoke(obj,null)));
		}
//		jsonMap.put("data", list);
		//将list转换成json字符串。
		return obj;
	}
	
	/**
	 * 和listToJsonStr()一样   只是通过反射的值的时候通过capitalize首字母大写 修改为 uncapitalize首字母小写。
	 */
	public static String listToJsonStr1(List list,String fields[],Class clazz) throws Exception{
		JsonConfig cfg = new JsonConfig();
		cfg.registerJsonValueProcessor(Timestamp.class, new JsonDateValueProcessor());
		for(int i=0; i < list.size();i++){
			for(int j=0; j < fields.length;j++){
				Method getMethod = clazz.getMethod("get"+StringUtils.uncapitalize(fields[j]), null);
				Method setMethod = clazz.getMethod("set"+StringUtils.uncapitalize(fields[j]), String.class);
				setMethod.invoke(list.get(i), CodeMap.getValue(fields[j], (String)getMethod.invoke(list.get(i),null)));
			}
		}
		String str = JSONArray.fromObject(list,cfg).toString();
		return str;
	}
	
	
	
	
	/**
	 * 把对象转成jsonString（对象类型）
	 * @param obj
	 * @return
	 * @throws Exception
	 * @author sy
	 */
	@SuppressWarnings("static-access")
	public static String objectToJsonObj(Object obj) throws Exception {
		JsonConfig jsonConfig = new JsonConfig();
		//注册字段处理器来处理Date类型的字段
		jsonConfig.registerJsonValueProcessor(java.sql.Date.class,new JsonValueProcessor() {
		  private SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		  @Override
		public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {
		    return  value == null ?"" : sd.format(value);
		  }
		  @Override
		public Object processArrayValue(Object value, JsonConfig jsonConfig) {
		    return null;
		  }
		});
		//注册字段处理器来处理Timestamp类型的字段
		jsonConfig.registerJsonValueProcessor(Timestamp.class, new JsonDateValueProcessor());
		
		JSONObject str=JSONObject.fromObject(obj,jsonConfig);
		return str.toString();
	}
	
	/**
	 * 将list中的有码值key的字段转换成对应的码值value，回写到原来的list中
	 * 
	 * @param list 
	 * @param fields 需要码值转换的字段数组  例如 AdvSystemUser中有isActive字段 0代表启用， 1 代表 不启用
	 * 那么需要转换此字段换 fields的值为{"isActive"}
	 * @param clazz list中对象类型的class
	 * @return str 输出转换后的list并将其转换成json字符串
	 * @throws Exception
	 * @author sy
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static List listTolist(List list,String fields[],Class clazz) throws Exception{
		//转换list中的码值，并回写到list中
		for(int i=0; i < list.size();i++){
			for(int j=0; j < fields.length;j++){
				Method getMethod = clazz.getMethod("get"+StringUtils.capitalize(fields[j]), null);
				Method setMethod = clazz.getMethod("set"+StringUtils.capitalize(fields[j]), String.class);
				setMethod.invoke(list.get(i), CodeMap.getValue(fields[j], (String)getMethod.invoke(list.get(i),null)));
			}
		}
		return list;
	}
	
	
	
	/**
	 * 字符串MD5加密
	 * @param str
	 * @return
	 */
	public static String getMD5Str(String str) throws Exception {
		str = (str == null) ? "":str;
		return DigestUtils.md5DigestAsHex(str.getBytes());
	}
	
	/**
	 * 字符串转时间戳
	 * @param str_time
	 * @return
	 * @throws Exception
	 */
	public static Timestamp stringToTimestamp(String str_time) throws Exception{
		if(!StringUtils.isEmpty(str_time)){
			SimpleDateFormat sdf = null;
			if(str_time.length()>10){
				sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			}else{
				sdf = new SimpleDateFormat("yyyy-MM-dd");
			}
			Date date = null;
			try {
				date = sdf.parse(str_time);
			} catch (ParseException e) {
				System.out.println("时间转化为Timestamp异常，看看格式是否正确");
			}
			Timestamp ts = new Timestamp(date.getTime());
			return ts;
		}else{
			System.out.println("字符串为空");
		}
		return null;
	}
	
	/**
	 * 获取uuid
	 * @return
	 */
	public static String getUUID(){
	  return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	/**
	 * 计算两个日期相差的天数
	 * @param s 开始时间
	 * @param e 结束时间
	 * @return
	 * @author sy
	 */
	public static int monthsBetween(String s,String e){
		//GregorianCalendar 是 Calendar 的一个具体子类，提供了世界上大多数国家/地区使用的标准日历系统。
		//GregorianCalendar 是一种混合日历
		/*Calendar cal1 = new GregorianCalendar();
		cal1.setTime(java.sql.Date.valueOf(s));
		Calendar cal2 = new GregorianCalendar();
		cal2.setTime(java.sql.Date.valueOf(e));
		//获取相差月数
		int c = (cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR)) * 12 + 
				cal1.get(Calendar.MONTH) - cal2.get(Calendar.MONTH);
		//如果结果=0就返回1，否则就取结果的绝对值
		return c == 0 ? 1 : Math.abs(c);*/
		try {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date d1 = df.parse(e);
			Date d2 = df.parse(s);
			long diff = d1.getTime() - d2.getTime();
			long days = diff / (1000 * 60 * 60 * 24);
			return new Long(days).intValue();
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return 0;
	}
	
	/**
	 * 计算两个日期相差的天数
	 * @param s 开始日期
	 * @param e 结束日期
	 * @return
	 * @throws ParseException
	 * @author sy
	 */
	public static int daysBetween(String s,String e) throws ParseException{  
		//设置时间格式
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
        //格式化开始日期
        Calendar cal = Calendar.getInstance();    
        cal.setTime(sdf.parse(s)); 
        
        long time1 = cal.getTimeInMillis();                 
        cal.setTime(sdf.parse(e));    
        long time2 = cal.getTimeInMillis();     
        //算出两个日期相差的天数
        long between_days=(time2-time1)/(1000*3600*24);  
            
       return Integer.parseInt(String.valueOf(between_days));     
    }  
	
	
	/**
	 * 传入超过两位的double数，然后将其
	 * 处理成，只保留小数点后2位数，其余的截取掉。
	 * 去尾法
	 * @param num
	 * @return
	 */
	public static double getDoubleTwo(double num){
		num = num + 0.000001;
		BigDecimal mm = new BigDecimal(num);
		BigDecimal m = mm.setScale(2, RoundingMode.DOWN);
		//mum = m.d
		return m.doubleValue();
	}
	
	/**
	 * 如果字符串为null或“”就返回false
	 * 否则就返回true
	 * @param obj 传过来的string
	 * @return
	 */
	public static boolean stringIsNotNull(String obj) throws Exception {   
		if(!StringUtils.isEmpty(obj)){
			return true;
		}else{
		   return false;
		}
	}
	
	/**
	 * 如果集合为null或长度<0就返回false
	 * 否则就返回true
	 * @param obj 传过来的集合
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static boolean listIsNotNull(List obj) throws Exception {
		if(obj!=null && obj.size()>0){
			return true;
		}else{
			return false;
		}
	}
	
	
	/**
	 * 获取当前时间戳
	 * @return Timestamp
	 */
	public static Timestamp getCurrentTimestamp() {
		return new Timestamp(System.currentTimeMillis());
	}
	
	/**
	 * 获取当前时间  yyyy-MM-dd HH:mm:ss
	 * @return
	 * @throws Exception
	 * @author sy
	 */
	public static String getCurrentTimestr() throws Exception{
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sf.format(new Date());
	}
	
	/**
	 * 根据当前时间获取几天前的日期
	 * @param day 天数 例如 -7（7天之前）
	 * @return
	 */
	public static String getCertainTime(int day){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
		//过去七天
        c.setTime(new Date());
        c.add(Calendar.DATE,day);
        Date d = c.getTime();
        String date = format.format(d);
        return date;
	}
	
	/**
	 * yyyyMMddhhmmssSSS
	 * @return
	 * @throws Exception
	 * @author jfl
	 */
	public static String getCurrentTimeInt() throws Exception{
		SimpleDateFormat sf=new SimpleDateFormat("yyyyMMddhhmmssSSS");
		return sf.format(new Date());
	}
	/**
	 * 获取当前日期 yyyy-MM-dd
	 * @return
	 * @throws Exception
	 * @author sy
	 */
	public static String getCurrentDaystr() throws Exception{
		SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
		return sf.format(new Date());
	}
	/**
	 * 获取当前年月日（yyyy-dd-mm）
	 * @return String
	 */
	public static String getCurrentTime() {
		return new Timestamp(System.currentTimeMillis()).toString().substring(0, 10);
	}
	
	/**
	 * 获取几天前或者几天后的日期
	 * 如果是几天前就-n   几天后就n
	 * @param day
	 * @return
	 * @throws Exception
	 */
	public static java.sql.Date getNDaysAgo(int day) throws Exception{
		Calendar curr = Calendar.getInstance();
		curr.set(Calendar.DAY_OF_MONTH,curr.get(Calendar.DAY_OF_MONTH)+day);
		Date date=curr.getTime();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		java.sql.Date d=java.sql.Date.valueOf(df.format(date));
		return d;
	}
	
	/**
	 * 创建文件夹。
	 * 获取文件上传或者下载的服务器根路径。
	 * @return
	 * @author sy
	 */
	public static String getServiceUploadPath(){	
		String savePath = ServletActionContext.getServletContext().getRealPath("");
		String tempName = StringUtils.lowerCase(System.getProperty("os.name"));
		String extracted = "";
		if(tempName.startsWith("win")){
			extracted = savePath+"\\upload\\";
		}else{
			extracted = savePath+"/upload/";
		}
		
		File temp = new File(extracted);
		if(!temp.exists() && !temp.isDirectory()){
			temp.mkdirs();
		}
		return extracted;
	}
	
	/**
	 * 创建下载东哲溯源码文件用 
	 * @return
	 */
	public static String getServiceDownloadPath(){	
		String savePath = ServletActionContext.getServletContext().getRealPath("");
		String tempName = StringUtils.lowerCase(System.getProperty("os.name"));
		String extracted = "";
		if(tempName.startsWith("win")){
			extracted = savePath+"\\download\\DZSourceCodeTxt\\";
		}else{
			extracted = savePath+"/download/DZSourceCodeTxt/";
		}
		
		File temp = new File(extracted);
		if(!temp.exists() && !temp.isDirectory()){
			temp.mkdirs();
		}
		return extracted;
	}
	
	public static String toUtf8(String json) throws Exception{
		//获取操作系统字符集
		String encoding = System.getProperty("file.encoding");
		//设置json编码集
		if("GBK".equals(encoding)){
			json=new String(json.getBytes("ISO-8859-1"), "UTF-8");
		}else {
			json=new String(json.getBytes("UTF-8"), "UTF-8");
		}
		return json;
	}
	
	/**
	 * 16进制转2进制
	 * @param code 溯源码
	 * @return
	 * @author sy
	 */
	public static String hex2binary(String code){
        if (code == null || code.length() % 2 != 0){
        	return null;
        }
        StringBuilder bString = new StringBuilder(), tmp;  
        for (int i = 0; i < code.length(); i++){  
            tmp = new StringBuilder("0000");
            tmp.append(Integer.toBinaryString(Integer.parseInt(code.substring(i, i + 1), 16)));
            
            bString.append(tmp.substring(tmp.length() - 4));
        }
        return bString.toString();
	}
	
	/**
	 * 将2进制字符串转int
	 * @param binary
	 * @return
	 * @author sy
	 */
	public static int binary2int(String binary) {
		int len = binary.length();
		int sum = 0;
		int tmp,  max = len - 1;
		for (int i = 0; i < len; ++i) {
			tmp = binary.charAt(i) - '0';
			sum += tmp * Math.pow(2, max--);
		}
		return sum;
	}
	
	/**
	 * 计算字符串中空格的数量
	 * @param str
	 * @return
	 * @author sy
	 */
	public static int countSpaceSum(String str) {  
        int spacecount = 0;  
        char[] b = str.toCharArray();  
        for(int i = 0; i < b.length; i++){  
            if(b[i]==' '){  
                spacecount++;  
            }
	    }  
        return spacecount;   
	}  
	
	/**
	 * 获取主机ip地址
	 * @return
	 * @throws UnknownHostException
	 */
	public static String getMyIp() throws UnknownHostException{
		InetAddress ia = InetAddress.getLocalHost();
		return ia.getHostAddress();
	}
	
	
	
	public static void main(String[] args) {
//		String str="324/3232/apk";
//		System.out.println(File.separator);
//		System.out.println(str.substring(str.lastIndexOf("/")+1));
		
//		System.out.println("2016-09-09 20:00:00".compareTo("2016-09-09 20:00:00"));
//		System.out.println(countSpaceSum("四川省 成都市 双流区 华府大道地铁站"));
		
		String str="四川省 成都市双流区华府大道地铁站";
		String[] strs=str.split(" ");
		for(String s:strs){
			System.out.println(s);
		}
		System.out.println(strs.length);
		System.out.println(countSpaceSum(str));
	}
}
