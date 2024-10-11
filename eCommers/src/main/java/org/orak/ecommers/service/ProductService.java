package org.orak.ecommers.service;

import org.orak.ecommers.dto.ProductDto;

public interface ProductService {
    ProductDto get(String id);

    ProductDto update(String id, ProductDto dto);

    void delete(String id);

    ProductDto create(ProductDto dto);
}
