package com.example.ioc;

import java.util.Base64;
import org.springframework.stereotype.Component;

@Component("base64Encoder")
public class Base64Encoder implements IEncoder {

	public String encode(String message){
		return Base64.getEncoder().encodeToString(message.getBytes());
	}

}
