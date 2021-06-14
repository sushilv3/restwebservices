package com.kodecamp.rest.webservices.filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {
	
	
	@GetMapping("/filtring")
	public SomeBean retrieveSomeBean() {
		
		return new SomeBean("value1","value2","value3");
	}
	

	@GetMapping("/filtring-list")
	public List<SomeBean> retrieveListOfSomeBean() {
		
		return Arrays.asList(new SomeBean("value12","value22","value32"), new SomeBean("value1","value2","value3"));
	
	}
	
	@GetMapping("/dynamic-filtring")
	public MappingJacksonValue dynamicRetrieveSomeBean() {
		
		SomeBean2 sb2= new SomeBean2("value1","value2","value3","value4");
		
		//configure dynaming value filter
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1","field2");
//		
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
//		
		MappingJacksonValue mapping = new MappingJacksonValue(sb2);
		mapping.setFilters(filters);
		
		return mapping;
		
	}
	

	@GetMapping("/dynamic-filtring-list")
	public List<SomeBean2> dynamicRetrieveListOfSomeBean() {
		
		return Arrays.asList(new SomeBean2("value12","value22","value32","value42"), new SomeBean2("value1","value2","value3","value4"));
	
	}
}
