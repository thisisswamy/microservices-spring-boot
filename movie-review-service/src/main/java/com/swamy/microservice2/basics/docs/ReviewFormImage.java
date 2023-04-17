package com.swamy.microservice2.basics.docs;

import java.util.Arrays;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class ReviewFormImage {
	
	
	private String imageName;
	private long imageSize;
	private String imageType;
	
	
	public String getImageName() {
		return imageName;
	}
	
	public ReviewFormImage() {
		super();
	}

	public ReviewFormImage(String imageName, long imageSize, String imageType) {
		this.imageName = imageName;
		this.imageSize = imageSize;
		this.imageType = imageType;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public long getImageSize() {
		return imageSize;
	}
	public void setImageSize(long imageSize) {
		this.imageSize = imageSize;
	}
	public String getImageType() {
		return imageType;
	}
	public void setImageType(String imageType) {
		this.imageType = imageType;
	}
	
	
	
	
	
	
	
}
