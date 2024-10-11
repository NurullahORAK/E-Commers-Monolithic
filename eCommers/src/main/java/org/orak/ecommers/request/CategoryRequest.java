package org.orak.ecommers.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.orak.ecommers.entity.Product;

import java.util.List;
@Builder
@Setter
@Getter
public class CategoryRequest {
    public String name;
    public List<Product> productList;

}
