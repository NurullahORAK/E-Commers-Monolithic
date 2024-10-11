package org.orak.ecommers.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BasketProductDto {
    private int id;
    private int basketId;
    private ProductDto product;
}