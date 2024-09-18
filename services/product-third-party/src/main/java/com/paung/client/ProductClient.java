package com.paung.client;

import com.paung.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(name = "product-service", url = "${application.config.product-url}" )
//@FeignClient(name = "product-service")
public interface ProductClient {

  @GetMapping("/all")
//  @RequestHeader("Authorization")
  List<Product> findAllProducts(@RequestHeader("Authorization") String token);

}
