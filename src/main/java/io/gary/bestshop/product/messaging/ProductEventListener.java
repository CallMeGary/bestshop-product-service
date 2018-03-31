package io.gary.bestshop.product.messaging;

import io.gary.bestshop.product.messaging.event.product.ProductCreatedEvent;
import io.gary.bestshop.product.messaging.event.product.ProductDeletedEvent;
import io.gary.bestshop.product.messaging.event.product.ProductReviewAddedEvent;
import io.gary.bestshop.product.messaging.event.product.ProductUpdatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ProductEventListener {

    @StreamListener(MessagingChannels.PRODUCT_CREATED_INPUT)
    public void handleProductCreatedEvent(ProductCreatedEvent event) {
        log.info("Processing event {}", event);
    }

    @StreamListener(MessagingChannels.PRODUCT_UPDATED_INPUT)
    public void handleProductUpdatedEvent(ProductUpdatedEvent event) {
        log.info("Processing event {}", event);
    }

    @StreamListener(MessagingChannels.PRODUCT_DELETED_INPUT)
    public void handleProductDeletedEvent(ProductDeletedEvent event) {
        log.info("Processing event {}", event);
    }

    @StreamListener(MessagingChannels.PRODUCT_REVIEW_ADDED_INPUT)
    public void handleProductReviewAddedEvent(ProductReviewAddedEvent event) {
        log.info("Processing event {}", event);
    }
}
