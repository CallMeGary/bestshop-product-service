package io.gary.bestshop.product.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Wither;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Wither
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "products")
public class Product {

    @Id
    @JsonIgnoreProperties(allowGetters = true)
    private String id;

    @NotBlank
    private String name;

    @NotBlank
    private String brand;

    @NotNull
    private Category category;

    @NotBlank
    private String description;

    @NotNull
    private BigDecimal price;

    private List<Review> reviews;

    private LocalDateTime createdAt;
}
