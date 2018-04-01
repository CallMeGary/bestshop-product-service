package io.gary.bestshop.product.messaging;

import io.gary.bestshop.messaging.dto.ProductDto;
import io.gary.bestshop.messaging.dto.ReviewDto;
import io.gary.bestshop.messaging.event.product.ProductCreatedEvent;
import io.gary.bestshop.messaging.event.product.ProductDeletedEvent;
import io.gary.bestshop.messaging.event.product.ProductReviewAddedEvent;
import io.gary.bestshop.messaging.event.product.ProductUpdatedEvent;
import io.gary.bestshop.product.domain.Product;
import io.gary.bestshop.product.domain.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import static org.springframework.messaging.support.MessageBuilder.withPayload;

@Component
@RequiredArgsConstructor
public class ProductEventPublisher {

    private final MessagingChannels messagingChannels;

    public Product publishProductCreatedEvent(Product product) {
        messagingChannels.productCreatedOutput().send(buildProductCreatedEvent(product));
        return product;
    }

    public Product publishProductUpdatedEvent(Product oldProduct, Product updatedProduct) {
        messagingChannels.productUpdatedOutput().send(buildProductUpdatedEvent(oldProduct, updatedProduct));
        return updatedProduct;
    }

    public Product publishProductDeletedEvent(Product product) {
        messagingChannels.productDeletedOutput().send(buildProductDeletedEvent(product));
        return product;
    }

    public Product publishProductReviewAddedEvent(Product product, Review review) {
        messagingChannels.productReviewAddedOutput().send(buildProductReviewAddedEvent(product, review));
        return product;
    }

    private Message<?> buildProductCreatedEvent(Product product) {
        ProductCreatedEvent event = new ProductCreatedEvent(toDto(product));
        return withPayload(event).build();
    }

    private Message<?> buildProductUpdatedEvent(Product oldProduct, Product updatedProduct) {
        ProductUpdatedEvent event = new ProductUpdatedEvent(toDto(oldProduct), toDto(updatedProduct));
        return withPayload(event).build();
    }

    private Message<?> buildProductDeletedEvent(Product product) {
        ProductDeletedEvent event = new ProductDeletedEvent(toDto(product));
        return withPayload(event).build();
    }

    private Message<?> buildProductReviewAddedEvent(Product product, Review review) {
        ProductReviewAddedEvent event = new ProductReviewAddedEvent(toDto(product), toDto(review));
        return withPayload(event).build();
    }

    private ProductDto toDto(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .brand(product.getBrand())
                .category(product.getCategory().name())
                .description(product.getDescription())
                .price(product.getPrice())
                .createdAt(product.getCreatedAt())
                .lastModifiedAt(product.getLastModifiedAt())
                .build();
    }

    private ReviewDto toDto(Review review) {
        return ReviewDto.builder()
                .rating(review.getRating())
                .comment(review.getComment())
                .createdBy(review.getCreatedBy())
                .createdAt(review.getCreatedAt())
                .build();
    }
}
