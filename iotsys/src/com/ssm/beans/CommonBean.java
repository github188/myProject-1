package com.ssm.beans;

/*Table表格处理后的数据对象*/
public class CommonBean {
	
	private Object t1;
	private Object t2;
	private Object t3;
	private Object t4;
	private Object t5;
	private Object t6;
	private Object t7;
	private Object t8;
	private Object t9;
	private Object t10;
	
	public CommonBean(){
		super();
	}
	public CommonBean(Object t1,Object t2,Object t3,Object t4,Object t5,Object t6,Object t7,Object t8,Object t9,Object t10){
		this.t1=t1;
		this.t2=t2;
		this.t3=t3;
		this.t4=t4;
		this.t5=t5;
		this.t6=t6;
		this.t7=t7;
		this.t8=t8;
		this.t9=t9;
		this.t10=t10;	
	}
	public Object getT1() {
		return t1;
	}
	public void setT1(Object t1) {
		this.t1 = t1;
	}
	public Object getT2() {
		return t2;
	}
	public void setT2(Object t2) {
		this.t2 = t2;
	}
	public Object getT3() {
		return t3;
	}
	public void setT3(Object t3) {
		this.t3 = t3;
	}
	public Object getT4() {
		return t4;
	}
	public void setT4(Object t4) {
		this.t4 = t4;
	}
	public Object getT5() {
		return t5;
	}
	public void setT5(Object t5) {
		this.t5 = t5;
	}
	public Object getT6() {
		return t6;
	}
	public void setT6(Object t6) {
		this.t6 = t6;
	}
	public Object getT7() {
		return t7;
	}
	public void setT7(Object t7) {
		this.t7 = t7;
	}
	public Object getT8() {
		return t8;
	}
	public void setT8(Object t8) {
		this.t8 = t8;
	}
	public Object getT9() {
		return t9;
	}
	public void setT9(Object t9) {
		this.t9 = t9;
	}
	public Object getT10() {
		return t10;
	}
	public void setT10(Object t10) {
		this.t10 = t10;
	}
	@Override
	public String toString() {
		return "CommonBean [t1=" + t1 + ", t2=" + t2 + ", t3=" + t3 + ", t4="
				+ t4 + ", t5=" + t5 + ", t6=" + t6 + ", t7=" + t7 + ", t8="
				+ t8 + ", t9=" + t9 + ", t10=" + t10 + "]\r\n";
	}
	
}
