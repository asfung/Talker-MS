package com.paung.client;

import com.paung.Product;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "product-service", url = "${application.config.product-url}" )
//@FeignClient(name = "product-service")
public interface ProductClient {

  @GetMapping("/all")
  List<Product> findAllProducts();

}
