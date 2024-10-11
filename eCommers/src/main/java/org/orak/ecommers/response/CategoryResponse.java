package org.orak.ecommers.response;

import lombok.*;
import org.orak.ecommers.dto.ProductDto;

import java.util.List;
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponse {
    private int id;
    private String name;
    private List<ProductDto> productList;
}
