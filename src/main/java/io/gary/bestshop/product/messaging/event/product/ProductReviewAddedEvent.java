package io.gary.bestshop.product.messaging.event.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductReviewAddedEvent {

    private ProductDto product;

    private ReviewDto newReview;
}
