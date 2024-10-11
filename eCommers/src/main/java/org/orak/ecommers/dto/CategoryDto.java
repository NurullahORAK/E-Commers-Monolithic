package org.orak.ecommers.dto;

import lombok.*;
import org.orak.ecommers.entity.Product;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {
    private int id;
    private String name;
    private List<ProductDto> productList;
}
