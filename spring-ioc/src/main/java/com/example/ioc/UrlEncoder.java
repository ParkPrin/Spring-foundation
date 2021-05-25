package com.example.ioc;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import org.springframework.stereotype.Component;

@Component
public class UrlEncoder implements IEncoder {

	public String encode(String message) {
		try {
			return URLEncoder.encode(message, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}

}
