package com.paung.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "user-service", url = "${application.config.user-url}")
public interface UserClient {
//  @GetMapping("/{userId}")

}
