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

  public FullProductThirdPartyResponse findAll(){
    var originalProduct = productClient.findAllProducts();
    return FullProductThirdPartyResponse.builder()
            .originalproduct(null)
            .product(originalProduct)
            .build();
  }

}
