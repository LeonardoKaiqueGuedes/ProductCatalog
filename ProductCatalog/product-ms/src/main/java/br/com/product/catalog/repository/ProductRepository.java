package br.com.product.catalog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.product.catalog.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	
	@Query(value = "SELECT * FROM Product WHERE (:q IS NULL OR (UPPER(name) LIKE UPPER(CONCAT('%', :q, '%'))" 
				    	+ "OR UPPER(description) LIKE UPPER(CONCAT('%', :q, '%'))))" 
							+ "AND (:min_price IS NULL OR price >= :min_price)" 
								+ "AND (:max_price IS NULL OR price <= :max_price)", nativeQuery = true) 
	List<Product> findFiltered(String q, Double min_price, Double max_price);
}