package io.gary.bestshop.product.repository;

import io.gary.bestshop.product.domain.Category;
import io.gary.bestshop.product.domain.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

    Optional<Product> findById(String id);

    List<Product> findByCategory(Category category);
}
