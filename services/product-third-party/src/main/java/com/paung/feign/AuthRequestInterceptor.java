package com.paung.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class AuthRequestInterceptor implements RequestInterceptor {
  @Override
  public void apply(RequestTemplate template) {
    ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    String token = attributes.getRequest().getHeader("Authorization");
    template.header("Authorization", token);

//    ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//    String authorizationHeader = attributes.getRequest().getHeader("Authorization");
//    String token = authorizationHeader.replace("Bearer ", "");
//    template.header("Authorization", "Bearer " + token);

  }

}
