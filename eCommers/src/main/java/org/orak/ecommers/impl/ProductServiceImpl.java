package org.orak.ecommers.impl;

import lombok.RequiredArgsConstructor;
import org.orak.ecommers.dto.ProductDto;
import org.orak.ecommers.entity.Product;
import org.orak.ecommers.repository.ProductRepository;
import org.orak.ecommers.service.ProductService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository repository;
    private final CategoryServiceImpl service;

    @Override
    public ProductDto get(String id) {
        return toDto(repository.findById(Integer.parseInt(id)).get());

    }

    private ProductDto toDto(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .categoryId(product.getCategory().getId()).build();
    }

    @Override
    public ProductDto update(String id, ProductDto dto) {
        ProductDto dto1 = toDto(repository.findById(Integer.parseInt(id)).get());
        ProductDto a = ProductDto.builder()
                .id(dto1.getId())
                .categoryId(dto1.getCategoryId())
                .name(dto1.getName())
                .build();
        return toDto(repository.save(toEntity(a)));
    }

    @Override
    public void delete(String id) {
        repository.deleteById(Integer.parseInt(id));
    }

    @Override
    public ProductDto create(ProductDto dto) {
        return toDto(repository.save(toEntity(dto)));
    }

    private Product toEntity(ProductDto dto) {
        return Product.builder()
                .name(dto.getName())
                .id(dto.getId())
                .category(service.getCategory(dto.getCategoryId())).build();
    }

    public Product findByProduct(int id) {
        return repository.findById(id).get();
    }
}