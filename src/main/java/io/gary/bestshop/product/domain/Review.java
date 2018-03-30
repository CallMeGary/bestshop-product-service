package io.gary.bestshop.product.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Wither;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Wither
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Review {

    @Min(1)
    @Max(5)
    @NotNull
    private Integer rating;

    @NotBlank
    private String comment;

    @NotNull
    private Profile createdBy;

    private LocalDateTime createdAt;
}
