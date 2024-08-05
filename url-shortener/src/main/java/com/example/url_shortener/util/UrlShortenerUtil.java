package com.example.url_shortener.util;


import java.util.UUID;

public class UrlShortenerUtil {
	 //private static final SecureRandom random = new SecureRandom();
	   // private static final int SHORT_URL_LENGTH = 8;

	    public static String generateShortUrl() {
	       return UUID.randomUUID().toString().substring(0, 10);
	    }

}
