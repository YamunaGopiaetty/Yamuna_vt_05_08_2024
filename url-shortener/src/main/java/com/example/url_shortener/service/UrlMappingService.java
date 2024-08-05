package com.example.url_shortener.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.example.url_shortener.util.UrlShortenerUtil;
import com.opencsv.exceptions.CsvValidationException;


@Service
public class UrlMappingService {
	 @Autowired
	    private CsvUrlService csvUrlService;
	
	 
	 public static final String BASE_URL = "http://localhost:8084/api/url/";

	    public String shortenUrl(String originalUrl) {
	        String shortUrl = UrlShortenerUtil.generateShortUrl();
	        LocalDateTime expiryDate = LocalDateTime.now().plusMonths(10);
	       // UrlMapping url =new UrlMapping(shortUrl, originalUrl, expiryDate)
	        csvUrlService.writeUrl(shortUrl, originalUrl, expiryDate.toString());
	        		//expiryDate.toString());
	        return BASE_URL+shortUrl;
	    }

	    public boolean updateUrl(String shortUrl, String newOriginalUrl) throws CsvValidationException {
	        csvUrlService.updateUrl(shortUrl, newOriginalUrl);
	        return true;
	      
	    }

	    public String getOriginalUrl(String shortUrl) throws CsvValidationException {
	        List<String[]> urls = csvUrlService.readAllUrls();
	        for (String[] url : urls) {
	            if (url[0].equals(shortUrl) && LocalDateTime.parse(url[2]).isAfter(LocalDateTime.now())) {
	                return url[1];
	            }
	        }
	        return null;
	    	
	    }

	    public boolean updateExpiry(String shortUrl, int daysToAdd) throws CsvValidationException {
	        List<String[]> urls = csvUrlService.readAllUrls();
	        for (String[] url : urls) {
	            if (url[0].equals(shortUrl)) {
	                LocalDateTime newExpiryDate = LocalDateTime.parse(url[2]).plusDays(daysToAdd);
	                csvUrlService.updateExpiry(shortUrl, newExpiryDate.toString());
	                return true;
	            }
	        }
	        return false;
	    }
	 

}
