package com.example.response.controller;

import com.example.response.dto.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {

	// TEXT
	@GetMapping("/text")
	public String next(@RequestParam String account) {
		return account;
	}

	// JSON
	// req -> object mapper -> object -> method -> object -> object mapper -> json -> response
	@PostMapping("/json")
	public User json(@RequestBody User user) {
		return user; // 200 OK
	}


	// 명확하게 상태값을 다룸

	/**
	 *
	 * @param user
	 * @return
	 *
	 * ResponseEntity는 규격화된 반환타입 지정, 결과값 지정 및 DTO를 넣는다.
	 */

	@PutMapping("/put")
	public ResponseEntity<User> pub(@RequestBody User user){
		return ResponseEntity.status(HttpStatus.CREATED).body(user);
	}

}
