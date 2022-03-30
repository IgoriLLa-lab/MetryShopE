package com.metryfabricshopnew.service;

import com.metryfabricshopnew.domain.Basket;
import com.metryfabricshopnew.domain.User;
import com.metryfabricshopnew.dto.BasketDTO;

import java.util.List;

public interface BasketService {

    Basket createBasket(User user, List<Long> productsIds);

    void addProducts(Basket basket, List<Long> productsIds);

    BasketDTO getBasketByUser(String name);
}
