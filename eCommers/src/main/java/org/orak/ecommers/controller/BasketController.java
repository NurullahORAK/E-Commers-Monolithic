package org.orak.ecommers.controller;

import lombok.RequiredArgsConstructor;
import org.orak.ecommers.dto.BasketDto;
import org.orak.ecommers.request.BasketRequest;
import org.orak.ecommers.response.BasketResponse;
import org.orak.ecommers.service.BasketService;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("baskets")

public class BasketController {
    private final BasketService basketService;

    @GetMapping
    public BasketResponse get(@RequestParam(value = "id") String id) {
        return toResponse(basketService.get(id));
    }

    private BasketResponse toResponse(BasketDto basketDto) {
        return BasketResponse.builder().
                basketProductList(basketDto.getBasketProductList()).
                user(basketDto.getUser()).
                id(basketDto.getId()).
                build();
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable(name = "id") String id) {
        basketService.delete(id);
    }

    @PostMapping
    public BasketResponse create(@RequestBody BasketRequest basketRequest) {
        return toResponse(basketService.create(toDto(basketRequest)));
    }

    private BasketDto toDto(BasketRequest basketRequest) {
        return BasketDto.builder().
                basketProductList(basketRequest.basketProductList).
                build();
    }

    @PutMapping("{id}")
    public BasketResponse update(@PathVariable(name = "id") String id, @RequestBody BasketRequest basketRequest) {
        return toResponse(basketService.update(id, toDto(basketRequest)));
    }

}