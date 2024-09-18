package com.paung;

import com.paung.client.ProductClient;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductThirdPartyService {

  private final ProductClient productClient;

  public FullProductThirdPartyResponse findAll(String authorizationHeader){
    var originalProduct = productClient.findAllProducts(authorizationHeader);
    return FullProductThirdPartyResponse.builder()
            .originalproduct(null)
            .product(originalProduct)
            .build();
  }

}
