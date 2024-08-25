package com.paung.config;

import de.huxhorn.sulky.ulid.ULID;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UlidConfig {
  @Bean
  public ULID ulid(){
    return new ULID();
  }
}
