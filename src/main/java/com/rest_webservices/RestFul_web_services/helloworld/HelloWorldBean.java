package com.rest_webservices.RestFul_web_services.helloworld;

public class HelloWorldBean {

	private String msg;

	public HelloWorldBean(String msg) {
		// TODO Auto-generated constructor stub
		this.msg = msg;
	}
	

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}


	@Override
	public String toString() {
		return "HelloWorldBean [msg=" + msg + "]";
	}
	
	
	

}
