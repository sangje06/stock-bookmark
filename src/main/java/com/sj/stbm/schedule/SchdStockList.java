package com.sj.stbm.schedule;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class SchdStockList {

	@Autowired
	private WebClient webClient;
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	// @Scheduled(fixedDelay = 3000)
	public void getStockList() throws JsonMappingException, JsonProcessingException {
		int page = 1;
		int rows = 1000;
		String mStr = this.sendStockList(page, rows);
		System.out.println(mStr);

		int totalCnt = this.putRedis(mStr);
		

		while (totalCnt > (page - 1) * rows) {
			page++;
			mStr = this.sendStockList(page, rows);
			this.putRedis(mStr);
		}
	}

	private String sendStockList(int page, int rows) {
		String url = UriComponentsBuilder
				.fromHttpUrl("http://apis.data.go.kr/1160100/service/GetStocIssuInfoService/getItemBasiInfo")
				.queryParam("ServiceKey", "VExeD6Opzi37DVShO3SURnt7jDRw4VTIGiJ0nQpqhlvmAgGqNhRjq6V/S8RaJ2t93A59Abxrb9pCn6QmvYjbQA==")
				.queryParam("pageNo", page)
				.queryParam("numOfRows", rows)
				.queryParam("resultType", "json")
				.build().toUriString();

		String mStr = webClient.get().uri(url).retrieve().bodyToMono(String.class).block();
		return mStr;
	}
	
	private int putRedis(String mStr) throws JsonMappingException, JsonProcessingException {

		ObjectMapper om = new ObjectMapper();
		Map<String, Object> map = om.readValue(mStr, Map.class);
		Map<String, Object> res = (Map<String, Object>) map.get("response");
		Map<String, Object> body = (Map<String, Object>) res.get("body");
		Map<String, Object> items = (Map<String, Object>) body.get("items");
		List<Map<String, Object>> itemList = (List<Map<String, Object>>) items.get("item");
		if (itemList != null && itemList.size() > 0) {
			HashOperations<String, String, Object> hashOperations = redisTemplate.opsForHash();
	        String key = "stocklist";
//			redisTemplate.delete("stocklist");
			for(Map m:itemList) {
//		        hashOperations.put(key, (String)m.get("isinCd"), m);
		        hashOperations.put(key, (String)m.get("isinCd"), om.writeValueAsString(m));
//		        Map obj = (Map) hashOperations.get(key, (String)m.get("isinCd"));
//		        Map newmap = (Map) obj;
//		        System.out.println(newmap);
			}
		}
		int totalCnt = (int) body.get("totalCount");
		
		return totalCnt;
	}
}
