package com.sj.stbm.service;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class StockService {

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	public Map<String, String> getStockList() {
		Map<Object, Object> map = redisTemplate.opsForHash().entries("stocklist");
		ObjectMapper om = new ObjectMapper();
		Map<String, String> stockMap = map.entrySet().stream().collect(Collectors.toMap(e -> {
			try {
				Map<String, String> m = om.readValue((String) e.getValue(), Map.class);
				return m.get("isinCd");
			} catch (JsonMappingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (JsonProcessingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return "";
		}, e -> {
			try {
				Map<String, String> m = om.readValue((String) e.getValue(), Map.class);
				return m.get("isinCdNm");
			} catch (JsonMappingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (JsonProcessingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return "";
		}));

		return stockMap;
	}
}
