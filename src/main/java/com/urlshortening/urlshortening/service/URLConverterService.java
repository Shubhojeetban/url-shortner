package com.urlshortening.urlshortening.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.urlshortening.urlshortening.common.IDConverter;
import com.urlshortening.urlshortening.repository.URLRepository;

@Service
public class URLConverterService {
	private static final Logger LOGGER = LoggerFactory.getLogger(URLRepository.class);
	private final URLRepository urlRepository;
	
	@Autowired
	public URLConverterService(URLRepository urlRepository) {
		this.urlRepository = urlRepository;
	}
	
	public String shortenURL(String localURL, String longUrl) {
		LOGGER.info("Shortening {}", longUrl);
		Long id = urlRepository.incrementID();
		String uniqueId = IDConverter.INSTANCE.createUniqueueID(id);
		
		urlRepository.saveUrl("url:"+id, longUrl);
		
		String baseString = formatLocalURLFromShortner(localURL);
		String shortenUrl = baseString+uniqueId;
		LOGGER.info("The Base String is " + baseString);
		return shortenUrl;
	}
	
	public String getLongUrlFromId(String uniqueID) throws Exception {
		Long dictionaryKey = IDConverter.INSTANCE.getDictionaryKeyFromUniqueID(uniqueID);
		String longUrl = urlRepository.getUrl(dictionaryKey);
		LOGGER.info("Converting shortened url back to {}", longUrl);
		return longUrl;
	}
	
	private String formatLocalURLFromShortner(String localUrl) {
		String[] addressComponents = localUrl.split("/");
		
		StringBuilder sBuilder = new StringBuilder();
		
		for(int i =0; i < addressComponents.length -1; i++) {
			sBuilder.append(addressComponents[i]);
		}
		sBuilder.append('/');
		return sBuilder.toString();
	}
}
