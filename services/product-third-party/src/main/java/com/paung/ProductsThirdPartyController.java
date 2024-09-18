package com.paung;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/product-third-party")
@RequiredArgsConstructor
public class ProductsThirdPartyController {

  private final ProductThirdPartyService productThirdPartyService;

  @GetMapping("/all")
  public ResponseEntity<FullProductThirdPartyResponse> findAll(HttpServletRequest request){
    String authorizationHeader = request.getHeader("Authorization");
    return ResponseEntity.ok(productThirdPartyService.findAll(authorizationHeader));
  }

}
