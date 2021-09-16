package br.com.product.catalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.product.catalog.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{}