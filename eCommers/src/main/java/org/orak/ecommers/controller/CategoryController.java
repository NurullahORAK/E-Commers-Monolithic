package org.orak.ecommers.controller;

import lombok.RequiredArgsConstructor;
import org.orak.ecommers.dto.CategoryDto;
import org.orak.ecommers.dto.ProductDto;
import org.orak.ecommers.request.CategoryRequest;
import org.orak.ecommers.response.CategoryResponse;
import org.orak.ecommers.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService service;

    @GetMapping
    public CategoryResponse get(@RequestParam(value = "id") String id) {
        return toResponse(service.get(id));
    }

    @PostMapping
    public CategoryResponse create(@RequestBody CategoryRequest request) {
        return toResponse(service.create(toDto(request)));
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable(name = "id") String id) {
        service.delete(id);
    }

    @PutMapping("{id}")
    public CategoryResponse update(@PathVariable(name = "id") String id, @RequestBody CategoryRequest request) {
        return toResponse(service.update(id, toDto(request)));
    }

    private CategoryDto toDto(CategoryRequest request) {
        return CategoryDto.builder()
                .name(request.name)
                .productList(request.getProductList().stream().map(product -> ProductDto.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .build()).collect(Collectors.toList())).build();

    }

    private CategoryResponse toResponse(CategoryDto categoryDto) {
        return CategoryResponse.builder()
                .id(categoryDto.getId())
                .name(categoryDto.getName())
                .productList(categoryDto.getProductList()).build();
    }
}
