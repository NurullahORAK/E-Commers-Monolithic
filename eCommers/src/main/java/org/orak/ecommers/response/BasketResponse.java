package org.orak.ecommers.response;

import lombok.*;
import org.orak.ecommers.dto.BasketProductDto;
import org.orak.ecommers.dto.UserDto;

import java.util.List;
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BasketResponse {
    private int id;
    private List<BasketProductDto> basketProductList;
    private UserDto user;
}
