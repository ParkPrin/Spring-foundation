package com.example.swagger.controller;

import com.example.swagger.dto.UserReq;
import com.example.swagger.dto.UserRes;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// Swagger UI에서 타이틀에 대한 내용을 설정할 수 있음
@Api(tags = {"API 정보를 제공하는 Controller"})
@RestController
@RequestMapping("/api")
public class ApiController {

	@GetMapping("/hello")
	public String hello(){
		return "hello";
	}

	// @ApiImplicitParams: swagger에서 파라미터의 정보표시함
	@ApiImplicitParams(
			{
					@ApiImplicitParam(name = "x", value = "x 값", required = true, paramType = "path", dataType = "int"),
					@ApiImplicitParam(name = "y", value = "y 값", required = true, paramType = "query", dataType = "int")
			}
	)
	@GetMapping("/plus/{x}")
	public int plus(@PathVariable int x, @RequestParam int y){
		return x+y;
	}


	@ApiResponse(code = 502, message = "사용자의 나이가 10살 이하일때") // @ApiResponse: Response의 상태코드에 대한 메세지 표시함
	@ApiOperation(value = "사용자의 이름과 나이를 리턴 하는 메소드") // @ApiOperation: 해당 API가 무엇인지 표시함
	@GetMapping("/user")
	public UserRes user(UserReq userReq){
		return new UserRes(userReq.getName(), userReq.getAge());
	}

	@PostMapping("/user")
	public UserRes userPost(@RequestBody UserReq req){
		return new UserRes(req.getName(), req.getAge());
	}

}
