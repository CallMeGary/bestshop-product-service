package io.gary.bestshop.product.messaging.event.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Wither;

import java.time.LocalDateTime;

@Data
@Wither
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto {

    private Integer rating;

    private String comment;

    private String createdBy;

    private LocalDateTime createdAt;
}
