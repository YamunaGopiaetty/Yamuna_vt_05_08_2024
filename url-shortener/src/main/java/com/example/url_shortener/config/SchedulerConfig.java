package com.example.url_shortener.config;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.example.url_shortener.service.CsvUrlService;
import com.opencsv.exceptions.CsvValidationException;

@Configuration
@EnableScheduling
public class SchedulerConfig {
	
	@Autowired
    private CsvUrlService csvUrlService;

    @Scheduled(cron = "0 0 0 * * ?") 
    public void removeExpiredUrls() throws CsvValidationException {
        List<String[]> urls = csvUrlService.readAllUrls();
        urls.removeIf(url -> LocalDateTime.parse(url[2]).isBefore(LocalDateTime.now()));
    }

}
