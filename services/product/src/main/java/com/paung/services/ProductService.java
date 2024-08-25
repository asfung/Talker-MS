package com.paung.services;

import com.paung.entity.Product;
import com.paung.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService{

  private ProductRepository productRepository;

  public List<Product> findAll(){
    return productRepository.findAll();
  }

}

