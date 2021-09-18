package br.com.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.product.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	
	@Query(value = "SELECT * FROM Product WHERE (:q IS NULL OR (UPPER(name) LIKE UPPER(CONCAT('%', :q, '%'))" 
				    	+ "OR UPPER(description) LIKE UPPER(CONCAT('%', :q, '%'))))" 
							+ "AND (:minPrice IS NULL OR price >= :minPrice)" 
								+ "AND (:maxPrice IS NULL OR price <= :maxPrice)", nativeQuery = true) 
	List<Product> findFiltered(String q, Double minPrice , Double maxPrice);
}