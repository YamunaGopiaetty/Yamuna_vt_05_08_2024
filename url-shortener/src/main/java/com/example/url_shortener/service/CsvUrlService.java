package com.example.url_shortener.service;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

@Service
public class CsvUrlService {
	
	 private static final String CSV_FILE_PATH = "src/main/resources/urls.csv";

	    public List<String[]> readAllUrls() throws CsvValidationException {
	        List<String[]> urls = new ArrayList<>();
	        try (CSVReader csvReader = new CSVReader(new FileReader(CSV_FILE_PATH))) {
	            String[] values;
	            while ((values = csvReader.readNext()) != null) {
	                urls.add(values);
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return urls;
	    }

	    public void writeUrl(String shortUrl, String originalUrl, String expiryDate) {
	        try (CSVWriter csvWriter = new CSVWriter(new FileWriter(CSV_FILE_PATH, true))) {
	            String[] record = {shortUrl,originalUrl,expiryDate};
	            csvWriter.writeNext(record);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    	 
	    	       
	    }

	    public void updateUrl(String shortUrl, String newOriginalUrl) throws CsvValidationException {
	        List<String[]> urls = readAllUrls();
	        try (CSVWriter csvWriter = new CSVWriter(new FileWriter(CSV_FILE_PATH))) {
	            for (String[] url : urls) {
	                if (url[0].equals(shortUrl)) {
	                    url[1] = newOriginalUrl;
	                }
	                csvWriter.writeNext(url);
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    public void updateExpiry(String shortUrl, String newExpiryDate) throws CsvValidationException {
	        List<String[]> urls = readAllUrls();
	        try (CSVWriter csvWriter = new CSVWriter(new FileWriter(CSV_FILE_PATH))) {
	            for (String[] url : urls) {
	                if (url[0].equals(shortUrl)) {
	                    url[2] = newExpiryDate;
	                }
	                csvWriter.writeNext(url);
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	 
		
}
