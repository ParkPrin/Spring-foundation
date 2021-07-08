package com.example.swagger.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRes {

	// @ApiModelProperty: swagger에서 Dto의 property의 정보를 표시함
	@ApiModelProperty(value = "사용자의 이름", example = "park", required = true)
	private String name;

	@ApiModelProperty(value = "사용자의 나이", example = "34", required = true)
	private int age;
}
