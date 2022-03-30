package com.metryfabricshopnew.service;

import com.metryfabricshopnew.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    List<ProductDTO> getAll();

    void addToUserBasket(Long productId, String username);
}
