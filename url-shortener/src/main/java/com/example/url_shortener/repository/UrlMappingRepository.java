package com.example.url_shortener.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.example.url_shortener.entity.UrlMapping;

public interface UrlMappingRepository extends JpaRepository<UrlMapping, Long>{
	 UrlMapping findByShortUrl(String shortUrl);

	

	
}
