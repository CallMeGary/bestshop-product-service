package io.gary.bestshop.product.service;

import io.gary.bestshop.product.domain.Category;
import io.gary.bestshop.product.domain.Product;
import io.gary.bestshop.product.domain.Review;
import io.gary.bestshop.product.errors.ProductNotFoundException;
import io.gary.bestshop.product.messaging.ProductEventPublisher;
import io.gary.bestshop.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;
import static java.util.Optional.ofNullable;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    private final ProductEventPublisher productEventPublisher;

    public List<Product> getProducts(@NotNull Optional<Category> category) {

        log.info("Getting products: category={}", category);

        if (category.isPresent()) {
            return productRepository.findByCategory(category.get());
        }

        return productRepository.findAll();
    }

    public Product getProduct(@NotNull String id) {

        log.info("Getting product: id={}", id);

        return findProductOrThrow(id);
    }

    public Product createProduct(@NotNull @Valid Product product) {

        log.info("Creating product: product={}", product);

        LocalDateTime now = LocalDateTime.now();
        Product toCreate = product.withId(null).withCreatedAt(now).withLastModifiedAt(now);

        Product createdProduct = productRepository.save(toCreate);

        return productEventPublisher.publishProductCreatedEvent(createdProduct);
    }

    public Product updateProduct(@NotNull String id, @NotNull @Valid Product product) {

        log.info("Updating product: id={}, product={}", id, product);

        Product existingProduct = findProductOrThrow(id);

        Product toUpdate = existingProduct.withLastModifiedAt(LocalDateTime.now());
        ofNullable(product.getName()).ifPresent(toUpdate::setName);
        ofNullable(product.getBrand()).ifPresent(toUpdate::setBrand);
        ofNullable(product.getPrice()).ifPresent(toUpdate::setPrice);
        ofNullable(product.getCategory()).ifPresent(toUpdate::setCategory);
        ofNullable(product.getDescription()).ifPresent(toUpdate::setDescription);

        Product updatedProduct = productRepository.save(toUpdate);

        return productEventPublisher.publishProductUpdatedEvent(existingProduct, updatedProduct);
    }

    public void deleteProduct(@NotNull String id) {

        log.info("Deleting product: id={}", id);

        Product toDelete = findProductOrThrow(id);

        productRepository.delete(toDelete);

        productEventPublisher.publishProductDeletedEvent(toDelete);
    }

    public Product addReview(@NotNull String id, @NotNull @Valid Review review) {

        log.info("Adding review for product: id={}, review={}", id, review);

        Product product = findProductOrThrow(id);

        List<Review> reviews = new ArrayList<>(ofNullable(product.getReviews()).orElse(emptyList()));

        reviews.add(review.withCreatedAt(LocalDateTime.now()));

        Product updatedProduct = productRepository.save(product.withReviews(reviews));

        return productEventPublisher.publishProductReviewAddedEvent(updatedProduct, review);
    }

    public void increasePurchaseCount(@NotNull String id) {

        log.info("Increasing purchase count for product: id={}", id);

        Product product = findProductOrThrow(id);

        Integer currentPurchaseCount = ofNullable(product.getPurchaseCount()).orElse(0);

        productRepository.save(product.withPurchaseCount(currentPurchaseCount + 1));
    }

    private Product findProductOrThrow(String id) {
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
    }

}