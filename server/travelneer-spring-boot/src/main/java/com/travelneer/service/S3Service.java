package com.travelneer.service;

import java.net.URL;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;

@Service
public class S3Service {
	
	private AmazonS3 s3Client;

	//@Value("${amazonProperties.bucketName}")
	private String bucketName = "";
	//@Value("${amazonProperties.accessKey}")
	private String accessKey = "";
	//@Value("${amazonProperties.secretKey}")
	private String secretKey = "";

	@PostConstruct
	private void initializeAmazon() {
		AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);
		 s3Client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(credentials))
				.withRegion(Regions.EU_CENTRAL_1).enablePayloadSigning().build();
	}

	public String getImage(String location) {
//		URL url = null;
//
//		try {
//			System.out.println("Generating pre-signed URL.");
//			java.util.Date expiration = new java.util.Date();
//			long milliSeconds = expiration.getTime();
//			milliSeconds += 1000 * 60 * 60; // Add 1 hour.
//			expiration.setTime(milliSeconds);
//
//			GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(bucketName,
//					location);
//			generatePresignedUrlRequest.setMethod(com.amazonaws.HttpMethod.GET);
//			generatePresignedUrlRequest.setExpiration(expiration);
//
//			url = s3Client.generatePresignedUrl(generatePresignedUrlRequest);
//
//			return url.toString();
//
//		} catch (AmazonServiceException exception) {
//			System.out.println(exception.getMessage());
//			throw exception;
//		} catch (AmazonClientException ace) {
//			System.out.println(ace.getMessage());
//			throw ace;
//		}

		return "fake url";
	}
}
