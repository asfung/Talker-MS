//package com.paung.config;
//
//import com.paung.client.ProductClient;
//import feign.Feign;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//
//public class ProductClientFeginConfig {
//
//  @Value("${product.service.url}")
//  private String productServiceUrl;
//
//  @Bean
//  public ProductClient productServiceClient() {
//    return Feign.builder()
//            .encoder(new JacksonEncode())
//            .decoder(new JacksonDecoder())
//            .target(ProductClient.class, productServiceUrl);
//  }
//
//}
