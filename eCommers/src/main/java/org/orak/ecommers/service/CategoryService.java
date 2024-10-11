package org.orak.ecommers.service;

import org.orak.ecommers.dto.CategoryDto;

public interface CategoryService {
    CategoryDto get(String id);

    CategoryDto create(CategoryDto dto);

    void delete(String id);

    CategoryDto update(String id, CategoryDto dto);
}
