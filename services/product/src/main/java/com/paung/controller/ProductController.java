package com.paung.controller;

import com.paung.entity.Product;
import com.paung.repository.ProductRepository;
import com.paung.services.ProductService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.http.protocol.ResponseServer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

  private ProductService productService;
  private final ProductRepository productRepository;

  public ProductController(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @GetMapping("/test")
  String heloWorld(){
    return "Hello World";
  }

  @GetMapping("/produks")
  List<Product> semuaProduk(@RequestParam(name = "page", defaultValue = "1") Integer page, @RequestParam(name = "limit", defaultValue = "5") Integer limit){
//    Integer size = 5;
    Pageable pageable = PageRequest.of(page, limit, Sort.by("id").ascending());
    Page<Product> pageUser = productRepository.findAll(pageable);
    return pageUser.getContent();
  }

//  @GetMapping("/all")
//  List<Product> allProduct(){
//    return productService.findAll();
//  }


  @GetMapping("/all")
  List<Product> allProduct(){
    return productRepository.findAll();
  }

}
