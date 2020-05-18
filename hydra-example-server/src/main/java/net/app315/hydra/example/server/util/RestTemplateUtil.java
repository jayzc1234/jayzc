package net.app315.hydra.example.server.util;

import com.jgw.supercodeplatform.utils.http.Requests;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;



@Component
public class RestTemplateUtil {

	@Autowired
	private RestTemplate restTemplate;
	

	public ResponseEntity<String> getRequestAndReturnJosn(String url,Map<String, Object> params,Map<String, String> headerMap)  {

		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		if (null!=headerMap && !headerMap.isEmpty()) {
			for(String key:headerMap.keySet()) {
				headers.add(key, headerMap.get(key));
			}
		}
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(url);
		if (null!=params && !params.isEmpty()) {
			for(String key:params.keySet()) {
				Object value=params.get(key);
				builder.queryParam(key,  value);
			}
		}
        
        HttpEntity<?> entity = new HttpEntity<>(headers);
        ResponseEntity<String> result = restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET, entity, String.class);
        return result;
    }
	


	public ResponseEntity<String> getRequestAndReturnJosn(String url,Map<String, Object> params,Map<String, String> headerMap, RestTemplate restTemplate)  {

		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		if (null!=headerMap && !headerMap.isEmpty()) {
			for(String key:headerMap.keySet()) {
				headers.add(key, headerMap.get(key));
			}
		}
		UriComponentsBuilder builder = UriComponentsBuilder
				.fromUriString(url);
		if (null!=params && !params.isEmpty()) {
			for(String key:params.keySet()) {
				Object value=params.get(key);
				builder.queryParam(key,  value);
			}
		}

		HttpEntity<?> entity = new HttpEntity<>(headers);
		ResponseEntity<String> result = restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET, entity, String.class);
		return result;
	}

	public ResponseEntity<String> postJsonDataAndReturnJosn(String url,String json,Map<String, String> headerMap, RestTemplate restTemplate)  {

		HttpHeaders headers = new HttpHeaders();
		//默认值 发送json数据
		headers.add("content-Type", "application/json; charset=utf-8");
		if (null!=headerMap && !headerMap.isEmpty()) {
			for(String key:headerMap.keySet()) {
				headers.add(key, headerMap.get(key));
			}
		}
		HttpEntity<String> requestEntity = new HttpEntity<String>(json, headers);
		ResponseEntity<String> result = restTemplate.exchange(url,
				HttpMethod.POST, requestEntity, String.class);
		return result;
	}

	public ResponseEntity<String> postJsonDataAndReturnJosn(String url,String json,Map<String, String> headerMap)  {

		HttpHeaders headers = new HttpHeaders();
		//默认值 发送json数据
		headers.add("content-Type", "application/json; charset=utf-8");
		if (null!=headerMap && !headerMap.isEmpty()) {
			for(String key:headerMap.keySet()) {
				headers.add(key, headerMap.get(key));
			}
		}
		HttpEntity<String> requestEntity = new HttpEntity<String>(json, headers);
		ResponseEntity<String> result = restTemplate.exchange(url,
				HttpMethod.POST, requestEntity, String.class);
		return result;
	}

	/**
	 * @param url
	 * @param c
	 * @param headerJson
	 * @param isLoadBalanced
	 * @param params
	 * @throws
	 * @Description //TODO 表单提交
	 * @Auther corbett
	 * @date 2019/5/15 10:53
	 * @Title:
	 * @return:
	 */
	public <T> T multipartAndGetResultBySpring(String url, Map<String, Object> params, Map<String, String> headerJson, Class<T> c, boolean isLoadBalanced) {
		if (isLoadBalanced) {
			return Requests.multipartAndGetResultBySpring(url, params, headerJson, c, restTemplate);
		} else {
			return Requests.multipartAndGetResultBySpring(url, params, headerJson, c, new RestTemplate());
		}
	}
}
