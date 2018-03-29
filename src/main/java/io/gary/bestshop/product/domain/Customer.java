package io.gary.bestshop.product.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Wither;
import org.hibernate.validator.constraints.NotBlank;

@Data
@Wither
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @NotBlank
    private String id;

    @NotBlank
    private String name;
}
