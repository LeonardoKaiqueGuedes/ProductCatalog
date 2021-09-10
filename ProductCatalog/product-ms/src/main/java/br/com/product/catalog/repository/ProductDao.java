package br.com.product.catalog.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import br.com.product.catalog.model.Products;

@Repository
public class ProductDao {

	@PersistenceContext
	private EntityManager em;
	
	public List<Products> getProducts(String q, Double min_price, Double max_price) {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Products> query = criteriaBuilder.createQuery(Products.class);
		Root<Products> root = query.from(Products.class);
		
		Path<String> namePath = root.<String> get("name");
		Path<String> descriptionPath = root.<String> get("description");
		
		Path<Double> min_pricePath = root.<Double> get("price");
		Path<Double> max_pricePath = root.<Double> get("price");
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		if (q != "" && q != null) {			
				Predicate sameName = criteriaBuilder.like(criteriaBuilder.lower(namePath), "%" + q + "%");
				Predicate sameDesc = criteriaBuilder.like(criteriaBuilder.lower(descriptionPath), "%" + q + "%");
				
				predicates.add(criteriaBuilder.or(sameName, sameDesc));				
		}
		
		if(min_price != null) {
			Predicate same_minPrice = criteriaBuilder.greaterThanOrEqualTo(min_pricePath, min_price);
			predicates.add(same_minPrice);
		}
		
		if(max_price != null) {
			Predicate same_maxPrice = criteriaBuilder.lessThanOrEqualTo(max_pricePath, max_price);
			predicates.add(same_maxPrice);			
		}
		
		query.where((Predicate[]) predicates.toArray(new Predicate[0]));
		
		TypedQuery<Products> typedQuery = em.createQuery(query);
		typedQuery.setHint("org.hibernate.cacheable", "true");
		
		return typedQuery.getResultList();
	}
}