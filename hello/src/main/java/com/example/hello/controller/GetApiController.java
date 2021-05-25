package com.example.hello.controller;

import com.example.hello.dto.UserRequest;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/get")
public class GetApiController {

	@GetMapping(path = "/hello")    // http://localhost:9090/api/get/hello
	public String getHello() {
		return "get Hello";
	}

	@RequestMapping(path = "hi", method = RequestMethod.GET)   // get http://localhost:9090/api/get/hi
	//@RequestMapping(path = "hi")   // get / post  / put  / delete
	public String hi(){
		return "hi";
	}

	// http://localhost:9090/api/get/path-variable/{name}
	@GetMapping("/path-variable/{id}")
	public String pathVariable(@PathVariable(name= "id") String pathName){
		System.out.println("PathVariable: " + pathName);
		return pathName;
	}

	/**
	 * search? q = google+chrome+web+store
	 * & rlz = 1C5CHFA_enKR948KR948
	 * & oq =
	 * & aqs = chrome.0.69i59i450l8.22982069j0j15
	 * & sourceid = chrome
	 * & ie = UTF-8
	 */

	// http://lcoalhost:9090/api/get/query-param?user=steve&email=steve@gmail.com&age=30

	@GetMapping(path = "/query-param")
	public String queryParam(@RequestParam  Map<String, String> queryParam){
		StringBuilder sb = new StringBuilder();
		queryParam.entrySet().forEach( entry -> {
			System.out.println(entry.getKey());
			System.out.println(entry.getValue());
			System.out.println("\n");

			sb.append(entry.getKey()+ " = " + entry.getValue()+ "\n");
		});
		return sb.toString();
	}

	@GetMapping("query-param02")
	public String queryParam02(
			@RequestParam String name,
			@RequestParam String email,
			@RequestParam int age
	) {

		return name + " " + email + " " + age;

	}

	@GetMapping("query-param03")
	public String queryParam03(
			UserRequest userRequest
	) {

		System.out.println(userRequest.toString());
		return userRequest.toString();

	}



}
