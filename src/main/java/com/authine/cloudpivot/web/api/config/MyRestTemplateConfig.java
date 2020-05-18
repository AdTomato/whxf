package com.authine.cloudpivot.web.api.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;

/**
 * @Author:wangyong
 * @Date:2020/3/12 20:47
 * @Description:
 */
@SpringBootConfiguration
public class MyRestTemplateConfig {

    @Bean
    public RestTemplate getRestTemplate(ClientHttpRequestFactory factory) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(factory);
//        restTemplate.set
        restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(Charset.forName("UTF-8")));
        return restTemplate;
    }

    @Bean
    public ClientHttpRequestFactory simpleClientHttpRequestFactory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setReadTimeout(5000);
        factory.setConnectTimeout(5000);
        return factory;
    }

}
