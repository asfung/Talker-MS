package com.paung.repository;

import com.paung.entity.Product;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
  List<Product> findAllByIdInOrderById(List<Integer> ids);
//  List<Product> findAll();
}