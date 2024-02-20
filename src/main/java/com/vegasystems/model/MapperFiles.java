package com.vegasystems.model;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.stereotype.Component;

@Component
public class MapperFiles {
	
	public byte[] inputStreamToByteArray(InputStream inputStream) throws IOException {
		return inputStream.readAllBytes();
	}

}
