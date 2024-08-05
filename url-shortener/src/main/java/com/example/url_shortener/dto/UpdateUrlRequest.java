package com.example.url_shortener.dto;

public class UpdateUrlRequest {
	
	private String shortUrl;
    private String newOriginalUrl;
    
	public String getShortUrl() {
		return shortUrl;
	}
	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}
	public String getNewOriginalUrl() {
		return newOriginalUrl;
	}
	public void setNewOriginalUrl(String newOriginalUrl) {
		this.newOriginalUrl = newOriginalUrl;
	}
    
    

}
