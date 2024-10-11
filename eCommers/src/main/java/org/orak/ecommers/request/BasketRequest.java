package org.orak.ecommers.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.orak.ecommers.dto.BasketProductDto;
import java.util.List;

@Builder
@Getter
@Setter
public class BasketRequest {
    public List<BasketProductDto> basketProductList;
}
