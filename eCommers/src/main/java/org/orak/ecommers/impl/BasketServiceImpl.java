package org.orak.ecommers.impl;

import lombok.RequiredArgsConstructor;
import org.orak.ecommers.dto.BasketDto;
import org.orak.ecommers.dto.BasketProductDto;
import org.orak.ecommers.entity.Basket;
import org.orak.ecommers.entity.BasketProduct;
import org.orak.ecommers.repository.BasketRepository;
import org.orak.ecommers.service.BasketService;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BasketServiceImpl implements BasketService {
    private final BasketRepository basketRepository;
    private final UserServiceImpl userService;
    private final ProductServiceImpl productService;

    @Override

    public void delete(String id) {
        basketRepository.deleteById(Integer.parseInt(id));
    }

    @Override
    public BasketDto create(BasketDto dto) {
        return toDto(basketRepository.save(toEntity(dto)));
    }

    private Basket toEntity(BasketDto dto) {
        return Basket.builder()
                .id(dto.getId())
                .user(userService.findByUser(dto.getId()))
                .basketProductList(dto.getBasketProductList().stream().map(basketProductDto -> BasketProduct.builder()
                        .id(basketProductDto.getId())
                        .basket(basketRepository.findById(basketProductDto.getBasketId()).get())
                        .product(productService.findByProduct(basketProductDto.getId())).build()).collect(Collectors.toList()))
                .build();
    }

    @Override
    public BasketDto update(String id, BasketDto dto) {
        BasketDto dto1 = toDto(basketRepository.findById(Integer.parseInt(id)).get());
        BasketDto a = BasketDto.builder()
                .id(dto1.getId())
                .basketProductList(dto1.getBasketProductList())
                .user(dto1.getUser())
                .build();
        return toDto(basketRepository.save(toEntity(a)));
    }

    @Override
    public BasketDto get(String id) {
        return toDto(basketRepository.findById(Integer.parseInt(id)).get());
    }

    private BasketDto toDto(Basket basket) {
        return BasketDto.builder()
                .id(basket.getId())
                .user(userService.get(String.valueOf(basket.getUser())))
                .basketProductList(basket.getBasketProductList().stream()
                        .map(basketProduct -> BasketProductDto.builder()
                                .basketId(basketProduct.getBasket().getId())
                                .id(basketProduct.getId())
                                .product(productService.get(String.valueOf(basketProduct.getProduct().getId())))
                                .build()
                        ).collect(Collectors.toList()))
                .build();
    }
}