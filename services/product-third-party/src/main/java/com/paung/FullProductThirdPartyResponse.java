package com.paung;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FullProductThirdPartyResponse {
  private String originalproduct;
  List<Product> product;
}
