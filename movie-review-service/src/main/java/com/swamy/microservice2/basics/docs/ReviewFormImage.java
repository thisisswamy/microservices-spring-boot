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
	private byte[] imageBytes;
	
	
	public ReviewFormImage(String imageName, long imageSize, String imageType, byte[] imageBytes) {
		super();
		this.imageName = imageName;
		this.imageSize = imageSize;
		this.imageType = imageType;
		this.imageBytes = imageBytes;
	}
	public String getImageType() {
		return imageType;
	}
	public void setImageType(String imageType) {
		this.imageType = imageType;
	}
	public String getImageName() {
		return imageName;
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
	public byte[] getImageBytes() {
		return imageBytes;
	}
	public void setImageBytes(byte[] imageBytes) {
		this.imageBytes = imageBytes;
	}
	public ReviewFormImage() {
		super();
	}
	@Override
	public String toString() {
		return "ReviewFormImage [imageName=" + imageName + ", imageSize=" + imageSize + ", imageBytes="
				+ Arrays.toString(imageBytes) + "]";
	}
	
	
	
	
	
}
