package com.swamy.microservice2.basics.utities;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.swamy.microservice2.basics.docs.Review;
import com.swamy.microservice2.basics.repos.ReviewRepo;

import net.jpountz.lz4.LZ4BlockInputStream;
import net.jpountz.lz4.LZ4BlockOutputStream;

@Component
public class ReviewUtility {
	
	@Autowired
	private ReviewRepo repo;
	
	public boolean isReviewWritten(String key) {
		Review review=repo.findByKey(key);
		return review != null ? true : false;
	}
	
	public Review getReviewObject(String key) {
		Review review=repo.findByKey(key);
		return review;
	}
	
	public boolean getReviewByMovieName(String movieName) {
		List<Review> findAll = repo.findAll();
		boolean anyMatch = findAll.stream().anyMatch(t->t.getMovieName().toLowerCase().equals(movieName.toLowerCase()));
		return anyMatch;
	}
	
	public static byte[] compressImage(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setLevel(Deflater.BEST_COMPRESSION);
        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] tmp = new byte[4*1024];
        while (!deflater.finished()) {
            int size = deflater.deflate(tmp);
            outputStream.write(tmp, 0, size);
        }
        try {
            outputStream.close();
        } catch (Exception ignored) {
        }
        return outputStream.toByteArray();
    }



    public static byte[] decompressImage(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] tmp = new byte[4*1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(tmp);
                outputStream.write(tmp, 0, count);
            }
            outputStream.close();
        } catch (Exception ignored) {
        }
        return outputStream.toByteArray();
    }
    
    public static byte[] lZ4CompressImage(byte[] imageBytes) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        LZ4BlockOutputStream outputStream = new LZ4BlockOutputStream(baos);
        outputStream.write(imageBytes);
        outputStream.close();
        return baos.toByteArray();
    }
    
    public static byte[] lZ4DecompressImage(byte[] compressedBytes) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(compressedBytes);
        LZ4BlockInputStream inputStream = new LZ4BlockInputStream(bais);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        while ((len = inputStream.read(buffer)) != -1) {
            baos.write(buffer, 0, len);
        }
        inputStream.close();
        baos.close();
        return baos.toByteArray();
    }
    
   

	

}
