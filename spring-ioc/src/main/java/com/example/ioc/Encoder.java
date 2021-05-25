package com.example.ioc;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

public class Encoder implements IEncoder {

	private IEncoder iEncoder;

	public Encoder( IEncoder iEncoder){
		this.iEncoder = iEncoder;
	}

	public String encode(String message){
		return iEncoder.encode(message);

	}

	public void setiEncoder(IEncoder iEncoder){
		this.iEncoder = iEncoder;
	}

}
