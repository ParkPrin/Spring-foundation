package com.example.validation.dto;

import com.example.validation.annotation.YearMonth;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class User {

	@NotBlank
	private String name;

	@Max(value = 90)
	private int age;

	@Email
	private String email;

	@Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$", message = "핸드폰 번호의 양식과 맞지 않습니다. 01x-xxx(x)-xxxx")
	private String phoneNumber;

	@YearMonth
	private String reqYearMonth; //yyyyMM

	@Valid
	private List<Car> cars;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getReqYearMonth() {
		return reqYearMonth;
	}

	public void setReqYearMonth(String reqYearMonth) {
		this.reqYearMonth = reqYearMonth;
	}

	public List<Car> getCars() {
		return cars;
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}

	// 재사용이 불가함
	/*	@AssertTrue(message = "yyyyMM 의 형식에 맞지 않습니다.")
	public boolean isReqYearMonthValidation() {
		try {
			LocalDate localDate = LocalDate.parse(this.reqYearMonth+"01", DateTimeFormatter.ofPattern("yyyyMMdd"));
		} catch (Exception e) {
			return false;
		}
		return true;

	}*/

	@Override
	public String toString() {
		return "User{" +
				"name='" + name + '\'' +
				", age=" + age +
				", email='" + email + '\'' +
				", phoneNumber='" + phoneNumber + '\'' +
				", reqYearMonth='" + reqYearMonth + '\'' +
				", cars=" + cars +
				'}';
	}
}
