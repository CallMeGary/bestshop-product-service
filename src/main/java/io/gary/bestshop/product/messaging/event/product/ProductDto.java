package io.gary.bestshop.product.messaging.event.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Wither;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Wither
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private String id;

    private String name;

    private String brand;

    private String category;

    private String description;

    private BigDecimal price;

    private LocalDateTime createdAt;

    private LocalDateTime lastModifiedAt;
}
