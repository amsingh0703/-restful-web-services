package com.rest_webservices.RestFul_web_services.filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

@RestController
public class FilteringController {
	
	// Static filtering (field2 has @JsonIgnore in SomeBeans)
	@GetMapping("/filtering")
	public SomeBeans filtering() {
		return new SomeBeans("Value1", "Value2", "Value3");
	}
	
	@GetMapping("/filtering-list")
	public List<SomeBeans> filteringList() {
		return Arrays.asList(
				new SomeBeans("Value1", "Value2", "Value3"),
				new SomeBeans("Value4", "Value5", "Value3"),
				new SomeBeans("Value7", "Value2", "Value3")
		);
	}
	
	// Dynamic filtering
	@GetMapping("/filtering-with-view")
	@JsonView(View.View1.class)
	public SomeBeans filteringWithView() {
		return new SomeBeans("Value1", "Value2", "Value3");
	}
	
	@GetMapping("/filtering-list-with-view")
	@JsonView(View.View2.class)
	public List<SomeBeans> filteringListWithView() {
		return Arrays.asList(
				new SomeBeans("Value1", "Value2", "Value3"),
				new SomeBeans("Value4", "Value5", "Value3"),
				new SomeBeans("Value7", "Value2", "Value3")
		);
	}
}
