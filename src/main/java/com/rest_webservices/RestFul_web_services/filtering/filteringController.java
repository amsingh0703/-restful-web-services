package com.rest_webservices.RestFul_web_services.filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

@RestController
public class filteringController {
	
	//Static filtering(Here we directly add JsonIgnore on fields)
	@GetMapping("/filtering")
	public someBeans filtering() {
		return new someBeans("Value1","Value2","Value3");
	}
	
	@GetMapping("/filtering-list")
	public List<someBeans> filteringList() {
		return Arrays.asList(new someBeans("Value1","Value2","Value3"),
				new someBeans("Value4","Value5","Value3"),
				new someBeans("Value7","Value2","Value3"));
	}
	
	//Dynamic filtering
	
	@GetMapping("/filtering-with-view")
	@JsonView(view.view1.class)
	public someBeans filteringwithView() {
		return new someBeans("Value1","Value2","Value3");
	}
	
	@GetMapping("/filtering-list-with-view")
	@JsonView(view.view2.class)
	public List<someBeans> filteringListWithView() {
		return Arrays.asList(new someBeans("Value1","Value2","Value3"),
				new someBeans("Value4","Value5","Value3"),
				new someBeans("Value7","Value2","Value3"));
	}
}
