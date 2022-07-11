package com.sj.stbm.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class MyStockService {

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	public Map<Object, Object> getMyStockList() {
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		System.out.println(userName);
		String key = userName + "::stocks";
		Map<Object, Object> retVal = redisTemplate.opsForHash().entries(key);
		return retVal;
	}
	
	public void saveMyStockList(Map<String, Object> stocks) throws JsonProcessingException {
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		System.out.println(userName);
		String key = userName + "::stocks";
		ObjectMapper om = new ObjectMapper();
		for(String code:stocks.keySet()) {
			HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
			List<Map<String, String>> mapList = (List<Map<String, String>>) stocks.get(code);
			System.out.println(om.writeValueAsString(mapList));
			hash.put(key, code, om.writeValueAsString(mapList));
		}
	}
	
}
