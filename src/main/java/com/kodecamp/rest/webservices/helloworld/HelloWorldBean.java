package com.kodecamp.rest.webservices.helloworld;

public class HelloWorldBean {
	
	private String m;

	public HelloWorldBean(String m) {
		this.m=m;
	}

	public void setM(String m) {
		this.m = m;
	}

	public String getM() {
		return m;
	}

	@Override
	public String toString() {
		return "HelloWorldBean [m=" + m + "]";
	}
	

}
