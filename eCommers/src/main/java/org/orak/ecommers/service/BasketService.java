package org.orak.ecommers.service;

import org.orak.ecommers.dto.BasketDto;

public interface BasketService {
    void delete(String id);

    BasketDto create(BasketDto dto);

    BasketDto update(String id, BasketDto dto);

    BasketDto get(String id);
}
