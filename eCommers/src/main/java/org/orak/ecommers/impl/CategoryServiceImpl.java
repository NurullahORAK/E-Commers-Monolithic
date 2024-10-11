package org.orak.ecommers.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.orak.ecommers.dto.CategoryDto;
import org.orak.ecommers.dto.ProductDto;
import org.orak.ecommers.entity.Product;
import org.orak.ecommers.repository.CategoryRepository;
import org.orak.ecommers.service.CategoryService;
import org.springframework.stereotype.Service;
import org.orak.ecommers.entity.Category;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service

public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository repository;

    @Override
    public CategoryDto get(String id) {
        return  toDto(repository.findById(Integer.parseInt(id)).get());

    }

    @Override
    public CategoryDto create(CategoryDto dto) {
        return  toDto(repository.save(toEntity(dto)));

    }

    @Override
    public void delete(String id) {
        repository.deleteById(Integer.parseInt(id));

    }

    @Override
    public CategoryDto update(String id, CategoryDto dto) {
        CategoryDto dto1 = toDto(repository.findById(Integer.parseInt(id)).get());
        CategoryDto a = CategoryDto.builder()
                .productList(dto1.getProductList())
                .name(dto1.getName())
                .id(dto1.getId()).build();
        return toDto(repository.save(toEntity(dto1)));

    }

    private CategoryDto toDto(Category category) {
        return CategoryDto.builder()
                .name(category.getName())
                .productList(category.getProductList().stream()
                        .map(product -> ProductDto.builder()
                                .name(product.getName())
                                .categoryId(product.getCategory().getId())
                                .id(product.getId())
                                .build())
                        .collect(Collectors.toList())) // Fixed here to collect the stream as a List
                .build();
    }

    private Category toEntity(CategoryDto dto) {
        return Category.builder()
                .id(dto.getId())
                .name(dto.getName())
                .productList(dto.getProductList().stream()
                        .map(productDto -> Product.builder()
                                .name(productDto.getName())
                                .id(productDto.getId())
                                .category(repository.findById(productDto.getCategoryId())
                                        .orElseThrow(() -> new EntityNotFoundException("Category not found")))
                                .build())
                        .collect(Collectors.toList())) // Collect the stream as a list
                .build();
    }

    public Category getCategory(int id) {
        return repository.findById(id).get();
    }

}
