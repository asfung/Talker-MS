package com.paung;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {

  private Integer id;
  private String name;
  private String description;
  private double availableQuantity;
}
