package org.orak.ecommers.controller;

import lombok.RequiredArgsConstructor;
import org.orak.ecommers.dto.ProductDto;
import org.orak.ecommers.request.ProductRequest;
import org.orak.ecommers.response.ProductResponse;
import org.orak.ecommers.service.ProductService;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService service;
    @GetMapping
    public ProductResponse get(@RequestParam(value = "id")String id){
        return toResponse(service.get(id));
    }
    @PutMapping("{id}")
    public ProductResponse update(@PathVariable(name="id")String  id, @RequestBody ProductRequest request){
        return toResponse(service.update(id,toDto(request)));
    }
    @DeleteMapping("{id}")
    public void delete(@PathVariable(name = "id")String  id){
        service.delete(id);
    }
    @PostMapping("/create")
    public ProductResponse create(@RequestBody ProductRequest request){
        return toResponse(service.create(toDto(request)));
    }
    private ProductDto toDto(ProductRequest request) {
        return ProductDto.builder()
                .name(request.getName()).build();
    }

    private ProductResponse toResponse(ProductDto productDto) {
        return ProductResponse.builder()
                .id(productDto.getId())
                .name(productDto.getName())
                .categoryId(productDto.getCategoryId()).build();
    }
}
