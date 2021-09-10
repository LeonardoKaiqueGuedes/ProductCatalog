package br.com.product.catalog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.product.catalog.model.Products;

public interface ProductRepository extends JpaRepository<Products, Long>{

	List<Products> findByName(String name);
}
