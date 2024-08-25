package com.paung.entity;

import jakarta.persistence.*;
import jdk.jfr.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Product {
  @Id
  @GeneratedValue
  private Integer id;
  private String name;
  private String description;
  private double availableQuantity;
  private BigDecimal price;
//  @ManyToOne
//  @JoinColumn(name = "category_id")
//  private Category category;

}
