package com.example.url_shortener.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "url", uniqueConstraints = @UniqueConstraint(columnNames = "short_url"))
public class UrlMapping {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "short_url", nullable = false, unique = true, length = 30)
	private String shortUrl;

	@Column(name = "expiry_date")
	private LocalDateTime expiresAt;

	@Column(name = "destination_url")
	private String destinationUrl;

	public UrlMapping() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UrlMapping( Long id ,String shortUrl, 
			LocalDateTime expiresAt,String destinationUrl) {
		super();
		this.id = id;

		this.shortUrl = shortUrl;

		this.expiresAt = expiresAt;
		this.destinationUrl = destinationUrl;
	}

	public Long getId() {
		return id; 
	}
	public void setId(Long id) { 
		this.id = id; 
	}

	public String getShortUrl() {
		return shortUrl; 
	}
	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl; 
	}

	public LocalDateTime getExpiresAt() { 
		return expiresAt; 
	}
	public void setExpiresAt(LocalDateTime expiresAt) {
		this.expiresAt = expiresAt; 
	}
	public String getDestinationUrl() {
		return destinationUrl;
	}

	public void setDestinationUrl(String destinationUrl) {
		this.destinationUrl = destinationUrl;
	}














}

