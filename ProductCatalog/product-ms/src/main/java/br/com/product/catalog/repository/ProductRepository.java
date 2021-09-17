package br.com.product.catalog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.product.catalog.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{}
	
//	@Query(value = "select * from Product where LOWER(name) like %:q% or LOWER(description) like %:q% " + 
//			"and price BETWEEN :min_price AND :max_price", nativeQuery=true)			
//	public List<Product> findFiltered(@RequestParam(required=false) String q,
//									  @RequestParam(required=false) Double min_price,
//									  @RequestParam(required=false) Double max_price);