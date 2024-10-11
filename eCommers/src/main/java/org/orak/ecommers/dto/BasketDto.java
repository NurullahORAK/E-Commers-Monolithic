package org.orak.ecommers.dto;

import lombok.*;

import java.util.List;
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BasketDto {
    private int id;
    private List<BasketProductDto> basketProductList;
    private UserDto user;
}
