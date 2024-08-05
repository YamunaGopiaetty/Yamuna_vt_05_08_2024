package com.example.url_shortener.controller;


import java.io.IOException;

import java.util.NoSuchElementException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.url_shortener.dto.UpdateExpiryRequest;
import com.example.url_shortener.dto.UpdateUrlRequest;

import com.example.url_shortener.service.UrlMappingService;
import com.opencsv.exceptions.CsvValidationException;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/url")
public class UrlMappingController {
	
	 @Autowired
	    private UrlMappingService urlService;

	    @PostMapping("/shorten")
	    public ResponseEntity<String> shortenUrl(@RequestBody String originalUrl) {
	        String shortUrl = urlService.shortenUrl(originalUrl);
	        return ResponseEntity.ok(shortUrl);
	    }
	   
	    @PostMapping("/update")
	    public ResponseEntity<Boolean> updateUrl(@RequestBody UpdateUrlRequest request) throws CsvValidationException {
	        boolean result = urlService.updateUrl(request.getShortUrl(), request.getNewOriginalUrl());
	        return ResponseEntity.ok(result);
	    }

	    @GetMapping("/{shortUrl}")
	    public void redirectToOriginalUrl(HttpServletResponse response, @PathVariable String shortUrl) throws CsvValidationException {
	        
	        try {
	        	String originalUrl = urlService.getOriginalUrl(shortUrl);
		        if (originalUrl == null) {
		           // response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		            //return;
		        	 throw new NoSuchElementException();
		        }
	            response.sendRedirect(originalUrl);
	        } catch (NoSuchElementException e) {
	            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "URL not found", e);
	        } catch (IOException e) {
	            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	        }
	    }

	    @PostMapping("/update-expiry")
	    public ResponseEntity<Boolean> updateExpiry(@RequestBody UpdateExpiryRequest request) throws CsvValidationException {
	        boolean result = urlService.updateExpiry(request.getShortUrl(), request.getDaysToAdd());
	        return ResponseEntity.ok(result);
	    }
	
	
}
