package com.rest_webservices.RestFul_web_services.filtering;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

//@JsonIgnoreProperties({"field1","field2"})
public class someBeans {
	@JsonView(view.view1.class)
	private String field1;
	@JsonIgnore
	@JsonView(view.view2.class)
	private String field2;
	
	@JsonView({view.view2.class,view.view1.class})
	private String field3;
	public someBeans(String field1, String field2, String field3) {
		super();
		this.field1 = field1;
		this.field2 = field2;
		this.field3 = field3;
	}
	public String getField1() {
		return field1;
	}
	public void setField1(String field1) {
		this.field1 = field1;
	}
	public String getField2() {
		return field2;
	}
	public void setField2(String field2) {
		this.field2 = field2;
	}
	public String getField3() {
		return field3;
	}
	public void setField3(String field3) {
		this.field3 = field3;
	}
	
	
	
	
}
