package com.sj.stbm.schedule;

import java.util.Map;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import reactor.core.publisher.Mono;

@Component
public class SchdStockList {

	@Scheduled(fixedDelay = 3000)
	public void getStockList() throws JsonMappingException, JsonProcessingException {
		String url = UriComponentsBuilder
				.fromHttpUrl("http://apis.data.go.kr/1160100/service/GetStocIssuInfoService/getItemBasiInfo")
				.queryParam("ServiceKey", "VExeD6Opzi37DVShO3SURnt7jDRw4VTIGiJ0nQpqhlvmAgGqNhRjq6V/S8RaJ2t93A59Abxrb9pCn6QmvYjbQA==")
				.queryParam("pageNo", "1")
				.queryParam("numOfRows", "100")
				.queryParam("resultType", "json")
				.build().toUriString();

		WebClient client = WebClient.create();
		String mStr = client.get().uri(url)
				.retrieve()
				.bodyToMono(String.class)
				.block();
		
		ObjectMapper om = new ObjectMapper();
		Map<String,Object> map = om.readValue(mStr, Map.class);
		System.out.print(map.toString());
	}

}
